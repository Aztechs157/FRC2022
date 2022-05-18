// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.hanging;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.HangingConstants;
import frc.robot.input.Keys;
import frc.robot.input.OperatorInputs;
import java.lang.Math;

public class TeleopHang extends CommandBase {
    private final OperatorInputs controller;
    private final Hanging hanging;

    private final double P = 0.05;

    /** Creates a new TeleopExtend. */
    public TeleopHang(final OperatorInputs controller, final Hanging hanging) {
        this.controller = controller;
        this.hanging = hanging;
        addRequirements(hanging);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        var rotateSpeed = controller.axis(Keys.Axis.RotateSpeed).get();
        // final var rightRotatePosition = hanging.getRightRotationPosition();
        // final var leftRotatePosition = hanging.getLeftRotationPosition();
        // if (rotateSpeed > 0) {
        // rotateSpeed = rotateSpeed * Math.max(Math.min(rightRotatePosition -
        // HangingConstants.ROTATE_MAX_POS_RIGHT,
        // leftRotatePosition - HangingConstants.ROTATE_MAX_POS_LEFT) * P, rotateSpeed);
        // }
        // else {
        // //rotateSpeed =
        // }
        hanging.rotateArms(rotateSpeed);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
