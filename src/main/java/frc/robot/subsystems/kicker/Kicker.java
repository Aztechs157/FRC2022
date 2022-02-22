// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.kicker;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.KickerConstants;
import frc.robot.subsystems.intake.Intake.ColorResult;

public class Kicker extends SubsystemBase {
    private CANSparkMax kickerMotor;
    private DigitalInput ballSensor;
    private ColorResult color;
    final NetworkTableEntry kickerColorSim = null;

    /** Creates a new Kicker. */
    public Kicker() {
        kickerMotor = new CANSparkMax(KickerConstants.KICKER_MOTOR_ID, MotorType.kBrushless);
        ballSensor = new DigitalInput(KickerConstants.KICKER_SENSOR_ID);
        color = ColorResult.NONE;
        // tab = Shuffleboard.getTab("Debug");
        // kickerColorSim = tab.add("Kicker Color", "null").getEntry();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public void runKicker(double speed) {
        kickerMotor.set(speed);
    }

    /**
     * This method feeds the ball into the shooter.
     */
    public void kickerFeed() {
        kickerMotor.set(KickerConstants.FEED_SPEED);
    }

    /**
     * This method ejects the ball from the Kicker system.
     */
    public void kickerEject() {
        kickerMotor.set(KickerConstants.EJECT_SPEED);
    }

    /**
     * This method Stops the Kicker System.
     */
    public void kickerStop() {
        kickerMotor.set(0);
    }

    /**
     * This method senses the ball, currently uncomplete and return type may change.
     *
     * @return the digital input
     */
    public boolean getBallSensor() {
        return ballSensor.get();
    }

    /**
     * Temporary getting ball sensor simulator that gives us a true or false for a
     * ball in the kicker. Runs through shuffleboard.
     *
     * @return
     */
    // public boolean getBallSensor(double something) {
    // return kickerSensorSim.getBoolean(false);
    // }

    /**
     * This method gets us the color of the cargo. Temporary simulator that runs
     * through shuffleboard.
     *
     * @return cargo color.
     */
    public ColorResult getBallColor() {
        return color;
    }

    /**
     * This method is for the temporary simulator code. sets the variable color to
     * strings as so shuffleboard can use the color.
     *
     * @param color
     */
    public void setBallColor(ColorResult color) {
        this.color = color;
        switch (color) {
            case NONE:
                kickerColorSim.setString("NONE");
                break;

            case RED:
                kickerColorSim.setString("RED");
                break;

            case BLUE:
                kickerColorSim.setString("BLUE");
                break;

            default:
                kickerColorSim.setString("NONE");
                break;
        }
    }

    /**
     * Debug print for the DebugPrints command, useful for debugging and testing at
     * a later date.
     */
    public void debugPrint() {
        System.out.println("kicker sensor: " + getBallSensor());
    }
}
