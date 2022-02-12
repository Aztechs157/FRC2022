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
    final ShuffleboardTab tab;
    final NetworkTableEntry turretMotorSim;
    final NetworkTableEntry posSensorSim;
    private int rotations;
    private int previousPosition;
    private int maxRotation;

    /** Creates a new Turret. */
    public Turret(OperatorController operatorController) {
        turretMotor = new CANSparkMax(TurretConstants.TURRET_MOTOR_ID, MotorType.kBrushless);
        positionSensor = new AnalogInput(TurretConstants.POSITION_SENSOR_ID);
        aimerMotor = new CANSparkMax(TurretConstants.AIMER_MOTOR_ID, MotorType.kBrushless);
        absoluteEncoder = new Counter(Mode.kSemiperiod);
        absoluteEncoder.setSemiPeriodMode(true);
        absoluteEncoder.setUpSource(TurretConstants.ABS_ENCODER_PORT);
        absoluteEncoder.reset();
        tab = Shuffleboard.getTab("Debug");
        turretMotorSim = tab.add("Turret Speed", 0).getEntry();
        posSensorSim = tab.add("Turret Position", 0).getEntry();

        this.operatorController = operatorController;
        this.setDefaultCommand(new TurretSpin(operatorController, this));
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
    public void turretTurn(double speed, double something) {
        if (speed > 0 && readPositionSensor() > TurretConstants.CLOCKWISE_BOUNDARY) {
            turretMotor.set(0);
        } else if (speed < 0 && readPositionSensor() < TurretConstants.COUNTERCLOCKWISE_BOUNDARY) {
            turretMotor.set(0);
        } else {
            turretMotor.set(speed);
        }
    }

    // temporary simulator to run the turret. Runs through shuffleboard.
    public void turretTurn(double speed) {
        if (speed > 0 && readPositionSensor() > TurretConstants.CLOCKWISE_BOUNDARY) {
            turretMotorSim.setDouble(0);
        } else if (speed < 0 && readPositionSensor() < TurretConstants.COUNTERCLOCKWISE_BOUNDARY) {
            turretMotorSim.setDouble(0);
        } else {
            turretMotorSim.setDouble(speed);
        }
    }

    /**
     * This method stops the turret motor.
     */
    public void turretStop(double something) {
        turretMotor.set(0);
    }

    public void turretStop() {
        turretMotorSim.setDouble(0);
    }

    /**
     * This method reads the position sensor on the turret.
     *
     * @return the position of the turret according to the sensor.
     */
    public int readPositionSensor(double something) {
        return positionSensor.getValue();
    }

    public int readPositionSensor() {
        return (int) Math.round(posSensorSim.getDouble(0));
    }

    /**
     * This method gets us the encoder position in degrees. 1024 is ticks.
     *
     * @return aimer position in degrees.
     */
    public double getAimerPosition() {
        return absoluteEncoder.getPeriod() * (360 / 1024) * 1000000; // equation for degree per tick converted to
                                                                     // seconds.
    }

    /**
     * This method moves the aimer in a upwards direction. Decreasing the aimer
     * angle.
     */
    public void aimerUp() {

    }

    /**
     * This method moves the aimer in a downwards direction. Increasing the aimer
     * angle.
     */
    public void aimerDown() {

    }

    /**
     * This method will run the aimer either up or down.
     * 
     * @param speed
     */
    public void runAimer(double speed) {

    }

    /**
     * This method will stop the aimer from moving.
     */
    public void stopAimer() {

    }
}
