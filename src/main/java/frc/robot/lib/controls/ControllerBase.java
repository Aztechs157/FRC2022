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
     * Add a Layout to this Controller. This particular method sets the Layout to
     * the Controller's default.
     *
     * @param layout The Layout to add
     */
    public void addDefault(final LayoutBase<ButtonKey, AxisKey> layout) {
        layouts.setDefaultOption(layout.getName(), layout);
    }

    /**
     * Add a Layout to this Controller. This allows the Controller to display and
     * swap to it using Shuffleboard.
     *
     * @param layout The Layout to add
     */
    public void add(final LayoutBase<ButtonKey, AxisKey> layout) {
        layouts.addOption(layout.getName(), layout);
    }

    /**
     * Get a proper WPI {@link Button} from the currently selected layout. This
     * allows you to setup commands to run.
     *
     * @param buttonKey Which button to retreave
     * @return A WPI {@link Button} representing the input
     */
    public Button button(final ButtonKey buttonKey) {
        return new Button(() -> getButton(buttonKey));
    }

    /**
     * Get a button from the currently selected layout
     *
     * @param buttonKey Which button to retreave
     * @return The boolean representing the input
     */
    public boolean getButton(final ButtonKey buttonKey) {
        var layout = layouts.getSelected();

        if (layout == null) {
            return false;
        }

        return layout.getButton(buttonKey).get();
    }

    /**
     * Get a axis from the currently selected layout
     *
     * @param axisKey Which axis to retreave
     * @return The number representing the input
     */
    public double getAxis(final AxisKey axisKey) {
        var layout = layouts.getSelected();

        if (layout == null) {
            return 0;
        }

        return layout.getAxis(axisKey).get();
    }
}
