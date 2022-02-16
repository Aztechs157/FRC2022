package frc.robot.lib.controls;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * Models map physical buttons/axises to {@link ButtonInput} or
 * {@link AxisInput}.
 */
public class Model {

    private final int deviceId;

    /**
     * Create a Model that models the device specified by `deviceId`
     *
     * @param deviceId The id of the device
     */
    public Model(final int deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * Create a {@link ButtonInput} that models a physical button
     *
     * @param buttonId The button to model
     * @return The modeled {@link ButtonInput}
     */
    public ButtonInput button(final int buttonId) {
        return new ButtonInput(() -> DriverStation.getStickButton(deviceId, buttonId));
    }

    /**
     * Create a {@link AxisInput} that models a physical axis
     *
     * @param buttonId The axis to model
     * @return The modeled {@link AxisInput}
     */
    public AxisInput axis(final int axisId) {
        return new AxisInput(() -> DriverStation.getStickAxis(deviceId, axisId));
    }
}
