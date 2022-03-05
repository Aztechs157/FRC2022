// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.kicker.Kicker;

public class IntakeAutomatically extends CommandBase {
    private Intake intake;
    private Kicker kicker;

    /** Creates a new IntakeAutomatically. */
    public IntakeAutomatically(Intake intake, Kicker kicker) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.intake = intake;
        this.kicker = kicker;
        addRequirements(intake);
        addRequirements(kicker);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        intake.lowerArm();
        intake.rollerFeed();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        intake.rollerStop();
        intake.raiseArm();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
