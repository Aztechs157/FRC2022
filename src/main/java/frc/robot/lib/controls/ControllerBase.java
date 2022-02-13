package frc.robot.lib.controls;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * Object that manages layouts. A layout can be selected from Shuffleboard that
 * can then be used by the robot. It maps the inputs of a controller to the
 * desired functions of the robot.
 */
public class ControllerBase<ButtonKey, AxisKey> implements Sendable {

    private final SendableChooser<LayoutBase<ButtonKey, AxisKey>> layouts = new SendableChooser<>();

    @Override
    public void initSendable(final SendableBuilder builder) {
        layouts.initSendable(builder);
    }

    /**
     * Get a button from the currently selected layout. This acts both as a
     * {@link ButtonInput} and a proper WPI {@link Button} to allow for command
     * running.
     *
     * @param buttonKey Which button to retrieve
     * @return A {@link ButtonInput} and {@link Button} representing the input
     */
    public Button button(final ButtonKey buttonKey) {
        return new Button(getSelectedLayout().getButton(buttonKey));
    }

    /**
     * Get a axis from the currently selected layout.
     *
     * @param axisKey Which axis to retrieve
     * @return A {@link AxisInput} representing the input
     */
    public AxisInput axis(final AxisKey axisKey) {
        return getSelectedLayout().getAxis(axisKey);
    }

    /**
     * Get the layout currently selected on Shuffleboard
     *
     * @return The selected layout
     */
    private LayoutBase<ButtonKey, AxisKey> getSelectedLayout() {
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
            super("No layouts have been added to the controller");
        }
    }

    private boolean hasDefault = false;

    /**
     * Add a Layout to this Controller. This allows the Controller to display and
     * swap to it using Shuffleboard. If no layouts have been added yet, this will
     * implicitly also set the new one as the default. To manually overwrite the
     * default later, use {@link ControllerBase#addAndSetDefault(LayoutBase)}.
     *
     * @param layout The Layout to add
     */
    public void add(final LayoutBase<ButtonKey, AxisKey> layout) {
        if (hasDefault) {
            layouts.addOption(layout.getName(), layout);
        } else {
            addAndSetDefault(layout);
        }
    }

    /**
     * Manually set a layout as the default. This will additionally add it if it
     * wasn't added already. Normally this isn't necessary, as
     * {@link ControllerBase#add(LayoutBase)} will automatically set the first
     * layout as default.
     *
     * @param layout The Layout to add
     */
    public void addAndSetDefault(final LayoutBase<ButtonKey, AxisKey> layout) {
        layouts.setDefaultOption(layout.getName(), layout);
        hasDefault = true;
    }

    /**
     * Convenience class to avoid having to restate the `ButtonKey` and `AxisKey`
     * type parameters for `LayoutBase`, as we can just use the ones passed to
     * `ControllerBase`. Additionally, this will automatically call
     * {@link ControllerBase#add(LayoutBase)} with the newly created layout.
     *
     * Example:
     *
     * <pre>
     *
     * class Controller extends ControllerBase<ButtonKey, AxisKey> {
     *     // Before
     *     var layout = new LayoutBase<ButtonKey, AxisKey>("Layout");
     *     addDefault(layout);
     *
     *     // After
     *     var layout = new Layout("Layout");
     * }
     *
     * </pre>
     */
    public class Layout extends LayoutBase<ButtonKey, AxisKey> {
        public Layout(final String name) {
            super(name);
            add(this);
        }
    }
}
