// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.kicker.Kicker;
import frc.robot.subsystems.shooter.ShootCargo;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.uptake.Uptake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AutoLowShootDrive extends SequentialCommandGroup {
    /** Creates a new AutoLowShootDrive. */
    public AutoLowShootDrive(Shooter shooter, Kicker kicker, Uptake uptake, Intake intake, Drive drive) {
        // Add your commands in the addCommands() call, e.g.
        // addCommands(new FooCommand(), new BarCommand());
        addCommands(
                race(new ShootCargo(shooter, kicker, uptake, intake, ShooterConstants.SHOOTER_RPM),
                        new WaitCommand(7)),
                new DriveBackwards(drive, AutoConstants.AUTO_DISTANCE_TICKS * 2));
    }
}
