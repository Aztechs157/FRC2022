// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.controls.ButtonKey;
import frc.robot.controls.DriverController;
import frc.robot.controls.OperatorController;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.kicker.Kicker;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.uptake.RunUptake;
import frc.robot.subsystems.uptake.Uptake;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    private final DriverController driverController = new DriverController();
    private final OperatorController operatorController = new OperatorController();

    // The robot's subsystems and commands are defined here...
    private final Uptake uptake = new Uptake();
    private final Kicker kicker = new Kicker();
    private final Intake intake = new Intake();
    private final Shooter shooter = new Shooter();

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
     * it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        // Jame's button test code, prints out Hello when A is pressed
        driverController.button(ButtonKey.Hello)
                .whenPressed(() -> System.out.println("Hello driver"));

        operatorController.button(ButtonKey.Hello)
                .whenPressed(() -> System.out.println("Hello operator"));

        // Jame's Button code but I, Eric, Used it for my own purposes.
        // X Button runs Uptake and Kicker, this line specifically runs uptakeFeed when
        // the x button is held
        driverController.button(ButtonKey.UptakeRun)
                .whenPressed(uptake::uptakeFeed);

        // runs uptakeStop when the x button is released
        driverController.button(ButtonKey.UptakeRun)
                .whenReleased(uptake::uptakeStop);

        // runs kickerFeed when the x button is held
        driverController.button(ButtonKey.KickerRun)
                .whenPressed(kicker::kickerFeed);

        // runs kickerStop when the x button is released
        driverController.button(ButtonKey.KickerRun)
                .whenReleased(kicker::kickerStop);

        // Y Button runs the Intake, this line runs rollerFeed when the y button is held
        driverController.button(ButtonKey.IntakeRun)
                .whenPressed(intake::rollerFeed);

        // runs rollerStop when the y button is released
        driverController.button(ButtonKey.IntakeRun)
                .whenReleased(intake::rollerStop);

        // B Button runs the Shooter, this line runs ejectTop when the b button is held
        driverController.button(ButtonKey.ShooterRun)
                .whenPressed(shooter::ejectTop);

        // runs ejectStop when the b button is held
        driverController.button(ButtonKey.ShooterRun)
                .whenReleased(shooter::ejectStop);
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return null;
    }
}
