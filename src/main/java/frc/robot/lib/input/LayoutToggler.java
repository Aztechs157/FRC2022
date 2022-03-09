package frc.robot.lib.input;

import frc.robot.lib.input.axis.Axis;
import frc.robot.lib.input.axis.AxisKey;
import frc.robot.lib.input.button.Button;
import frc.robot.lib.input.button.ButtonKey;
import frc.robot.lib.input.pov.Pov;
import frc.robot.lib.input.pov.PovKey;

/** Toggles between two layouts */
public class LayoutToggler {
    private final Layout firstLayout;
    private final Layout secondLayout;
    private boolean state;

    public LayoutToggler(final Layout firstLayout, final Layout secondLayout) {
        this.firstLayout = firstLayout;
        this.secondLayout = secondLayout;
    }

    /**
     * Get a button from the currently selected layout.
     *
     * @param key Which button to retrieve
     * @return A {@link Button} and {@link ButtonKey} representing the input
     */
    public Button button(final ButtonKey key) {
        return new Button(() -> getSelected().button(key).get());
    }

    /**
     * Get a axis from the currently selected layout.
     *
     * @param key Which axis to retrieve
     * @return A {@link Axis} representing the input
     */
    public Axis axis(final AxisKey key) {
        return new Axis(() -> getSelected().axis(key).get());
    }

    /**
     * Get a pov from the currently selected layout.
     *
     * @param key Which pov to retrieve
     * @return A {@link Pov} representing the input
     */
    public Pov pov(final PovKey key) {
        return new Pov(() -> getSelected().pov(key).get());
    }

    public Layout getSelected() {
        if (state) {
            return firstLayout;
        } else {
            return secondLayout;
        }
    }

    public void toggle() {
        state = !state;
    }
}
