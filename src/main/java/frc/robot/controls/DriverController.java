package frc.robot.controls;

import frc.robot.lib.controls.ControllerBase;
import frc.robot.controls.models.LogitechGamepadF310;
import frc.robot.controls.models.LogitechExtreme3D;

public class DriverController extends ControllerBase<ButtonKey, AxisKey> {

    public DriverController() {
        final var driveInputScale = 0.3;

        final var defaultLayout = new Layout("Default");
        final var logitech = new LogitechGamepadF310(0);

        defaultLayout.assign(AxisKey.DriveSpeedX,
                logitech.leftStickX.scaled(driveInputScale));
        defaultLayout.assign(AxisKey.DriveSpeedY,
                logitech.leftStickY.scaled(driveInputScale).inverted());
        defaultLayout.assign(AxisKey.DriveRotation,
                logitech.rightStickX.scaled(driveInputScale));

        defaultLayout.assign(ButtonKey.Hello, logitech.a);

        final var flightLayout = new Layout("Flight Stick");
        final var flight = new LogitechExtreme3D(1);

        flightLayout.assign(AxisKey.DriveSpeedX, flight.stickX.scaled(driveInputScale));
        flightLayout.assign(AxisKey.DriveSpeedY, flight.stickY.scaled(driveInputScale).inverted());
        flightLayout.assign(AxisKey.DriveRotation, flight.stickRotate.scaled(driveInputScale));
    }
}
