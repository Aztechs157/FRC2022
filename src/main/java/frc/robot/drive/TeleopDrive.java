// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.controls.Axis;
import frc.robot.controls.DriverControls;

public class TeleopDrive extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private final DriverControls driverControls;

    /** Creates a new TeleopDrive. */
    public TeleopDrive(final DriverControls driverControls, final DriveSubsystem driveSubsystem) {
        this.driverControls = driverControls;
        this.driveSubsystem = driveSubsystem;
        addRequirements(driveSubsystem);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        final var speedY = driverControls.axis(Axis.DriveSpeedY).get();
        final var speedX = driverControls.axis(Axis.DriveSpeedX).get();
        final var rotation = driverControls.axis(Axis.DriveRotation).get();

        driveSubsystem.driveCartesian(speedY, speedX, rotation);
    }
}
