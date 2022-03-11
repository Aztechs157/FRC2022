// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.hanging;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.input.Keys;
import frc.robot.input.OperatorInputs;

public class TeleopHang extends CommandBase {
    private final OperatorInputs controller;
    private final Hanging hanging;

    /** Creates a new TeleopExtend. */
    public TeleopHang(final OperatorInputs controller, final Hanging hanging) {
        this.controller = controller;
        this.hanging = hanging;
        addRequirements(hanging);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        final var rotateSpeed = controller.axis(Keys.Axis.RotateSpeed).get();
        hanging.rotateArms(rotateSpeed);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
