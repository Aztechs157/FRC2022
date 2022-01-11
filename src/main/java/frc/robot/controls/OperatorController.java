package frc.robot.controls;

import frc.robot.lib.controls.ControllerBase;
import frc.robot.controls.models.LogitechModel;

public class OperatorController extends ControllerBase<ButtonKey, AxisKey> {

    public OperatorController() {
        var defaultLayout = new Layout("Default");
        var logitech = new LogitechModel(0);

        defaultLayout.assign(ButtonKey.Hello, logitech.a);
    }
}
