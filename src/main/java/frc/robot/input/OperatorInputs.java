package frc.robot.input;

import frc.robot.lib.input.Layout;
import frc.robot.lib.input.ShuffleLayoutChooser;
import frc.robot.lib.input.models.LogitechGamepadF310;

public class OperatorInputs extends ShuffleLayoutChooser {

    public OperatorInputs() {
        final var defaultLayout = add(new Layout("Default"));
        final var logitech = new LogitechGamepadF310(0);

        defaultLayout.assign(Keys.Button.Hello, logitech.a);
    }
}
