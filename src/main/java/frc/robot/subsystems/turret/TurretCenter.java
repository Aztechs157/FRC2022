// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.TurretConstants;

import static frc.robot.Constants.TurretConstants.TURRET_CENTER_POS;

public class TurretCenter extends CommandBase {
    private final Turret turret;

    /** Creates a new TurretCenter. */
    public TurretCenter(final Turret turret) {
        this.turret = turret;
        addRequirements(turret);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        final var pos = turret.readPositionSensor();

        if (pos > TURRET_CENTER_POS.high) {
            // Right
            turret.unsafeTurretTurn(-TurretConstants.TURRET_SPEED / 5);
        } else if (pos < TURRET_CENTER_POS.low) {
            // Left
            turret.unsafeTurretTurn(TurretConstants.TURRET_SPEED / 5);
        }
    }

    @Override
    public boolean isFinished() {
        final var pos = turret.readPositionSensor();
        return TURRET_CENTER_POS.contains(pos);
    }

    @Override
    public void end(boolean interrupted) {
        turret.turretStop();
    }
}
