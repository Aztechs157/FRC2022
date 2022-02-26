// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import static frc.robot.Constants.AutoConstants.*;

public class DriveBackwards extends CommandBase {
    private final Drive drive;
    private final double distance;

    /** Creates a new DriveForward. */
    public DriveBackwards(final Drive drive, double distance) {
        this.drive = drive;
        addRequirements(drive);
        this.distance = distance;

    }

    public DriveBackwards(final Drive drive) {
        this(drive, AUTO_DISTANCE_TICKS);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        drive.resetDrivePosition();
    }

    @Override
    public void execute() {
        drive.driveCartesian(-AUTO_SPEED, 0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return -drive.getDrivePosition() > AUTO_DISTANCE_TICKS;
    }

    @Override
    public void end(boolean interrupted) {
        drive.driveCartesian(0, 0, 0);
    }
}
