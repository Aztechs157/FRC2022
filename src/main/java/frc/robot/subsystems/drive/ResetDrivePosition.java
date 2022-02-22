// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ResetDrivePosition extends CommandBase {
    private final Drive drive;

    /** Creates a new ResetDrivePosition. */
    public ResetDrivePosition(final Drive drive) {
        this.drive = drive;
        addRequirements(drive);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        drive.resetDrivePosition();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean runsWhenDisabled() {
        return true;
    }
}
