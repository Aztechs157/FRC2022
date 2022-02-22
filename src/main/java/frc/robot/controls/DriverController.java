package frc.robot.controls;

import frc.robot.lib.controls.ControllerBase;
import frc.robot.lib.controls.models.LogitechGamepadF310;

public class DriverController extends ControllerBase<ButtonKey, AxisKey> {

    public DriverController() {

        final double driveInputScale = .75;

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
        defaultLayout.assign(ButtonKey.LowShoot, logitech.start);
    }
}
