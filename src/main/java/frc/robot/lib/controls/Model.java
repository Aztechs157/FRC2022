package frc.robot.lib.controls;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * Models map physical buttons/axises to {@link Button} or
 * {@link Axis}.
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
     * Create a {@link Button} that models a physical button
     *
     * @param buttonId The button to model
     * @return The modeled {@link Button}
     */
    public Button button(final int buttonId) {
        return new Button(() -> DriverStation.getStickButton(deviceId, buttonId));
    }

    /**
     * Create a {@link Axis} that models a physical axis
     *
     * @param buttonId The axis to model
     * @return The modeled {@link Axis}
     */
    public Axis axis(final int axisId) {
        return new Axis(() -> DriverStation.getStickAxis(deviceId, axisId));
    }
}
