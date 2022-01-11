package frc.robot.lib.controls;

import java.util.HashMap;
import java.util.Map;

public class LayoutBase<ButtonKey, AxisKey> {

    private final String name;

    /**
     * TODO
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
    public void assign(final ButtonKey buttonKey, final ButtonInput input) {
        buttons.put(buttonKey, input);
    }

    /**
     * Retreave the ButtonInput accosiated with a key
     *
     * @param buttonKey They key a input was assigned with
     * @return The accosiated input
     */
    public ButtonInput getButton(final ButtonKey buttonKey) {
        return buttons.get(buttonKey);
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
     * Retreave the AxisInput accosiated with a key
     *
     * @param axisKey They key a input was assigned with
     * @return The accosiated input
     */
    public AxisInput getAxis(final AxisKey axisKey) {
        return axes.get(axisKey);
    }
}
