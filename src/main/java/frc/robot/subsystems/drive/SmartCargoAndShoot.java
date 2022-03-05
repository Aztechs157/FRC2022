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
import frc.robot.subsystems.vision.Vision;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SmartCargoAndShoot extends SequentialCommandGroup {
    /**
     * Creates a new SmartCargoAndShoot.
     *
     * @param shooter
     * @param kicker
     * @param uptake
     * @param targetSpeed
     * @param drive
     * @param vision
     * @param intake
     */
    public SmartCargoAndShoot(Shooter shooter, Kicker kicker, Uptake uptake, Drive drive,
            Vision vision, Intake intake) {
        // Add your commands in the addCommands() call, e.g.
        // addCommands(new FooCommand(), new BarCommand());

        final var shootLow = new ShootCargo(shooter, kicker, uptake, ShooterConstants.LOW_SHOOTER_RPM);
        final var driveBackward = new DriveBackwards(drive);
        final var turnDistance = new Turn180(drive, AutoConstants.TURN_DEGREES);
        final var findCargo = new FindCargo(vision, drive);
        final var driveForward = new DriveForwards(drive, AutoConstants.AUTO_DISTANCE_TICKS);
        final var intakeAutomatically = new IntakeAutomatically(intake);
        final var turnToGoal = new Turn180(drive, -AutoConstants.TURN_DEGREES);

        addCommands(race(shootLow, new WaitCommand(3)),
                driveBackward,
                turnDistance,
                new WaitCommand(6).deadlineWith(findCargo.andThen(driveForward.alongWith(intakeAutomatically))),
                turnToGoal);
    }
}
