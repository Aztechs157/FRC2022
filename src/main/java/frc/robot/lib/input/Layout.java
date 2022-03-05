package frc.robot.lib.input;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;

/**
 * A simple structure that stores the mapping between Button/AxisInput.Keys and
 * Button/AxisInputs respectively. These are meant to be used with
 * {@link LayoutChooser} to allow hot-swapping of various control
 * configurations, otherwise known as "layouts."
 */
public class Layout implements Sendable {

    private final String name;

    /**
     * Create a new layout with the specified name
     *
     * @param name The name this Layout will display as in Shuffleboard
     */
    public Layout(final String name) {
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

    private final Map<Button.Key, Button> buttons = new HashMap<>();
    private final Map<Axis.Key, Axis> axes = new HashMap<>();
    private final Map<Pov.Key, Pov> povs = new HashMap<>();

    /**
     * For this Layout, assign a {@link Button.Key} to a {@link Button}. Calling
     * this method multiple times with the same key will override the previous
     * assignment.
     *
     * @param key    The key to assign with
     * @param button The button being assigned
     */
    public void assign(final Button.Key key, final Button button) {
        buttons.put(key, button);
    }

    /**
     * For this Layout, assign a {@link Axis.Key} to a {@link Axis}. Calling this
     * method multiple times with the same key will override the previous
     * assignment.
     *
     * @param key  The key to assign with
     * @param axis The axis being assigned
     */
    public void assign(final Axis.Key key, final Axis axis) {
        axes.put(key, axis);
    }

    /**
     * For this Layout, assign a {@link Pov.Key} to a {@link Pov}. Calling this
     * method multiple times with the same key will override the previous
     * assignment.
     *
     * @param key The key to assign with
     * @param pov The pov being assigned
     */
    public void assign(final Pov.Key key, final Pov pov) {
        povs.put(key, pov);
    }

    /**
     * Retrieve the {@link Button} associated with a {@link Button.Key}
     *
     * @param key The key a button was assigned to
     * @return The associated button
     */
    public Button button(final Button.Key key) {
        final var button = buttons.get(key);

        if (button == null) {
            throw new InputNotAssignedException(name, "button", key.toString());
        }

        return button;
    }

    /**
     * Retrieve the {@link Axis} associated with a {@link Axis.Key}
     *
     * @param key The key an axis was assigned to
     * @return The associated axis
     */
    public Axis axis(final Axis.Key key) {
        final var axis = axes.get(key);

        if (axis == null) {
            throw new InputNotAssignedException(name, "axis", key.toString());
        }

        return axis;
    }

    /**
     * Retrieve the {@link Pov} associated with a {@link Pov.Key}
     *
     * @param key The key an pov was assigned to
     * @return The associated pov
     */
    public Pov pov(final Pov.Key key) {
        final var pov = povs.get(key);

        if (pov == null) {
            throw new InputNotAssignedException(name, "pov", key.toString());
        }

        return pov;
    }

    /**
     * Thrown when an input hasn't been assigned for the layout in question
     */
    public static class InputNotAssignedException extends RuntimeException {
        private InputNotAssignedException(final String layoutName, final String inputType, final String inputName) {
            super("The " + inputType + " " + inputName + " has not been assigned for layout " + layoutName);
        }
    }

    public Button tryButton(final Button.Key buttonKey) {
        final var button = buttons.get(buttonKey);

        if (button == null) {
            return Button.DEFAULT;
        }

        return button;
    }

    public Axis tryAxis(final Axis.Key axisKey) {
        final var axis = axes.get(axisKey);

        if (axis == null) {
            return Axis.DEFAULT;
        }

        return axis;
    }

    public Pov tryPov(final Pov.Key povKey) {
        final var pov = povs.get(povKey);

        if (pov == null) {
            return Pov.DEFAULT;
        }

        return pov;
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

        for (final var entry : povs.entrySet()) {
            builder.addDoubleProperty(
                    entry.getKey().toString(),
                    entry.getValue()::get,
                    (_value) -> {
                    });
        }
    }
}
