package frc.robot.lib.input.models;

import frc.robot.lib.input.Model;
import frc.robot.lib.input.axis.Axis;
import frc.robot.lib.input.button.Button;
import frc.robot.lib.input.pov.Pov;

public class LogitechAttack extends Model {

    public LogitechAttack(final int joystickId) {
        super(joystickId);
    }

    public final Button trigger = button(1);
    public final Button button2 = button(2);
    public final Button button3 = button(3);
    public final Button button4 = button(4);
    public final Button button5 = button(5);
    public final Button button6 = button(6);
    public final Button button7 = button(7);
    public final Button button8 = button(8);
    public final Button button9 = button(9);
    public final Button button10 = button(10);
    public final Button button11 = button(11);

    public final Axis stickX = axis(0);
    public final Axis stickY = axis(1);
    public final Axis slider = axis(3);

    public final Pov pov = pov(0);
}
