// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.kicker.Kicker;
import frc.robot.subsystems.uptake.Uptake;

public class ShootCargo extends CommandBase {
    private Shooter shooter;
    private Kicker kicker;
    private Uptake uptake;
    private Intake intake;
    private double targetSpeedFront;
    private double speedSumFront = 0;
    private double speedSumBack = 0;
    private double targetSpeedBack;
    private long count = 0;

    /** Creates a new SetShooterSpeed. */
    public ShootCargo(Shooter shooter, Kicker kicker, Uptake uptake, Intake intake, double targetSpeed,
            double rearRatio) {
        // Use addRequirements() here to declare subsystem dependencies.
        /**
         * Adds Shooter, Kicker, and Uptake as requirements and stops other systems from
         * using them while running. Also requires a targetSpeed to be set.
         */
        this.shooter = shooter;
        this.kicker = kicker;
        this.uptake = uptake;
        this.intake = intake;
        this.targetSpeedFront = targetSpeed;
        this.targetSpeedBack = targetSpeed * rearRatio * 0; // 1.4
        addRequirements(shooter);
        addRequirements(kicker);
        addRequirements(uptake);
        addRequirements(intake);
    }

    public ShootCargo(Shooter shooter, Kicker kicker, Uptake uptake, Intake intake, double targetSpeed) {
        this(shooter, kicker, uptake, intake, targetSpeed, 1);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        speedSumFront = 0;
        speedSumBack = 0;
        count = 0;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        /**
         * Measures motor velocity then sets power. Adds in tolerance for targetSpeed.
         * NOTE: tolerance not correct.
         * Then it runs the uptake and kicker in order to shoot.
         */
        var currSpeedFront = shooter.measureFrontVelocity();
        // var currSpeedBack = shooter.measureBackVelocity();
        speedSumFront += shooter.pidFrontCalculate(targetSpeedFront, currSpeedFront);
        // speedSumBack += shooter.pidBackCalculate(targetSpeedBack, currSpeedBack);
        shooter.setFrontPower(speedSumFront);
        // shooter.setBackPower(speedSumBack);
        if (currSpeedFront > targetSpeedFront - 50 && currSpeedFront < targetSpeedFront + 50
        // && currSpeedBack > targetSpeedBack - 50 && currSpeedBack < targetSpeedBack +
        // 50) {
        ) {
            count++;
            // kicker.kickerFeed();
            // uptake.uptakeFeed();
            // intake.rollerFeed();
        } else

        {
            count = 0;
            // kicker.kickerStop();
            // uptake.uptakeStop();
            // intake.rollerStop();
        }

        if (count >= 5) {
            kicker.kickerFeed();
            uptake.uptakeFeed();
            intake.rollerFeed();
        } else {
            // count = 0;
            kicker.kickerStop();
            uptake.uptakeStop();
            intake.rollerStop();
        }
        // shooter.backMotorVelocity.setDouble(currSpeedBack);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        // Stops all the motors from spinning
        shooter.setBothPower(0);
        kicker.kickerStop();
        uptake.uptakeStop();
        intake.rollerStop();
        count = 0;
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    public void SetShooterSpeed(double speed) {
        this.targetSpeedFront = speed;
    }
}
