package frc.robot.input;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.lib.input.Layout;
import frc.robot.lib.input.ShuffleLayoutChooser;
import frc.robot.lib.input.models.LogitechExtreme3D;
import frc.robot.lib.input.models.LogitechGamepadF310;
import frc.robot.lib.util.DoubleRange;
import frc.robot.lib.util.ShuffleUtil;

public class DriverInputs extends ShuffleLayoutChooser {

    public DriverInputs() {
        final var tab = Shuffleboard.getTab("Debug");
        final var driveInputScale = ShuffleUtil.numberInput(tab, "Drive Input Scale", 0);

        final var defaultLayout = add(new Layout("Default"));
        final var logitech = new LogitechGamepadF310(0);

        defaultLayout.assign(Keys.Axis.DriveSpeedX,
                logitech.leftStickX.scaled(driveInputScale));
        defaultLayout.assign(Keys.Axis.DriveSpeedY,
                logitech.leftStickY.scaled(driveInputScale).inverted());
        defaultLayout.assign(Keys.Axis.DriveRotation,
                logitech.rightStickX.scaled(driveInputScale));

        defaultLayout.assign(Keys.Button.Hello, logitech.a);

        final var flightLayout = add(new Layout("Flight Stick"));
        final var flight = new LogitechExtreme3D(1);

        flightLayout.assign(Keys.Axis.DriveSpeedX, flight.stickX.scaled(driveInputScale));
        flightLayout.assign(Keys.Axis.DriveSpeedY, flight.stickY.scaled(driveInputScale).inverted());
        flightLayout.assign(Keys.Axis.DriveRotation,
                flight.stickRotate.deadzone(new DoubleRange(0, 0.15)).scaled(driveInputScale));

        flightLayout.assign(Keys.Button.Hello, flight.thumbButton);

        tab.add("Drive", this);
        tab.add("Default Layout", defaultLayout);
        tab.add("Flight Layout", flightLayout);
    }
}
