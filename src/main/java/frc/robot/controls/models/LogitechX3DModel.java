package frc.robot.controls.models;

import frc.robot.lib.controls.AxisInput;
import frc.robot.lib.controls.ModelBase;

public class LogitechX3DModel extends ModelBase {
    public LogitechX3DModel(final int joystickId) {
        super(joystickId);
    }

    public final AxisInput stickX = axis(0);
    public final AxisInput stickY = axis(1);
    public final AxisInput stickZ = axis(2);
}
