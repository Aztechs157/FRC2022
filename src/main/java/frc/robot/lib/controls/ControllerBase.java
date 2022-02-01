package frc.robot.lib.controls;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.button.Button;

public class ControllerBase<ButtonKey, AxisKey> implements Sendable {

    private SendableChooser<LayoutBase<ButtonKey, AxisKey>> layouts = new SendableChooser<>();

    @Override
    public void initSendable(final SendableBuilder builder) {
        layouts.initSendable(builder);
    }

    /**
     * Get a proper WPI {@link Button} from the currently selected layout. This
     * allows you to setup commands to run.
     *
     * @param buttonKey Which button to retrieve
     * @return A WPI {@link Button} representing the input
     */
    public Button button(final ButtonKey buttonKey) {
        return new Button(() -> getButton(buttonKey));
    }

    /**
     * Get a button from the currently selected layout
     *
     * @param buttonKey Which button to retrieve
     * @return The boolean representing the input
     */
    public boolean getButton(final ButtonKey buttonKey) {
        return getSelectedLayout().getButton(buttonKey).getAsBoolean();
    }

    /**
     * Get a axis from the currently selected layout
     *
     * @param axisKey Which axis to retrieve
     * @return The number representing the input
     */
    public double getAxis(final AxisKey axisKey) {
        return getSelectedLayout().getAxis(axisKey).getAsDouble();
    }

    private LayoutBase<ButtonKey, AxisKey> getSelectedLayout() {
        var layout = layouts.getSelected();

        if (layout == null) {
            throw new NoLayoutsAddedException();
        }

        return layout;
    }

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
