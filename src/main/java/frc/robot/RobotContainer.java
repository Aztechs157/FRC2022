// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.drive.DriveSubsystem;
import frc.robot.drive.TeleopDrive;
import frc.robot.input.DriverInputs;
import frc.robot.input.Keys;
import frc.robot.input.OperatorInputs;

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
    private final DriverInputs driverInputs = new DriverInputs();
    private final OperatorInputs operatorInputs = new OperatorInputs();

    private final DriveSubsystem driveSubsystem = new DriveSubsystem();

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
        driveSubsystem.setDefaultCommand(
                new TeleopDrive(driverInputs, driveSubsystem));

        driverInputs.button(Keys.Button.Hello)
                .whenPressed(() -> System.out.println("Hello driver"));

        operatorInputs.button(Keys.Button.Hello)
                .whenPressed(() -> System.out.println("Hello operator"));

        operatorInputs.button(Keys.DebugButton.PrintFoo)
                .whenPressed(() -> System.out.println("foo"));

        operatorInputs.button(Keys.DebugButton.PrintBar)
                .whenPressed(() -> System.out.println("bar"));
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
