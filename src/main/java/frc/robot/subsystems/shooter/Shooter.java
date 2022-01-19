// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.shooter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
    private CANSparkMax ShootMotor1;
    private CANSparkMax ShootMotor2;

    /** Creates a new Shooter. */
    public Shooter() {
        ShootMotor1 = new CANSparkMax(ShooterConstants.SHOOTER_MOTOR1_ID, MotorType.kBrushless);
        ShootMotor2 = new CANSparkMax(ShooterConstants.SHOOTER_MOTOR2_ID, MotorType.kBrushless);
        ShootMotor2.setInverted(true);
        ShootMotor2.follow(ShootMotor1);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    /**
     * This method will get the velocity of the motor based on the built in encoder
     * reading.
     */
    public double measureVelocity() {
        return ShootMotor1.getEncoder().getVelocity();
    }

    /**
     * This method will set the power of the shooter motors.
     *
     * @param power the power the motors are set too. Accepted Values must be
     *              between -1 and 1.
     */
    public void setPower(double power) {
        ShootMotor1.set(power);
    }

    /**
     * This method will eject the ball out the top of the shooter.
     */
    public void ejectTop() {
        ShootMotor1.set(ShooterConstants.EJECT_SPEED);
    }

    /**
     * This method will eject the ball through the robot out through the intake.
     */
    public void ejectBottom() {
        ShootMotor1.set(-ShooterConstants.EJECT_SPEED);
    }

}
