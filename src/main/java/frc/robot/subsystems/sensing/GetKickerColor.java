// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.sensing;

import javax.xml.crypto.dsig.TransformService;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.kicker.Kicker;
import frc.robot.subsystems.uptake.Uptake;
import frc.robot.subsystems.intake.Intake.ColorResult;
import frc.robot.Constants.MotorDirection;

public class GetKickerColor extends CommandBase {
    private Kicker kicker;
    private Intake intake;
    private Uptake uptake;
    private ColorResult intakeColor;
    private ColorResult transitionalColor;
    private ColorResult kickerColor;

    /** Creates a new GetKickerColor. */
    public GetKickerColor(Kicker kicker, Intake intake, Uptake uptake) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.kicker = kicker;
        this.intake = intake;
        this.uptake = uptake;

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        /**
         * if ball in intake sensor, record ball color and position
         * - concurrent group 1
         *
         * if ball has left the intake sensor away from the kicker, eject the ball,
         * discard color - concurrent group 1
         *
         * if ball has left the intake sensor towards the kicker, record ball's
         * location as transitional - concurrent group 1
         *
         * if ball has arrived at kicker sensor, tell kicker the color of the ball that
         * is in transition, record ball location as kicker - concurrent group 2
         *
         * if ball has left the kicker sensor, discard color - concurrent group 2
         *
         * (if ball left the robot, ignore, if ball went back into intake, restart the
         * cycle.)
         */
        final ColorResult currentColor = intake.currentColor();
        final boolean ballSense = kicker.getBallSensor();

        if (currentColor != ColorResult.NONE) {
            intakeColor = currentColor;
        } else if (intakeColor != ColorResult.NONE) {
            if (uptake.getDirection() == MotorDirection.FEEDING) {
                transitionalColor = intakeColor;
                intakeColor = ColorResult.NONE;
            } else {
                intakeColor = ColorResult.NONE;
            }
        }

        if (ballSense && kickerColor == ColorResult.NONE) {
            kickerColor = transitionalColor;
            kicker.setBallColor(kickerColor);
            transitionalColor = ColorResult.NONE;
        } else if (!ballSense && kickerColor != ColorResult.NONE) {
            kickerColor = ColorResult.NONE;
            kicker.setBallColor(ColorResult.NONE);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
