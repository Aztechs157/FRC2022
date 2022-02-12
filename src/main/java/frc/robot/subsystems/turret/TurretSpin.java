// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.turret;

import edu.wpi.first.wpilibj.PS4Controller.Axis;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.controls.AxisKey;
import frc.robot.controls.OperatorController;

public class TurretSpin extends CommandBase {
    private Turret turret;
    private OperatorController operatorController;

    /** Creates a new TurretSpin. */
    public TurretSpin(OperatorController operatorController, Turret turret) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.operatorController = operatorController;
        this.turret = turret;
        addRequirements(turret);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // rotates the turret based on joystick input
        final var turretRotation = operatorController.getAxis(AxisKey.TurretSpeed);
        turret.turretTurn(turretRotation);
        final var aimerRotation = operatorController.getAxis(AxisKey.AimerSpeed);
        turret.runAimer(aimerRotation);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        // stops the turret
        turret.turretStop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
