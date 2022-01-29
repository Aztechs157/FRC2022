package frc.robot.controls;

import frc.robot.lib.controls.ControllerBase;
import frc.robot.controls.models.LogitechModel;

public class DriverController extends ControllerBase<ButtonKey, AxisKey> {

    public DriverController() {
        var defaultLayout = new Layout("Default");
        var logitech = new LogitechModel(0);

        defaultLayout.assign(ButtonKey.Hello, logitech.a);
        defaultLayout.assign(ButtonKey.UptakeRun, logitech.x);
        defaultLayout.assign(ButtonKey.KickerRun, logitech.x);
        defaultLayout.assign(ButtonKey.IntakeRun, logitech.y);
        defaultLayout.assign(ButtonKey.ShooterRun, logitech.b);

        addDefault(defaultLayout);
    }
}
