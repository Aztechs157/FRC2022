// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.sensing;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.KickerConstants;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.Intake.ColorResult;
import frc.robot.subsystems.kicker.Kicker;
import frc.robot.subsystems.uptake.Uptake;

public class PositionCargo extends CommandBase {
    private Kicker kicker;
    private Intake intake;
    private Uptake uptake;

    /** Creates a new PositionCargo. */
    public PositionCargo(Kicker kicker, Intake intake, Uptake uptake) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.intake = intake;
        this.uptake = uptake;
        this.kicker = kicker;
        addRequirements(intake, uptake, kicker);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (!kicker.getBallSensor()) {
            if (intake.currentColor() != ColorResult.NONE || uptake.getTransitionalColor() != ColorResult.NONE) {
                uptake.uptakeFeed();
                kicker.runKicker(0.4);
                intake.rollerFeed();
            } else {
                intake.rollerStop();
                kicker.kickerStop();
                uptake.uptakeStop();
            }
        } else {
            intake.rollerStop();
            kicker.kickerStop();
            uptake.uptakeStop();
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
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
