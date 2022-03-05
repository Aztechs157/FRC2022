// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.hanging;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.HangingConstants;

public class RotateArms extends CommandBase {
    private final Hanging hanging;
    private final double targetRotation;

    /** Creates a new RotateArms. */
    public RotateArms(final Hanging hanging, final double targetRotation) {
        this.hanging = hanging;
        this.targetRotation = targetRotation;
        addRequirements(hanging);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        hanging.rotateArms(HangingConstants.ROTATION_SPEED);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(final boolean interrupted) {
        hanging.rotateArms(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        final var difference = targetRotation - hanging.getRotationPosition();
        return Math.abs(difference) < HangingConstants.ROTATION_ERROR_MARGIN;
    }
}
