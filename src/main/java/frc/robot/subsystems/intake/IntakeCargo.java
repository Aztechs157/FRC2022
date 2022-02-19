// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intake.Intake.ColorResult;
import frc.robot.subsystems.kicker.Kicker;
import frc.robot.subsystems.uptake.Uptake;

public class IntakeCargo extends CommandBase {
    private Intake intake;
    private Uptake uptake;
    private Kicker kicker;
    private Command end = new RunIntake(intake, .5);

    /** Creates a new IntakeCargo. */
    public IntakeCargo(Intake intake, Uptake uptake, Kicker kicker) {
        // Use addRequirements() here to declare subsystem dependencies.
        // requires intake, uptake, and kicker to work, and stops other mechanisms that
        // need those systems while running.
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
        // Lowers the Intake Arms and starts spinning the rollers on press

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        /**
         * Runs while button press is active, checks the sensors to see when to stop
         * feeding cargo once sensors meet certain requirements.
         *
         * If kicker sensor isn't blocked by cargo
         */
        if (!kicker.getBallSensor()) {
            intake.rollerFeed();
            uptake.uptakeFeed();
            kicker.runKicker(0.4);
            intake.lowerArm();
            intake.rollerFeed();
            // else if the intake sensor isn't blocked by a ball but the kicker sensor is.
        } else if (intake.currentColor() == ColorResult.NONE) {
            intake.rollerFeed();
            uptake.uptakeFeed();
            kicker.kickerStop();
            intake.lowerArm();
            intake.rollerFeed();
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        // Stops all the motors and raises the arms.
        intake.rollerStop();
        kicker.kickerStop();
        uptake.uptakeStop();
        intake.raiseArm();
        end.schedule();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return kicker.getBallSensor() && !(intake.currentColor() == ColorResult.NONE);
    }
}
