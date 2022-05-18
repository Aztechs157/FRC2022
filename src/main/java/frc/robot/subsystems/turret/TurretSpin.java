// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.TurretConstants;
import frc.robot.input.Keys;
import frc.robot.input.OperatorInputs;

public class TurretSpin extends CommandBase {
    private Turret turret;
    private OperatorInputs operatorController;

    /** Creates a new TurretSpin. */
    public TurretSpin(OperatorInputs operatorController, Turret turret) {
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
        final var turretRotation = operatorController.axis(Keys.Axis.TurretSpeed).get();
        turret.turretTurn(turretRotation * TurretConstants.TURRET_SPEED);
        // final var aimerRotation =
        // operatorController.axis(Keys.Axis.AimerSpeed).get();
        // turret.runAimer(aimerRotation * TurretConstants.AIMER_SPEED);
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
