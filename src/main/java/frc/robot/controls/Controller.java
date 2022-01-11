package frc.robot.controls;

import frc.robot.lib.controls.ControllerBase;
import frc.robot.lib.controls.LayoutBase;
import frc.robot.controls.models.FlightModel;
import frc.robot.controls.models.LogitechModel;
import frc.robot.controls.Controller.ButtonKey;
import frc.robot.controls.Controller.AxisKey;

public class Controller extends ControllerBase<ButtonKey, AxisKey> {

    public Controller() {
    }

    public static enum ButtonKey {
    }

    public static enum AxisKey {
    }

    private static class Layout extends LayoutBase<ButtonKey, AxisKey> {
        public Layout(final String name) {
            super(name);
        }
    }
}
