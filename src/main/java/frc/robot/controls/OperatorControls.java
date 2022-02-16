package frc.robot.controls;

import frc.robot.lib.controls.LayoutChooser;
import frc.robot.lib.controls.models.LogitechGamepadF310;

public class OperatorControls extends LayoutChooser {

    public OperatorControls() {
        final var defaultLayout = createLayout("Default");
        final var logitech = new LogitechGamepadF310(0);

        defaultLayout.assign(Button.Hello, logitech.a);
    }
}
