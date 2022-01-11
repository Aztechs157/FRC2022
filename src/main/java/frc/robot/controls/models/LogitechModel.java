package frc.robot.controls.models;

import frc.robot.lib.controls.ButtonInput;
import frc.robot.lib.controls.ModelBase;
import frc.robot.lib.controls.AxisInput;

public class LogitechModel extends ModelBase {

    public LogitechModel(final int joystickId) {
        super(joystickId);
    }

    public final ButtonInput a = button(1);
    public final ButtonInput b = button(2);
    public final ButtonInput x = button(3);
    public final ButtonInput y = button(4);

    public final ButtonInput leftBumper = button(5);
    public final ButtonInput rightBumper = button(6);

    public final ButtonInput back = button(7);
    public final ButtonInput start = button(8);

    public final ButtonInput leftStickPress = button(9);
    public final ButtonInput rightStickPress = button(10);

    public final AxisInput leftStickX = axis(0);
    public final AxisInput leftStickY = axis(1);

    public final AxisInput rightTriggerHeld = axis(2);
    public final AxisInput leftTriggerHeld = axis(3);

    public final AxisInput rightStickX = axis(4);
    public final AxisInput rightStickY = axis(5);
}
