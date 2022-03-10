package frc.robot.lib.input;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.lib.input.axis.Axis;
import frc.robot.lib.input.axis.AxisKey;
import frc.robot.lib.input.button.Button;
import frc.robot.lib.input.button.ButtonKey;
import frc.robot.lib.input.pov.Pov;
import frc.robot.lib.input.pov.PovKey;

/**
 * Object that manages layouts. A layout can be selected from Shuffleboard that
 * can then be used by the robot. It maps the inputs of a
 * {@link ShuffleLayoutChooser}
 * to the
 * desired functions of the robot.
 */
public class ShuffleLayoutChooser implements Sendable {

    private final SendableChooser<Layout> layouts = new SendableChooser<>();

    @Override
    public void initSendable(final SendableBuilder builder) {
        layouts.initSendable(builder);
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

    /**
     * Get the {@link Layout} currently selected on Shuffleboard
     *
     * @return The selected layout
     */
    private Layout getSelected() {
        final var layout = layouts.getSelected();

        if (layout == null) {
            throw new NoLayoutsAddedException();
        }

        return layout;
    }

    /**
     * Thrown when no {@link Layout}s have been added to a
     * {@link ShuffleLayoutChooser}
     */
    public static class NoLayoutsAddedException extends RuntimeException {
        private NoLayoutsAddedException() {
            super("No Layouts have been added to the ShuffleLayoutChooser");
        }
    }

    private boolean hasDefault = false;

    /**
     * Add a {@link Layout} to this {@link ShuffleLayoutChooser}. This allows the
     * {@link ShuffleLayoutChooser} to display and
     * swap to it using Shuffleboard. If no layouts have been added yet, this will
     * implicitly also set the new one as the default. To manually overwrite the
     * default later, use {@link ShuffleLayoutChooser#addAndSetDefault(Layout)}.
     *
     * @param layout The Layout to add
     */
    public Layout add(final Layout layout) {
        if (hasDefault) {
            layouts.addOption(layout.getName(), layout);
        } else {
            addAndSetDefault(layout);
        }
        return layout;
    }

    /**
     * Manually set a {@link Layout} as the default. This will additionally add it
     * if it wasn't added already. Normally this isn't necessary, as
     * {@link ShuffleLayoutChooser#add(Layout)} will automatically set the first
     * layout as default.
     *
     * @param layout The Layout to add
     */
    public Layout addAndSetDefault(final Layout layout) {
        layouts.setDefaultOption(layout.getName(), layout);
        hasDefault = true;
        return layout;
    }
}
