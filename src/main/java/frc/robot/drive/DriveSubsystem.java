// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.drive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.DriveConstants.*;

public class DriveSubsystem extends SubsystemBase {
    private final CANSparkMax frontLeftMotor = new CANSparkMax(FRONT_LEFT_MOTOR_ID, MotorType.kBrushless);
    private final CANSparkMax backLeftMotor = new CANSparkMax(BACK_LEFT_MOTOR_ID, MotorType.kBrushless);
    private final CANSparkMax frontRightMotor = new CANSparkMax(FRONT_RIGHT_MOTOR_ID, MotorType.kBrushless);
    private final CANSparkMax backRightMotor = new CANSparkMax(BACK_RIGHT_MOTOR_ID, MotorType.kBrushless);

    private final MecanumDrive mecanumDrive = new MecanumDrive(
            frontLeftMotor, backLeftMotor,
            frontRightMotor, backRightMotor);

    /** Creates a new Drive. */
    public DriveSubsystem() {
        frontLeftMotor.setInverted(true);
        backLeftMotor.setInverted(true);
    }

    public void driveCartesian(final double ySpeed, final double xSpeed, final double zRotation) {
        // TODO: investigate gyro-augmented mecanum drive
        var currentGyroAngle = 0.0;
        mecanumDrive.driveCartesian(ySpeed, xSpeed, zRotation, currentGyroAngle);
    }
}
