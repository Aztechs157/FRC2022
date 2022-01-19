// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.controls.AxisKey;
import frc.robot.controls.OperatorController;

public class TeleopTurret extends CommandBase {
    private final OperatorController operatorController;
    private final TurretSubsystem turretSubsystem;

    /** Creates a new TeleopTurret. */
    public TeleopTurret(final OperatorController operatorController, final TurretSubsystem turretSubsystem) {
        this.operatorController = operatorController;
        this.turretSubsystem = turretSubsystem;

        addRequirements(turretSubsystem);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        var speed = operatorController.getAxis(AxisKey.TurnTurret);
        turretSubsystem.setSpeed(speed);
    }
}
