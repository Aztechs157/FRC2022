// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MiscConstants;

import static frc.robot.Constants.DriveConstants.*;

public class Drive extends SubsystemBase {
    private final CANSparkMax frontLeftMotor = new CANSparkMax(FRONT_LEFT_MOTOR_ID, MotorType.kBrushless);
    private final CANSparkMax backLeftMotor = new CANSparkMax(BACK_LEFT_MOTOR_ID, MotorType.kBrushless);
    private final CANSparkMax frontRightMotor = new CANSparkMax(FRONT_RIGHT_MOTOR_ID, MotorType.kBrushless);
    private final CANSparkMax backRightMotor = new CANSparkMax(BACK_RIGHT_MOTOR_ID, MotorType.kBrushless);

    ADXRS450_Gyro gyro = new ADXRS450_Gyro();

    private final MecanumDrive mecanumDrive = new MecanumDrive(
            frontLeftMotor, backLeftMotor,
            frontRightMotor, backRightMotor);

    public final double storedAngle = getAngle();

    /** Creates a new Drive. */
    public Drive() {
        frontLeftMotor.setInverted(true);
        backLeftMotor.setInverted(true);
        frontLeftMotor.setSmartCurrentLimit(MiscConstants.SMART_MOTOR_LIMIT);
        backLeftMotor.setSmartCurrentLimit(MiscConstants.SMART_MOTOR_LIMIT);
        frontRightMotor.setSmartCurrentLimit(MiscConstants.SMART_MOTOR_LIMIT);
        backRightMotor.setSmartCurrentLimit(MiscConstants.SMART_MOTOR_LIMIT);
    }

    public void driveCartesian(final double ySpeed, final double xSpeed, final double zRotation) {
        mecanumDrive.driveCartesian(ySpeed, xSpeed, zRotation);
    }

    public double getDrivePosition() {
        final var frontLeft = frontLeftMotor.getEncoder().getPosition();
        final var backLeft = backLeftMotor.getEncoder().getPosition();
        final var frontRight = frontRightMotor.getEncoder().getPosition();
        final var backRight = backRightMotor.getEncoder().getPosition();

        // Take average of all 4 drive motors
        return (frontLeft + backLeft + frontRight + backRight) / 4;
    }

    public void resetDrivePosition() {
        frontLeftMotor.getEncoder().setPosition(0);
        backLeftMotor.getEncoder().setPosition(0);
        frontRightMotor.getEncoder().setPosition(0);
        backRightMotor.getEncoder().setPosition(0);
    }

    public void enableBrakeMode() {
        frontLeftMotor.setIdleMode(IdleMode.kBrake);
        backLeftMotor.setIdleMode(IdleMode.kBrake);
        frontRightMotor.setIdleMode(IdleMode.kBrake);
        backRightMotor.setIdleMode(IdleMode.kBrake);
    }

    public void disableBrakeMode() {
        frontLeftMotor.setIdleMode(IdleMode.kCoast);
        backLeftMotor.setIdleMode(IdleMode.kCoast);
        frontRightMotor.setIdleMode(IdleMode.kCoast);
        backRightMotor.setIdleMode(IdleMode.kCoast);
    }

    public double getAngle() {
        return gyro.getAngle();
    }

    public void resetGyro() {
        gyro.reset();
    }
}
