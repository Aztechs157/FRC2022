package frc.robot.controls.models;

import frc.robot.lib.controls.AxisInput;
import frc.robot.lib.controls.ButtonInput;
import frc.robot.lib.controls.ModelBase;

public class FlightModel extends ModelBase {

    public FlightModel(final int joystickId) {
        super(joystickId);
    }

    public final ButtonInput bumper = button(1);

    public final ButtonInput middleDown = button(2);
    public final ButtonInput middleUp = button(3);
    public final ButtonInput middleLeft = button(4);
    public final ButtonInput middleRight = button(5);

    public final ButtonInput topLeft = button(6);
    public final ButtonInput bottomLeft = button(7);

    public final ButtonInput farBottomLeft = button(8);
    public final ButtonInput farBottomRight = button(9);

    public final ButtonInput bottomRight = button(10);
    public final ButtonInput topRight = button(11);

    public final AxisInput stickX = axis(0);
    public final AxisInput stickY = axis(1);
    public final AxisInput triggerHeld = axis(3);
}
