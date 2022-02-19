// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.controls.AutoShootAndDrive;
import frc.robot.controls.ButtonKey;
import frc.robot.controls.DriverController;
import frc.robot.controls.OperatorController;
import frc.robot.vision.AimTurret;
import frc.robot.vision.VisionSubsystem;
import frc.robot.subsystems.DebugPrints;
import frc.robot.subsystems.Dump;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.IntakeCargo;
import frc.robot.subsystems.intake.IntakeSim;
import frc.robot.subsystems.kicker.Kicker;
import frc.robot.subsystems.pneumatics.Pneumatics;
import frc.robot.subsystems.sensing.EjectCargo;
import frc.robot.subsystems.sensing.GetKickerColor;
import frc.robot.subsystems.shooter.ShootCargo;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.ShooterSim;
import frc.robot.subsystems.turret.Turret;
import frc.robot.subsystems.turret.TurretSim;
import frc.robot.subsystems.uptake.Uptake;
import frc.robot.drive.DriveBackwards;
import frc.robot.drive.DriveSubsystem;
import frc.robot.drive.ResetDrivePosition;
import frc.robot.drive.TeleopDrive;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    private final DriverController driverController = new DriverController();
    private final OperatorController operatorController = new OperatorController();

    // The robot's subsystems and commands are defined here...
    private final VisionSubsystem visionSubsystem = new VisionSubsystem();
    public final Uptake uptake = new Uptake();
    public final Kicker kicker = new Kicker();
    public final Intake intake = new Intake();
    private final Shooter shooter = new Shooter();
    private final Turret turret = new Turret(operatorController);
    private final Pneumatics pneumatics = new Pneumatics();
    private final DriveSubsystem driveSubsystem = new DriveSubsystem();

    private Command getKickerColor = new GetKickerColor(kicker, intake, uptake);

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
        driveSubsystem.setDefaultCommand(new TeleopDrive(driverController, driveSubsystem));

        // Jame's button test code, I changed the prints from simple strings of hello to
        // measure velocity method printouts.
        driverController.button(ButtonKey.Hello)
                .whenPressed(() -> System.out.println(shooter.measureVelocity()));

        operatorController.button(ButtonKey.DebugPrint)
                .whenPressed(new DebugPrints(intake, kicker, uptake, shooter, turret));

        driverController.button(ButtonKey.ResetDrivePosition).whenPressed(new ResetDrivePosition(driveSubsystem));

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

        // Y button runs the intakeCargo command. This will run the motors of the
        // intake, uptake and kicker when the y button is held.
        driverController.button(ButtonKey.IntakeRun)
                .whileHeld(new IntakeCargo(intake, uptake, kicker));

        // B Button runs the Shooter, this line runs ejectTop when the b button is held
        driverController.button(ButtonKey.ShooterRun)
                .whileHeld(new ShootCargo(shooter, kicker, uptake, 3000));

        // Right Bumper runs the Ejecting, this line runs the intake, uptake, kicker,
        // and shooter based on logic related to color sensing and position sensing.
        driverController.button(ButtonKey.EjectCargo)
                .whileHeld(new EjectCargo(intake, uptake, kicker, shooter));

        operatorController.button(ButtonKey.autoAim)
                .whileHeld(new AimTurret(visionSubsystem, turret, shooter, kicker, uptake));

        operatorController.button(ButtonKey.emergencyEject)
                .whileHeld(new Dump(shooter, kicker, uptake, intake));
    }

    public void startAutonomous() {
        driveSubsystem.enableBrakeMode();
    }

    public void endAutonomous() {
        driveSubsystem.disableBrakeMode();
    }

    /**
     *
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return new AutoShootAndDrive(visionSubsystem, turret, shooter, kicker, uptake, driveSubsystem);
    }

    /**
     * This will get the kicker color sensor command
     *
     * @return kicker stored cargo color
     */
    public Command getKickerColorSensorCommand() {
        return getKickerColor;
    }
}
