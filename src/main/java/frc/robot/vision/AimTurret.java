// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.vision;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.turret.Turret;

public class AimTurret extends CommandBase {
    private final VisionSubsystem vision;
    private final Turret turret;

    /** Creates a new AimTurret. */
    public AimTurret(final VisionSubsystem vision, final Turret turret) {
        this.vision = vision;
        this.turret = turret;
        addRequirements(vision);
        // Use addRequirements() here to declare subsystem dependencies.
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        vision.setLED(true);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (!vision.hasTarget()) {
            turret.turretStop();
            return;
        }

        var threshold = 0;
        var hubX = vision.getHubX();

        if (hubX > threshold) {
            turret.turretLeft();
        } else if (hubX < threshold) {
            turret.turretRight();
        } else {
            turret.turretStop();
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        vision.setLED(false);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
