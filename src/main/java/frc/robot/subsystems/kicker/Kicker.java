// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.kicker;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.KickerConstants;
import frc.robot.subsystems.intake.Intake.ColorResult;

public class Kicker extends SubsystemBase {
    private CANSparkMax kickerMotor;
    private DigitalInput ballSensor;
    private ColorResult color;
    final ShuffleboardTab tab;
    final NetworkTableEntry kickerSensorSim;
    final NetworkTableEntry kickerColorSim;

    /** Creates a new Kicker. */
    public Kicker() {
        kickerMotor = new CANSparkMax(KickerConstants.KICKER_MOTOR_ID, MotorType.kBrushless);
        ballSensor = new DigitalInput(KickerConstants.KICKER_SENSOR_ID);
        color = ColorResult.NONE;
        tab = Shuffleboard.getTab("Debug");
        kickerSensorSim = tab.add("Kicker Sensor", false).getEntry();
        kickerColorSim = tab.add("Kicker Color", "null").getEntry();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
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
    public boolean getBallSensor(double something) {
        return ballSensor.get();
    }

    public boolean getBallSensor() {
        return kickerSensorSim.getBoolean(false);
    }

    public ColorResult getBallColor() {
        return color;
    }

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
}
