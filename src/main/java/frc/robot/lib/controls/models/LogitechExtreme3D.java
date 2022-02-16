package frc.robot.lib.controls.models;

import frc.robot.lib.controls.AxisInput;
import frc.robot.lib.controls.ButtonInput;
import frc.robot.lib.controls.Model;

public class LogitechExtreme3D extends Model {
    public LogitechExtreme3D(final int joystickId) {
        super(joystickId);
    }

    public final ButtonInput trigger = button(1);
    public final ButtonInput thumbButton = button(2);
    public final ButtonInput button3 = button(3);
    public final ButtonInput button4 = button(4);
    public final ButtonInput button5 = button(5);
    public final ButtonInput button6 = button(6);
    public final ButtonInput button7 = button(7);
    public final ButtonInput button8 = button(8);
    public final ButtonInput button9 = button(9);
    public final ButtonInput button10 = button(10);
    public final ButtonInput button11 = button(11);
    public final ButtonInput button12 = button(12);

    public final AxisInput stickX = axis(0);
    public final AxisInput stickY = axis(1);
    public final AxisInput stickRotate = axis(2);
    public final AxisInput slider = axis(3);
}
