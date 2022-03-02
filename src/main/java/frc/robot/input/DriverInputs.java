package frc.robot.input;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.lib.input.Layout;
import frc.robot.lib.input.LayoutChooser;
import frc.robot.lib.input.models.LogitechExtreme3D;
import frc.robot.lib.input.models.LogitechGamepadF310;
import frc.robot.lib.util.DoubleRange;
import frc.robot.lib.util.ShuffleUtil;

public class DriverInputs extends LayoutChooser {

    public DriverInputs() {
        final var tab = Shuffleboard.getTab("Debug");
        final var driveInputScale = ShuffleUtil.numberInput(tab, "Drive Input Scale", 0);

        final var defaultLayout = add(new Layout("Default"));
        final var logitech = new LogitechGamepadF310(0);

        defaultLayout.assign(AxisKey.DriveSpeedX,
                logitech.leftStickX.scaled(driveInputScale));
        defaultLayout.assign(AxisKey.DriveSpeedY,
                logitech.leftStickY.scaled(driveInputScale).inverted());
        defaultLayout.assign(AxisKey.DriveRotation,
                logitech.rightStickX.scaled(driveInputScale));

        defaultLayout.assign(ButtonKey.Hello, logitech.a);

        final var flightLayout = add(new Layout("Flight Stick"));
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