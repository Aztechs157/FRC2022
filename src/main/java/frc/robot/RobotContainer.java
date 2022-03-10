// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import static frc.robot.Constants.ShooterConstants.SHOOTER_RPM;

import frc.robot.Constants.AutoConstants;
import frc.robot.input.DriverInputs;
import frc.robot.input.Keys;
import frc.robot.input.OperatorInputs;
import frc.robot.subsystems.drive.AutoLowShootDrive;
import frc.robot.subsystems.drive.AutoShootAndDrive;
import frc.robot.subsystems.drive.Drive;
import frc.robot.subsystems.drive.FindCargo;
import frc.robot.subsystems.drive.SmartCargoAndShoot;
import frc.robot.subsystems.drive.TeleopDrive;
import frc.robot.subsystems.drive.Turn180;
import frc.robot.subsystems.hanging.ExtendArms;
import frc.robot.subsystems.hanging.Hang;
import frc.robot.subsystems.hanging.Hanging;
import frc.robot.subsystems.hanging.RetractArms;
import frc.robot.subsystems.hanging.TeleopHang;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.IntakeCargo;
import frc.robot.subsystems.kicker.Kicker;
import frc.robot.subsystems.pneumatics.Pneumatics;
import frc.robot.subsystems.sensing.EjectCargo;
import frc.robot.subsystems.sensing.GetKickerColor;
import frc.robot.subsystems.shooter.LowShoot;
import frc.robot.subsystems.shooter.ShootCargo;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.turret.Turret;
import frc.robot.subsystems.uptake.Uptake;
import frc.robot.subsystems.vision.AimTurret;
import frc.robot.subsystems.vision.Vision;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    private final DriverInputs driverInputs = new DriverInputs();
    private final OperatorInputs operatorInputs = new OperatorInputs();

    // The robot's subsystems and commands are defined here...
    private final Vision visionSubsystem = new Vision();
    public final Kicker kicker = new Kicker();
    public final Intake intake = new Intake();
    public final Uptake uptake = new Uptake(kicker, intake);
    private final Shooter shooter = new Shooter();
    private final Turret turret = new Turret(operatorInputs);
    @SuppressWarnings("unused")
    private final Pneumatics pneumatics = new Pneumatics();
    private final Drive driveSubsystem = new Drive();
    private final Hanging hanging = new Hanging();
    private Command getKickerColor = new GetKickerColor(kicker, intake, uptake);

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();
        setupAutoChooser();
        Shuffleboard.getTab("Debug").add(autoSelector);
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
        driveSubsystem.setDefaultCommand(new TeleopDrive(driverInputs, driveSubsystem));
        hanging.setDefaultCommand(new TeleopHang(operatorInputs, hanging));

        // prints debug messages
        // operatorInputs.button(Keys.Button.DebugPrint)
        // .whenPressed(new DebugPrints(intake, kicker, uptake, shooter, turret));

        // runs the intake command on the driver controller
        driverInputs.button(Keys.Button.IntakeRun)
                .toggleWhenPressed(new IntakeCargo(intake, uptake, kicker));

        // runs the Shooter command on the operator controller
        operatorInputs.button(Keys.Button.ShooterRun)
                .whileHeld(new ShootCargo(shooter, kicker, uptake, SHOOTER_RPM));

        // runs the Eject command on the driver controller
        driverInputs.button(Keys.Button.LowShoot)
                .whileHeld(new LowShoot(shooter, kicker, uptake, intake));

        // while held will track the reflective tape
        operatorInputs.button(Keys.Button.AutoAim)
                .whileHeld(new AimTurret(visionSubsystem, turret, shooter, kicker, uptake));

        // shoots cargo at the lower rpm
        operatorInputs.button(Keys.Button.LowShoot)
                .whileHeld(new LowShoot(shooter, kicker, uptake, intake));

        // runs the Eject command on the operator controller
        operatorInputs.button(Keys.Button.EjectCargo)
                .whileHeld(new EjectCargo(intake, uptake, kicker, shooter));

        // tracks cargo while held
        operatorInputs.button(Keys.Button.TrackCargo)
                .whileHeld(new FindCargo(visionSubsystem, driveSubsystem));

        // will turn until degrees has been fully met
        operatorInputs.button(Keys.Button.autoTest)
                .whenPressed(new Turn180(driveSubsystem, AutoConstants.TURN_DEGREES));

        // will hang eventually
        driverInputs.button(Keys.Button.Hang).whenPressed(new Hang(hanging));

        operatorInputs.button(Keys.Button.ExtendHanger).whileHeld(new ExtendArms(hanging));
        operatorInputs.button(Keys.Button.RetractHanger).whileHeld(new RetractArms(hanging));
    }

    // turns on break mode for the drive motors
    public void enableDriveBreakMode() {
        driveSubsystem.enableBrakeMode();
    }

    // turns off break mode for drive motors
    public void disableBreakMode() {
        driveSubsystem.disableBrakeMode();
    }

    // creates a sendable chooser for auto, default option is shoot low and drive
    // backwards
    private final SendableChooser<Command> autoSelector = new SendableChooser<>();

    private void setupAutoChooser() {
        autoSelector.setDefaultOption("Shoot Low Drive Backward",
                new AutoLowShootDrive(shooter, kicker, uptake, intake, driveSubsystem));
        autoSelector.addOption("Drive Backward Shoot High",
                new AutoShootAndDrive(visionSubsystem, turret, shooter, kicker, uptake, driveSubsystem));
        autoSelector.addOption("Shoot Low Find Cargo",
                new SmartCargoAndShoot(shooter, kicker, uptake, driveSubsystem, visionSubsystem, intake));
    }

    /**
     *
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return autoSelector.getSelected();
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
