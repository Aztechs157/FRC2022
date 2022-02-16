package frc.robot.controls;

import frc.robot.lib.controls.Controller;
import frc.robot.lib.controls.models.LogitechGamepadF310;

public class OperatorController extends Controller {

    public OperatorController() {
        final var defaultLayout = createLayout("Default");
        final var logitech = new LogitechGamepadF310(0);

        defaultLayout.assign(Button.Hello, logitech.a);
    }
}
