// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.kicker.Kicker;
import frc.robot.subsystems.shooter.ShootCargo;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.turret.Turret;
import frc.robot.subsystems.uptake.Uptake;
import frc.robot.subsystems.vision.Vision;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TestAuto extends SequentialCommandGroup {

    /** Creates a new TestAuto. */
    public TestAuto(final Vision vision,
            final Turret turret,
            final Shooter shooter,
            final Kicker kicker,
            final Uptake uptake,
            final Intake intake,
            final Drive drive) {
        // Add your commands in the addCommands() call, e.g.
        // addCommands(new FooCommand(), new BarCommand());
        addCommands(
                race(new DriveForwards(drive, 30), new WaitCommand(5)),
                race(new Turn180(drive, 90), new WaitCommand(5)),
                race(new ShootCargo(shooter, kicker, uptake, intake, ShooterConstants.LOW_SHOOTER_RPM),
                        new WaitCommand(5))

        );

    }
}
