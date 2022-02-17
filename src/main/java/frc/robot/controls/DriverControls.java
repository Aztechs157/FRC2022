package frc.robot.controls;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.lib.DoubleRange;
import frc.robot.lib.controls.LayoutChooser;
import frc.robot.lib.controls.models.LogitechExtreme3D;
import frc.robot.lib.controls.models.LogitechGamepadF310;

public class DriverControls extends LayoutChooser {

    public DriverControls() {
        final var tab = Shuffleboard.getTab("Debug");

        final var scaleEntry = tab.add("Drive Input Scale", 0).getEntry();
        final DoubleSupplier driveInputScale = () -> scaleEntry.getDouble(0);

        final var defaultLayout = createLayout("Default");
        final var logitech = new LogitechGamepadF310(0);

        defaultLayout.assign(AxisKey.DriveSpeedX,
                logitech.leftStickX.scaled(driveInputScale));
        defaultLayout.assign(AxisKey.DriveSpeedY,
                logitech.leftStickY.scaled(driveInputScale).inverted());
        defaultLayout.assign(AxisKey.DriveRotation,
                logitech.rightStickX.scaled(driveInputScale));

        defaultLayout.assign(ButtonKey.Hello, logitech.a);

        final var flightLayout = createLayout("Flight Stick");
        final var flight = new LogitechExtreme3D(1);

        flightLayout.assign(AxisKey.DriveSpeedX, flight.stickX.scaled(driveInputScale));
        flightLayout.assign(AxisKey.DriveSpeedY, flight.stickY.scaled(driveInputScale).inverted());
        flightLayout.assign(AxisKey.DriveRotation,
                flight.stickRotate.deadzone(new DoubleRange(0, 0.15)).scaled(driveInputScale));

        flightLayout.assign(ButtonKey.Hello, flight.thumbButton);

        tab.add("Drive", this);
        tab.add("Default Layout", defaultLayout);
        tab.add("Flight Layout", flightLayout);
    }
}
