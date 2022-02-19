package frc.robot.controls;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.lib.DoubleRange;
import frc.robot.lib.controls.ControllerBase;
import frc.robot.lib.controls.models.LogitechExtreme3D;
import frc.robot.lib.controls.models.LogitechGamepadF310;

public class DriverController extends ControllerBase<ButtonKey, AxisKey> {

    public DriverController() {
        final var tab = Shuffleboard.getTab("Debug");

        final double driveInputScale = .65;

        final var defaultLayout = new Layout("Default");
        final var logitech = new LogitechGamepadF310(0);

        defaultLayout.assign(AxisKey.DriveSpeedX,
                logitech.leftStickX.scaled(driveInputScale));
        defaultLayout.assign(AxisKey.DriveSpeedY,
                logitech.leftStickY.scaled(driveInputScale).inverted());
        defaultLayout.assign(AxisKey.DriveRotation,
                logitech.rightStickX.scaled(driveInputScale));

        defaultLayout.assign(ButtonKey.Hello, logitech.back);
        defaultLayout.assign(ButtonKey.IntakeRun, logitech.rightBumper);
        defaultLayout.assign(ButtonKey.emergencyEject, logitech.start);

        // final var flightLayout = new Layout("Flight Stick");
        // final var flight = new LogitechExtreme3D(1);

        // flightLayout.assign(AxisKey.DriveSpeedX,
        // flight.stickX.scaled(driveInputScale));
        // flightLayout.assign(AxisKey.DriveSpeedY,
        // flight.stickY.scaled(driveInputScale).inverted());
        // flightLayout.assign(AxisKey.DriveRotation,
        // flight.stickRotate.deadzone(new DoubleRange(0,
        // 0.15)).scaled(driveInputScale));
        // flightLayout.assign(ButtonKey.UptakeRun, flight.button3);
        // flightLayout.assign(ButtonKey.KickerRun, flight.button3);
        // flightLayout.assign(ButtonKey.ShooterRun, flight.button4);
        // flightLayout.assign(ButtonKey.IntakeRun, flight.button5);

        // flightLayout.assign(ButtonKey.Hello, flight.thumbButton);

        tab.add("Drive", this);
        tab.add("Default Layout", defaultLayout);
        // tab.add("Flight Layout", flightLayout);
    }
}
