package frc.robot.controls;

import frc.robot.lib.controls.ControllerBase;
import frc.robot.controls.models.LogitechModel;

public class DriverController extends ControllerBase<ButtonKey, AxisKey> {

    public DriverController() {
        var defaultLayout = new Layout("Default");
        var logitech = new LogitechModel(0);

        defaultLayout.assign(AxisKey.DriveSpeedX, logitech.leftStickX);
        defaultLayout.assign(AxisKey.DriveSpeedY, logitech.leftStickY);
        defaultLayout.assign(AxisKey.DriveRotation, logitech.rightStickX);
    }
}
