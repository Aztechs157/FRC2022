package frc.robot.lib.input;

import frc.robot.lib.input.axis.Axis;
import frc.robot.lib.input.button.Button;
import frc.robot.lib.input.pov.Pov;

/**
 * Models map physical inputs on a input device to input classes such as
 * {@link Button}, {@link Axis}, or {@link Pov}.
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
        return new Button(deviceId, buttonId);
    }

    /**
     * Create a {@link Axis} that models a physical axis
     *
     * @param buttonId The axis to model
     * @return The modeled {@link Axis}
     */
    public Axis axis(final int axisId) {
        return new Axis(deviceId, axisId);
    }

    /**
     * Create a {@link Pov} that modes a physical pov
     *
     * @param povId The pov to model
     * @return The modeled {@link Pov}
     */
    public Pov pov(final int povId) {
        return new Pov(deviceId, povId);
    }
}
