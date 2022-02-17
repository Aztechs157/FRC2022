// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.turret;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Counter.Mode;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TurretConstants;
import frc.robot.controls.OperatorController;

public class Turret extends SubsystemBase {
    private CANSparkMax turretMotor;
    private AnalogInput positionSensor;
    private CANSparkMax aimerMotor;
    private Counter absoluteEncoder;
    private OperatorController operatorController;

    private int rotations = 0;
    private Double previousPosition = null;
    private int maxRotation = 300;

    /** Creates a new Turret. */
    public Turret(OperatorController operatorController) {
        turretMotor = new CANSparkMax(TurretConstants.TURRET_MOTOR_ID, MotorType.kBrushless);
        positionSensor = new AnalogInput(TurretConstants.POSITION_SENSOR_ID);
        aimerMotor = new CANSparkMax(TurretConstants.AIMER_MOTOR_ID, MotorType.kBrushless);
        absoluteEncoder = new Counter(Mode.kSemiperiod);
        absoluteEncoder.setSemiPeriodMode(true);
        absoluteEncoder.setUpSource(TurretConstants.ABS_ENCODER_PORT);
        absoluteEncoder.reset();
        this.operatorController = operatorController;
        this.setDefaultCommand(new TurretSpin(operatorController, this));
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        absEncoderUpdate();
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
        if (speed > 0 && readPositionSensor() < TurretConstants.CLOCKWISE_BOUNDARY) {
            turretMotor.set(0);
        } else if (speed < 0 && readPositionSensor() > TurretConstants.COUNTERCLOCKWISE_BOUNDARY) {
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
     * This method gets us the encoder position in degrees. 1024 is ticks.
     *
     * @return aimer position in degrees.
     */
    public double getAimerPosition() {
        return absoluteEncoder.getPeriod() * (360.0 / 1024.0) * 1000000; // equation for degree per tick converted to
        // seconds.
    }

    public double getActualPosition() {
        return getAimerPosition() + (360 * rotations);
    }

    /**
     * This method moves the aimer in a upwards direction. Decreasing the aimer
     * angle.
     */
    public void aimerUp() {
        runAimer(TurretConstants.AIMER_SPEED);
    }

    /**
     * This method moves the aimer in a downwards direction. Increasing the aimer
     * angle.
     */
    public void aimerDown() {
        runAimer(-TurretConstants.AIMER_SPEED);
    }

    /**
     * This method will run the aimer either up or down.
     *
     * @param speed
     */
    public void runAimer(double speed) {
        if (speed < 0 && getActualPosition() > TurretConstants.AIMER_HIGHER_BOUNDARY) {
            aimerMotor.set(0);
        } else if (speed > 0 && getActualPosition() < TurretConstants.AIMER_LOWER_BOUNDARY) {
            aimerMotor.set(0);
        } else {
            aimerMotor.set(speed);
        }
    }

    /**
     * This method will stop the aimer from moving.
     */
    public void stopAimer() {
        runAimer(0);
    }

    protected void absEncoderUpdate() {
        double absEncoder = getAimerPosition();
        if (previousPosition != null && Math.abs(absEncoder - previousPosition) > maxRotation) {
            if (absEncoder < previousPosition) {
                rotations++;
            } else if (absEncoder > previousPosition) {
                rotations--;
            }
        }
        previousPosition = absEncoder;
    }

    /**
     * Debug print for the DebugPrints command, useful for debugging and testing at
     * a later date.
     */
    public void debugPrint() {
        System.out.println("Aimer Position: " + getActualPosition());
        System.out.println("Turret Position: " + readPositionSensor());
    }
}
