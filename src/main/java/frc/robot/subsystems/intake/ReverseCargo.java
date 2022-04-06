// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.kicker.Kicker;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.uptake.Uptake;

public class ReverseCargo extends CommandBase {
    private Shooter shooter;
    private Uptake uptake;
    private Kicker kicker;

    /** Creates a new ReverseCargo. */
    public ReverseCargo(Uptake uptake, Kicker kicker, Shooter shooter) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.uptake = uptake;
        this.kicker = kicker;
        this.shooter = shooter;
        addRequirements(uptake);
        addRequirements(shooter);
        addRequirements(kicker);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        uptake.uptakeEject();
        shooter.ejectBottom();
        kicker.kickerEject();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        uptake.uptakeStop();
        kicker.kickerStop();
        shooter.ejectStop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
