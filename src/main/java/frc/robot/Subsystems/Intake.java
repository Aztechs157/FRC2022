// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {
    enum colorResult {
        RED, BLUE, NONE
    }

    private ColorSensorV3 entryColor;
    private CANSparkMax intConvMotor;
    private DoubleSolenoid intakeSolenoid;
    private final ColorMatch colorMatcher;

    /** Creates a new Intake. */
    public Intake() {
        colorMatcher = new ColorMatch();
        entryColor = new ColorSensorV3(IntakeConstants.COLORSENSOR_ID);
        intConvMotor = new CANSparkMax(IntakeConstants.INTAKE_CONVERYER_MOTOR_ID, MotorType.kBrushless);
        intakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, IntakeConstants.SOLENOIDFORWARD_ID,
                IntakeConstants.SOLENOIDREVERSE_ID);
        colorMatcher.addColorMatch(IntakeConstants.REDTARGET);
        colorMatcher.addColorMatch(IntakeConstants.BLUETARGET);
        colorMatcher.setConfidenceThreshold(IntakeConstants.COLORCONFIDENCE);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    /**
     * This method raises the Intake Arm.
     */
    public void raiseArm() {
        intakeSolenoid.set(Value.kForward);
    }

    /**
     * This method lowers the Intake Arm.
     */
    public void lowerArm() {
        intakeSolenoid.set(Value.kReverse);
    }

    /**
     * This method rolls the rollers in the correct direction to intake a ball.
     */
    public void rollerFeed() {
        intConvMotor.set(IntakeConstants.FEED_SPEED);
    }

    /**
     * This method rolls the rollers in the correct direction to expel a ball.
     */
    public void rollerEject() {
        intConvMotor.set(IntakeConstants.EJECT_SPEED);
    }

    /**
     * This method senses the current ball color and if there is a ball.
     *
     * @return the color that the ball is or {@link colorResult#NONE} if there is no
     *         ball.
     */
    public colorResult currentColor() {
        final var detectedColor = entryColor.getColor();
        final var match = colorMatcher.matchClosestColor(detectedColor);

        if (match.color == IntakeConstants.REDTARGET) {
            return colorResult.RED;
        } else if (match.color == IntakeConstants.BLUETARGET) {
            return colorResult.BLUE;
        } else {
            return colorResult.NONE;
        }
    }
}
