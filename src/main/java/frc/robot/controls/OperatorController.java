package frc.robot.controls;

import frc.robot.lib.controls.ControllerBase;
import frc.robot.lib.controls.models.LogitechGamepadF310;

public class OperatorController extends ControllerBase {

    public OperatorController() {
        final var defaultLayout = new Layout("Default");
        final var logitech = new LogitechGamepadF310(0);

        defaultLayout.assignButton(Button.Hello, logitech.a);
    }
}
