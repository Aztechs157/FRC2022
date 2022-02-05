// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.kicker.Kicker;
import frc.robot.subsystems.uptake.Uptake;

public class ShootCargo extends CommandBase {
    private Shooter shooter;
    private Kicker kicker;
    private Uptake uptake;
    private double targetSpeed;

    /** Creates a new SetShooterSpeed. */
    public ShootCargo(Shooter shooter, Kicker kicker, Uptake uptake, double targetSpeed) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.shooter = shooter;
        this.kicker = kicker;
        this.uptake = uptake;
        this.targetSpeed = targetSpeed;
        addRequirements(shooter);
        addRequirements(kicker);
        addRequirements(uptake);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        var currSpeed = shooter.measureVelocity();
        shooter.setPower(shooter.pidCalculate(targetSpeed, currSpeed));
        if (currSpeed > targetSpeed - 0.05 && currSpeed < targetSpeed + 0.05) {
            kicker.kickerFeed();
            uptake.uptakeFeed();
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        // Stops the shooter from spinning
        shooter.setPower(0);
        kicker.kickerStop();
        uptake.uptakeStop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
