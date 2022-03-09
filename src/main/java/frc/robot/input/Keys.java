package frc.robot.input;

import frc.robot.lib.input.axis.AxisKey;
import frc.robot.lib.input.button.ButtonKey;

public class Keys {
    public enum Button implements ButtonKey {
        Hello
    }

    public enum DebugButton implements ButtonKey.Optional {
        PrintFoo, PrintBar
    }

    public enum Axis implements AxisKey {
        DriveSpeedX,
        DriveSpeedY,
        DriveRotation,
    }
}
