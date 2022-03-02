// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.AutoConstants;

public class DriveForwards extends CommandBase {
    private Drive drive;
    private double distance;

    /** Creates a new DriveForwards. */
    public DriveForwards(Drive drive, double distance) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.drive = drive;
        addRequirements(drive);
        this.distance = distance;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        drive.resetDrivePosition();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        drive.driveCartesian(AutoConstants.AUTO_SPEED, 0, 0);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drive.driveCartesian(0, 0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return drive.getDrivePosition() > distance;
    }
}
