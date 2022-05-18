// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.AutoConstants;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.kicker.Kicker;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.turret.Turret;
import frc.robot.subsystems.uptake.Uptake;
import frc.robot.subsystems.vision.AimTurret;
import frc.robot.subsystems.vision.Vision;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class MultiBallAuto extends SequentialCommandGroup {
    /** Creates a new MultiBallAuto. */
    public MultiBallAuto(Shooter shooter, Kicker kicker, Uptake uptake, Drive drive,
            Vision vision, Intake intake, Turret turret) {
        // Add your commands in the addCommands() call, e.g.
        // addCommands(new FooCommand(), new BarCommand());

        final var driveForwardToBall = new DriveForwards(drive, AutoConstants.AUTO_DISTANCE_TICKS * 1.5);
        final var driveForwardToTerminal = new DriveForwards(drive, AutoConstants.AUTO_DISTANCE_TICKS * 5);
        final var driveForwardToGoal = new DriveForwards(drive, AutoConstants.AUTO_DISTANCE_TICKS * 4);
        final var intakeAutomatically = new IntakeAutomatically(intake);
        final var intakeAutomatically2 = new IntakeAutomatically(intake);
        final var turnToTerminal = new Turn180(drive, -AutoConstants.TURN_DEGREES);
        final var turnToGoal = new Turn180(drive, AutoConstants.TURN_DEGREES);
        final var turnToGoal2 = new Turn180(drive, AutoConstants.TURN_DEGREES);
        final var shootHigh = new AimTurret(vision, turret, shooter, kicker, uptake, intake);
        final var shootHigh2 = new AimTurret(vision, turret, shooter, kicker, uptake, intake);

        addCommands(
                driveForwardToBall.alongWith(intakeAutomatically).raceWith(new WaitCommand(2.5)),
                turnToGoal,
                shootHigh.raceWith(new WaitCommand(3.5)),
                turnToTerminal,
                driveForwardToTerminal.alongWith(intakeAutomatically2).raceWith(new WaitCommand(4.5)),
                turnToGoal2,
                driveForwardToGoal,
                shootHigh2.raceWith(new WaitCommand(3.5)));
    }
}
