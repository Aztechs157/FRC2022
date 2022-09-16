// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.shooter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MiscConstants;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
    private CANSparkMax ShootFrontMotor;
    private CANSparkMax ShootBackMotor;
    private PIDController ShooterFrontPID = new PIDController(0.00001, 0, 0.000003);
    private PIDController ShooterBackPID = new PIDController(0.00001, 0, 0.000003);
    public ShuffleboardTab tab;
    public NetworkTableEntry backMotorVelocity;

    /** Creates a new Shooter. */
    public Shooter() {
        ShootFrontMotor = new CANSparkMax(ShooterConstants.SHOOTER_MOTOR1_ID, MotorType.kBrushless);
        ShootFrontMotor.setInverted(true);
        ShootFrontMotor.setSmartCurrentLimit(MiscConstants.SMART_MOTOR_LIMIT + 20);
        ShootFrontMotor.setIdleMode(IdleMode.kCoast);
        // ShootBackMotor = new CANSparkMax(ShooterConstants.SHOOTER_MOTOR2_ID,
        // MotorType.kBrushless);
        ShootBackMotor.setInverted(true);
        ShootBackMotor.setSmartCurrentLimit(MiscConstants.SMART_MOTOR_LIMIT + 20);
        ShootBackMotor.setIdleMode(IdleMode.kCoast);
        tab = Shuffleboard.getTab("Debug");
        backMotorVelocity = tab.add("Rear Shooter Speed", 0).getEntry();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    /**
     * This method will get the velocity of the motor based on the built in encoder
     * reading.
     */
    public double measureFrontVelocity() {
        return ShootFrontMotor.getEncoder().getVelocity();
    }

    public double measureBackVelocity() {
        return ShootBackMotor.getEncoder().getVelocity();
    }

    /**
     * This method will set the power of the shooter motor.
     *
     * @param power the power the motors are set too. Accepted Values must be
     *              between -1 and 1.
     */
    public void setBothPower(double power) {
        setFrontPower(power);
        setBackPower(0);
    }

    public void setFrontPower(double power) {
        ShootFrontMotor.set(power);
    }

    public void setBackPower(double power) {
        ShootBackMotor.set(0);
    }

    /**
     * This method will eject the ball out the top of the shooter.
     */
    public void ejectTop() {
        setBothPower(ShooterConstants.EJECT_SPEED);
    }

    /**
     * This method will eject the ball through the robot out through the intake.
     */
    public void ejectBottom() {
        setBothPower(-ShooterConstants.EJECT_SPEED);
    }

    /**
     * This method will stop the Shooter Motor.
     */
    public void ejectStop() {
        setBothPower(0);
    }

    /**
     * This method will calculate the pid for our current and our wanted velocity
     * for the shooter motor.
     *
     * @param goalVelocity
     * @param currentVelocity
     * @return
     */
    public double pidFrontCalculate(double goalVelocity, double currentVelocity) {
        return ShooterFrontPID.calculate(currentVelocity, goalVelocity);
    }

    public double pidBackCalculate(double goalVelocity, double currentVelocity) {
        return ShooterBackPID.calculate(currentVelocity, goalVelocity);
    }

    /**
     * Debug print for the DebugPrints command, useful for debugging and testing at
     * a later date.
     */
    public void debugPrint() {

    }
}
