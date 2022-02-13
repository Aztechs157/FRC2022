// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.controls.AxisKey;
import frc.robot.controls.DriverController;

public class TeleopDrive extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private final DriverController driverController;

    /** Creates a new TeleopDrive. */
    public TeleopDrive(final DriverController driverController, final DriveSubsystem driveSubsystem) {
        this.driverController = driverController;
        this.driveSubsystem = driveSubsystem;
        addRequirements(driveSubsystem);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        final var speedY = driverController.getAxis(AxisKey.DriveSpeedY).getAsDouble();
        final var speedX = driverController.getAxis(AxisKey.DriveSpeedX).getAsDouble();
        final var rotation = driverController.getAxis(AxisKey.DriveRotation).getAsDouble();

        driveSubsystem.driveCartesian(speedY, speedX, rotation);
    }
}
