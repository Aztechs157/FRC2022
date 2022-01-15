// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TurretConstants;

public class Turret extends SubsystemBase {
    private CANSparkMax turretMotor;
    private AnalogInput positionSensor;
    private Servo aimerServo;

    /** Creates a new Turret. */
    public Turret() {
        turretMotor = new CANSparkMax(TurretConstants.TURRETMOTOR_ID, MotorType.kBrushless);
        positionSensor = new AnalogInput(TurretConstants.POSITIONSENSOR_ID);
        aimerServo = new Servo(TurretConstants.AIMERSERVO_ID);
        aimerServo.setBounds(2.0, 1.8, 1.5, 1.2, 1);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    /**
     * This method turns the turret counterclockwise
     */
    public void turretLeft() {

    }

    /**
     * This method turns the turret clockwise
     */
    public void turretRight() {

    }

    /**
     * This method that turns the turret at variable speeds, both counterclockwise
     * and clockwise.
     *
     * @param speed at which to turn the turret. must be between -1 and 1. -1 is
     *              counterclockwise, 1 is clockwise.
     */
    public void turretTurn(double speed) {

    }

    /**
     * This method reads the position sensor on the turret.
     *
     * @return the position of the turret according to the sensor.
     */
    public double readPositionSensor() {
        return 0;
    }

    /**
     * This method sets the aimer position for more accurate shots.
     *
     * @param position to set the aimer to. Must be between -1 and 1.
     */
    public void setAimerPosition(double position) {

    }
}
