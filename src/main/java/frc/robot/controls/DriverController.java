package frc.robot.controls;

import frc.robot.lib.controls.ControllerBase;
import frc.robot.controls.models.LogitechModel;
import frc.robot.controls.DriverController.ButtonKey;
import frc.robot.controls.DriverController.AxisKey;

public class DriverController extends ControllerBase<ButtonKey, AxisKey> {

    public DriverController() {
        var defaultLayout = new Layout("Default");
        var logitech = new LogitechModel(0);

        defaultLayout.assign(ButtonKey.Hello, logitech.a);
    }

    public static enum ButtonKey {
        Hello
    }

    public static enum AxisKey {
    }
}
