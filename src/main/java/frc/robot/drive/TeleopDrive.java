// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.input.AxisKey;
import frc.robot.input.DriverControls;

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
        final var speedY = driverControls.axis(AxisKey.DriveSpeedY).get();
        final var speedX = driverControls.axis(AxisKey.DriveSpeedX).get();
        final var rotation = driverControls.axis(AxisKey.DriveRotation).get();

        driveSubsystem.driveCartesian(speedY, speedX, rotation);
    }
}
