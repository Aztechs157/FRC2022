package frc.robot.input;

import frc.robot.lib.input.Layout;
import frc.robot.lib.input.LayoutChooser;
import frc.robot.lib.input.models.LogitechGamepadF310;

public class OperatorControls extends LayoutChooser {

    public OperatorControls() {
        final var defaultLayout = add(new Layout("Default"));
        final var logitech = new LogitechGamepadF310(0);

        defaultLayout.assign(ButtonKey.Hello, logitech.a);
    }
}
