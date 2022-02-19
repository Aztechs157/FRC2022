// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.controls;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.drive.DriveBackwards;
import frc.robot.drive.DriveSubsystem;
import frc.robot.subsystems.kicker.Kicker;
import frc.robot.subsystems.shooter.ShootCargo;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.turret.Turret;
import frc.robot.subsystems.uptake.Uptake;
import frc.robot.vision.AimTurret;
import frc.robot.vision.VisionSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoShootAndDrive extends SequentialCommandGroup {
    /** Creates a new AutoShootAndDrive. */
    public AutoShootAndDrive(
            final VisionSubsystem vision,
            final Turret turret,
            final Shooter shooter,
            final Kicker kicker,
            final Uptake uptake,
            final DriveSubsystem drive) {

        final var autoAim = new ShootCargo(shooter, kicker, uptake, 4300);
        final var driveForward = new DriveBackwards(drive);
        final var timeout = new WaitCommand(7);

        addCommands(race(timeout, autoAim));
        addCommands(driveForward);
    }
}
