// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.sensing;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.Intake.ColorResult;
import frc.robot.subsystems.kicker.Kicker;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.uptake.Uptake;

public class EjectCargo extends CommandBase {
    private Intake intake;
    private Uptake uptake;
    private Kicker kicker;
    private Shooter shooter;
    private ColorResult enemyColor;

    /**
     * My plan for this command is to make an eject system that will run either all
     * reverse, all forward, or half reverse half forward based on sensor data to
     * ensure safest and quickest ejecting capabilities. Example, if two balls,
     * eject out of either end, if only 1 ball in the intake, only run the intake
     * motors to eject, don't run kicker motors.
     */

    /** Creates a new EjectCargo. */
    public EjectCargo() {
        // Use addRequirements() here to declare subsystem dependencies.
        this.intake = intake;
        this.uptake = uptake;
        this.kicker = kicker;
        this.shooter = shooter;
        addRequirements(intake);
        addRequirements(uptake);
        addRequirements(kicker);
        addRequirements(shooter);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        if (DriverStation.getAlliance() == Alliance.Blue) {
            enemyColor = ColorResult.RED;
        } else {
            enemyColor = ColorResult.BLUE;
        }

        if (intake.currentColor() == enemyColor) {
            intake.lowerArm();
            intake.rollerEject();
            uptake.uptakeEject();
        } else if (kicker.getBallColor() == enemyColor) {
            kicker.kickerFeed();
            shooter.ejectTop();
        }

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        intake.raiseArm();
        intake.rollerStop();
        uptake.uptakeStop();
        kicker.kickerStop();
        shooter.ejectStop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
