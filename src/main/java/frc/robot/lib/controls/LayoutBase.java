package frc.robot.lib.controls;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;

/**
 * A simple structure that stores the mapping between Button/AxisKeys and
 * Button/AxisInputs respectively. These are meant to be used with
 * {@link ControllerBase} to allow hot-swapping of various control
 * configurations, otherwise known as "layouts."
 */
public class LayoutBase implements Sendable {

    private final String name;

    /**
     * Create a new layout with the specified name
     *
     * @param name The name this Layout will display as in Shuffleboard
     */
    public LayoutBase(final String name) {
        this.name = name;
    }

    /**
     * Getter for this Layout's name
     *
     * @return The name of this Layout
     */
    public String getName() {
        return name;
    }

    private final Map<ButtonKey, ButtonInput> buttons = new HashMap<>();

    /**
     * For this Layout, assign a ButtonKey to a ButtonInput. Calling this method
     * multiple times with the same ButtonKey will override the previous assignment.
     *
     * @param buttonKey The key to assign with
     * @param input     The input being assigned
     */
    public void assignButton(final ButtonKey buttonKey, final ButtonInput input) {
        buttons.put(buttonKey, input);
    }

    /**
     * Retrieve the ButtonInput associated with a key
     *
     * @param buttonKey They key a input was assigned with
     * @return The associated input
     */
    public ButtonInput assign(final ButtonKey buttonKey) {
        final var button = buttons.get(buttonKey);

        if (button == null) {
            throw new InputNotAssignedException(name, buttonKey.toString());
        }

        return button;
    }

    private final Map<AxisKey, AxisInput> axes = new HashMap<>();

    /**
     * For this Layout, assign a AxisKey to a AxisInput. Calling this method
     * multiple times with the same AxisKey will override the previous assignment.
     *
     * @param axisKey The key to assign with
     * @param input   The input being assigned
     */
    public void assign(final AxisKey axisKey, final AxisInput input) {
        axes.put(axisKey, input);
    }

    /**
     * Retrieve the AxisInput associated with a key
     *
     * @param axisKey They key a input was assigned with
     * @return The associated input
     */
    public AxisInput getAxis(final AxisKey axisKey) {
        final var axis = axes.get(axisKey);

        if (axis == null) {
            throw new InputNotAssignedException(name, axisKey.toString());
        }

        return axis;
    }

    /**
     * Thrown when an input hasn't been assigned for the layout in question
     */
    public static class InputNotAssignedException extends RuntimeException {
        private InputNotAssignedException(final String layoutName, final String inputName) {
            super("The input " + inputName + " has not been assigned for layout " + layoutName);
        }
    }

    @Override
    public void initSendable(final SendableBuilder builder) {

        for (final var entry : buttons.entrySet()) {
            builder.addBooleanProperty(
                    entry.getKey().toString(),
                    entry.getValue()::get,
                    (_value) -> {
                    });
        }

        for (final var entry : axes.entrySet()) {
            builder.addDoubleProperty(
                    entry.getKey().toString(),
                    entry.getValue()::get,
                    (_value) -> {
                    });
        }
    }
}
