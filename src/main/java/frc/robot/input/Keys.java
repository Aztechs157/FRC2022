package frc.robot.input;

import frc.robot.lib.input.Axis.AxisKey;
import frc.robot.lib.input.Button.ButtonKey;

public class Keys {
    public enum Button implements ButtonKey {
        Hello
    }

    public enum DebugButton implements ButtonKey.Optional {
        Print
    }

    public enum Axis implements AxisKey {
        DriveSpeedX,
        DriveSpeedY,
        DriveRotation,
    }
}
