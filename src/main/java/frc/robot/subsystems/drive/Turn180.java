// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class Turn180 extends CommandBase {
    private Drive drive;
    private double degrees;

    /** Creates a new Turn180. */
    public Turn180(Drive drive, double degrees) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.drive = drive;
        addRequirements(drive);
        this.degrees = degrees;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        drive.resetGyro();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        drive.driveCartesian(0, 0, degrees > 0 ? drive.storedAngle : -drive.storedAngle);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drive.driveCartesian(0, 0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return degrees > 0 ? drive.getAngle() > degrees : -drive.getAngle() > -degrees;
    }
}
