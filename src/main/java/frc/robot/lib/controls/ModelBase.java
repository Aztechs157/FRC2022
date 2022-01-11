package frc.robot.lib.controls;

import edu.wpi.first.wpilibj.DriverStation;

public class ModelBase {

    private final int joystickId;

    public ModelBase(final int joystickId) {
        this.joystickId = joystickId;
    }

    public ButtonInput button(final int buttonId) {
        return () -> DriverStation.getStickButton(joystickId, buttonId);
    }

    public AxisInput axis(final int axisId) {
        return () -> DriverStation.getStickAxis(joystickId, axisId);
    }
}
