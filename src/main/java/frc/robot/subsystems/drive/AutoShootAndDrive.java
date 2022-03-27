// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import static frc.robot.Constants.ShooterConstants.SHOOTER_RPM;

import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.kicker.Kicker;
import frc.robot.subsystems.shooter.ShootCargo;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.turret.Turret;
import frc.robot.subsystems.uptake.Uptake;
import frc.robot.subsystems.vision.AimTurret;
import frc.robot.subsystems.vision.Vision;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoShootAndDrive extends SequentialCommandGroup {
    /** Creates a new AutoShootAndDrive. */
    public AutoShootAndDrive(
            final Vision vision,
            final Turret turret,
            final Shooter shooter,
            final Kicker kicker,
            final Uptake uptake,
            final Intake intake,
            final Drive drive) {

        final var autoAim = new AimTurret(vision, turret, shooter, kicker, uptake, intake);
        final var driveBackward = new DriveBackwards(drive);

        addCommands(driveBackward, autoAim);
    }
}
