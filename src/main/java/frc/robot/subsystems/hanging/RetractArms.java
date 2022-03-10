// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.hanging;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.HangingConstants;

public class RetractArms extends CommandBase {
    private final Hanging hanging;

    /** Creates a new ExtendArms. */
    public RetractArms(final Hanging hanging) {
        this.hanging = hanging;
        addRequirements(hanging);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        hanging.extendLeftArm(HangingConstants.RETRACT_SPEED);
        hanging.extendRightArm(HangingConstants.RETRACT_SPEED);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return hanging.getBottomLeftLimit() && hanging.getBottomRightLimit();
    }
}
