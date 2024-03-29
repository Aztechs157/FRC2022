// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.VisionConstants;
import frc.robot.subsystems.vision.Vision;

public class FindCargo extends CommandBase {
    private final Vision vision;
    private final Drive drive;
    private byte ourColor;
    private int x = 0;

    /** Creates a new FindCargo. */
    public FindCargo(Vision vision, Drive drive) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.vision = vision;
        this.drive = drive;
        addRequirements(vision);
        addRequirements(drive);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        if (DriverStation.getAlliance() == Alliance.Blue) {
            ourColor = VisionConstants.BLUE;
        } else {
            ourColor = VisionConstants.RED;
        }
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // x = vision.getNCargoX(ourColor);
        // if (x == -1) {
        // drive.driveCartesian(0, 0, -.1);
        // } else {
        // drive.driveCartesian(0, 0, (AutoConstants.X_TARGET - x) * -0.007);
        // }
        System.out.println("Not Implemented");
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drive.driveCartesian(0, 0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
        // return AutoConstants.X_TARGET - x < 15;
    }
}
