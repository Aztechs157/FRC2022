// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.turret;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.Constants.TurretConstants;

public class Turret extends SubsystemBase {
    private CANSparkMax turretMotor;
    private AnalogInput positionSensor;
    private Servo aimerServo;

    /** Creates a new Turret. */
    public Turret() {
        turretMotor = new CANSparkMax(TurretConstants.TURRET_MOTOR_ID, MotorType.kBrushless);
        positionSensor = new AnalogInput(TurretConstants.POSITION_SENSOR_ID);
        aimerServo = new Servo(TurretConstants.AIMER_SERVO_ID);
        aimerServo.setBounds(2.0, 1.8, 1.5, 1.2, 1);
        // change to debug later
        Shuffleboard.getTab("SmartDashboard").addNumber("Turret Position", this::readPositionSensor);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    /**
     * This method turns the turret counterclockwise
     */
    public void turretLeft() {
        turretTurn(-TurretConstants.TURRET_SPEED);
    }

    /**
     * This method turns the turret clockwise
     */
    public void turretRight() {
        turretTurn(TurretConstants.TURRET_SPEED);
    }

    /**
     * This method that turns the turret at variable speeds, both counterclockwise
     * and clockwise.
     *
     * @param speed at which to turn the turret. must be between -1 and 1. -1 is
     *              counterclockwise, 1 is clockwise.
     */
    public void turretTurn(double speed) {
        if (speed > 0 && readPositionSensor() > TurretConstants.CLOCKWISE_BOUNDARY) {
            turretMotor.set(0);
        } else if (speed < 0 && readPositionSensor() < TurretConstants.COUNTERCLOCKWISE_BOUNDARY) {
            turretMotor.set(0);
        } else {
            turretMotor.set(speed);
        }
    }

    /**
     * This method stops the turret motor.
     */
    public void turretStop() {
        turretMotor.set(0);
    }

    /**
     * This method reads the position sensor on the turret.
     *
     * @return the position of the turret according to the sensor.
     */
    public int readPositionSensor() {
        return positionSensor.getValue();
    }

    /**
     * This method sets the aimer position for more accurate shots.
     *
     * @param position to set the aimer to. Must be between -1 and 1.
     */
    public void setAimerPosition(double position) {
        aimerServo.setSpeed(position);
    }
}
