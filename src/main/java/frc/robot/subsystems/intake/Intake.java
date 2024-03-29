// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CompressorConstants;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.MiscConstants;

public class Intake extends SubsystemBase {
    public enum ColorResult {
        RED, BLUE, NONE
    }

    private ColorSensorV3 entryColor;
    private CANSparkMax intakeConveyorMotor;
    private DoubleSolenoid intakeSolenoid;
    private final ColorMatch colorMatcher;
    private ColorResult intakeColor;

    /** Creates a new Intake. */
    public Intake() {
        colorMatcher = new ColorMatch();
        entryColor = new ColorSensorV3(IntakeConstants.COLOR_SENSOR_ID);
        intakeConveyorMotor = new CANSparkMax(IntakeConstants.INTAKE_MOTOR_ID, MotorType.kBrushless);
        intakeConveyorMotor.setInverted(true);
        intakeSolenoid = new DoubleSolenoid(CompressorConstants.COMPRESSOR_ID, PneumaticsModuleType.REVPH,
                IntakeConstants.SOLENOID_FORWARD_ID,
                IntakeConstants.SOLENOID_REVERSE_ID);
        colorMatcher.addColorMatch(IntakeConstants.RED_TARGET);
        colorMatcher.addColorMatch(IntakeConstants.BLUE_TARGET);
        colorMatcher.setConfidenceThreshold(IntakeConstants.COLOR_CONFIDENCE);
        intakeConveyorMotor.setSmartCurrentLimit(MiscConstants.REDUCED_MOTOR_LIMIT);
        intakeColor = ColorResult.NONE;
    }

    // returns the color seen by the intake sensor
    public ColorResult getIntakeColor() {
        return intakeColor;
    }

    // sets the intake color to said color cargo in the intake
    public void setIntakeColor(ColorResult intakeCargo) {
        this.intakeColor = intakeCargo;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        // currentColor();
    }

    /**
     * This method raises the Intake Arm. This is the default position and will not
     * pick up any cargo.
     */
    public void raiseArm() {
        intakeSolenoid.set(Value.kForward);
    }

    /**
     * This method lowers the Intake Arm. Prepares to feed cargo in an open
     * position.
     */
    public void lowerArm() {
        intakeSolenoid.set(Value.kReverse);
    }

    /**
     * This method rolls the rollers in the correct direction to intake a ball.
     */
    public void rollerFeed() {
        intakeConveyorMotor.set(IntakeConstants.FEED_SPEED);
    }

    /**
     * This method rolls the rollers in the correct direction to expel a ball.
     */
    public void rollerEject() {
        intakeConveyorMotor.set(IntakeConstants.EJECT_SPEED);
    }

    /**
     * This method Stops the Intake Rollers.
     */
    public void rollerStop() {
        intakeConveyorMotor.set(0);
    }

    private NetworkTableEntry kickerColor = Shuffleboard.getTab("Debug").add("Kicker Color", "none").getEntry();

    /**
     * This method senses the current ball color and if there is a ball.
     *
     * @return the color that the ball is or {@link ColorResult#NONE} if there is no
     *         ball.
     */
    public ColorResult currentColor() {
        final var detectedColor = entryColor.getColor();
        final var match = colorMatcher.matchClosestColor(detectedColor);
        if (entryColor.getProximity() > IntakeConstants.PROX_FAR
                && entryColor.getProximity() < IntakeConstants.PROX_CLOSE) {
            if (match.color == IntakeConstants.RED_TARGET) {
                kickerColor.setString("red");
                return ColorResult.RED;
            } else if (match.color == IntakeConstants.BLUE_TARGET) {
                kickerColor.setString("blue");
                return ColorResult.BLUE;
            } else {
                kickerColor.setString("none");
                return ColorResult.NONE;
            }
        } else {
            kickerColor.setString("Not in Proximity");
            return ColorResult.NONE;
        }
    }

    /**
     * This method prints the raw color of the the color sensor.
     */
    public void rawColor() {
        System.out.println(
                "ed: " + entryColor.getRed() + "\ngreen: " + entryColor.getGreen() + "\nblue: " + entryColor.getBlue());
    }

    /**
     * Debug print for the DebugPrints command, useful for debugging and testing at
     * a later date.
     */
    public void debugPrint() {
        System.out.println(entryColor.getProximity());
    }
}
