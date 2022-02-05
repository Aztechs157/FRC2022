// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intake.Intake.colorResult;
import frc.robot.subsystems.kicker.Kicker;
import frc.robot.subsystems.uptake.Uptake;

public class IntakeCargo extends CommandBase {
    private Intake intake;
    private Uptake uptake;
    private Kicker kicker;

    /** Creates a new IntakeCargo. */
    public IntakeCargo(Intake intake, Uptake uptake, Kicker kicker) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.intake = intake;
        this.uptake = uptake;
        this.kicker = kicker;
        addRequirements(intake);
        addRequirements(uptake);
        addRequirements(kicker);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        intake.lowerArm();
        intake.rollerFeed();
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (!kicker.getBallSensor()) {
            intake.rollerFeed();
            uptake.uptakeFeed();
            kicker.kickerFeed();
        } else if (intake.currentColor() == colorResult.NONE) {
            intake.rollerFeed();
            uptake.uptakeFeed();
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        intake.rollerStop();
        kicker.kickerStop();
        uptake.uptakeStop();
        intake.raiseArm();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return kicker.getBallSensor() && !(intake.currentColor() == colorResult.NONE);
    }
}
