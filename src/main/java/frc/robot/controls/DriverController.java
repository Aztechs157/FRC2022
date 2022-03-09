package frc.robot.controls;

import static frc.robot.Constants.DriveConstants.DRIVE_INPUT_SCALE;
import frc.robot.lib.controls.ControllerBase;
import frc.robot.lib.controls.models.LogitechGamepadF310;

public class DriverController extends ControllerBase<ButtonKey, AxisKey> {

    public DriverController() {
        final var defaultLayout = new Layout("Default");
        final var logitech = new LogitechGamepadF310(0);

        // defaultLayout.assign(AxisKey.DriveSpeedX,
        // logitech.leftStickX.scaled(DRIVE_INPUT_SCALE));
        // defaultLayout.assign(AxisKey.DriveSpeedY,
        // logitech.leftStickY.scaled(DRIVE_INPUT_SCALE).inverted());
        // defaultLayout.assign(AxisKey.DriveRotation,
        // logitech.rightStickX.scaled(DRIVE_INPUT_SCALE));

        defaultLayout.assign(AxisKey.DriveSpeedX, () -> 0);
        defaultLayout.assign(AxisKey.DriveSpeedY, () -> 0);
        defaultLayout.assign(AxisKey.DriveRotation, () -> 0);

        defaultLayout.assign(ButtonKey.Hello, logitech.back);
        defaultLayout.assign(ButtonKey.IntakeRun, logitech.rightBumper);
        defaultLayout.assign(ButtonKey.LowShoot, logitech.start);
        defaultLayout.assign(ButtonKey.Hang, logitech.b);
    }
}
