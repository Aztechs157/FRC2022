package frc.robot.lib.controls.models;

import frc.robot.lib.controls.Button;
import frc.robot.lib.controls.Model;
import frc.robot.lib.controls.Axis;

public class LogitechGamepadF310 extends Model {

    public LogitechGamepadF310(final int joystickId) {
        super(joystickId);
    }

    public final Button a = button(1);
    public final Button b = button(2);
    public final Button x = button(3);
    public final Button y = button(4);
    public final Button leftBumper = button(5);
    public final Button rightBumper = button(6);
    public final Button back = button(7);
    public final Button start = button(8);
    public final Button leftStickPress = button(9);
    public final Button rightStickPress = button(10);

    public final Axis leftStickX = axis(0);
    public final Axis leftStickY = axis(1);
    public final Axis rightTriggerHeld = axis(2);
    public final Axis leftTriggerHeld = axis(3);
    public final Axis rightStickX = axis(4);
    public final Axis rightStickY = axis(5);
}
