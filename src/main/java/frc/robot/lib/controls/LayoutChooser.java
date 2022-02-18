package frc.robot.lib.controls;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * Object that manages layouts. A layout can be selected from Shuffleboard that
 * can then be used by the robot. It maps the inputs of a controller to the
 * desired functions of the robot.
 */
public class LayoutChooser implements Sendable {

    private final SendableChooser<Layout> layouts = new SendableChooser<>();

    @Override
    public void initSendable(final SendableBuilder builder) {
        layouts.initSendable(builder);
    }

    /**
     * Get a button from the currently selected layout. This acts both as a
     * {@link Button} and a proper WPI {@link ButtonKey} to allow for command
     * running.
     *
     * @param buttonKey Which button to retrieve
     * @return A {@link Button} and {@link ButtonKey} representing the input
     */
    public Button button(final Button.Key buttonKey) {
        return new Button(() -> getSelected().button(buttonKey).getAsBoolean());
    }

    /**
     * Get a axis from the currently selected layout
     *
     * @param axisKey Which axis to retrieve
     * @return A {@link Axis} representing the input
     */
    public Axis axis(final Axis.Key axisKey) {
        return new Axis(() -> getSelected().axis(axisKey).getAsDouble());
    }

    /**
     * Get the layout currently selected on Shuffleboard
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
     * Thrown when no layouts have been added to a controller
     */
    public static class NoLayoutsAddedException extends RuntimeException {
        private NoLayoutsAddedException() {
            super("No Layouts have been added to the LayoutChooser");
        }
    }

    private boolean hasDefault = false;

    /**
     * Add a Layout to this Controller. This allows the Controller to display and
     * swap to it using Shuffleboard. If no layouts have been added yet, this will
     * implicitly also set the new one as the default. To manually overwrite the
     * default later, use {@link LayoutChooser#addAndSetDefault(Layout)}.
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
     * Manually set a layout as the default. This will additionally add it if it
     * wasn't added already. Normally this isn't necessary, as
     * {@link LayoutChooser#add(Layout)} will automatically set the first
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
