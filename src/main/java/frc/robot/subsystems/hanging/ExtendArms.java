// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.hanging;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.HangingConstants;

public class ExtendArms extends CommandBase {
    private final Hanging hanging;
    private final double targetDistance;

    /** Creates a new ExtendArms. */
    public ExtendArms(final Hanging hanging, final double targetDistance) {
        this.hanging = hanging;
        this.targetDistance = targetDistance;
        addRequirements(hanging);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        hanging.extendArms(HangingConstants.EXTEND_ERROR_MARGIN);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(final boolean interrupted) {
        hanging.extendArms(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        final var difference = targetDistance - hanging.getExtensionPosition();
        return Math.abs(difference) < HangingConstants.EXTEND_ERROR_MARGIN;
    }
}
