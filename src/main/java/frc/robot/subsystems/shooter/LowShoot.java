// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.uptake.Uptake;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.kicker.Kicker;

public class LowShoot extends CommandBase {
    private Shooter shooter;
    private Kicker kicker;
    private Uptake uptake;
    private Intake intake;

    /** Creates a new Dump. */
    public LowShoot(Shooter shooter, Kicker kicker, Uptake uptake, Intake intake) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.shooter = shooter;
        this.kicker = kicker;
        this.uptake = uptake;
        this.intake = intake;
        addRequirements(shooter);
        addRequirements(kicker);
        addRequirements(uptake);
        addRequirements(intake);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        shooter.ejectTop();
        intake.rollerFeed();
        kicker.kickerFeed();
        uptake.uptakeFeed();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        shooter.setPower(0);
        intake.rollerStop();
        kicker.kickerStop();
        uptake.uptakeStop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
