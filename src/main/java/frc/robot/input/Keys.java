package frc.robot.input;

import frc.robot.lib.input.axis.AxisKey;
import frc.robot.lib.input.button.ButtonKey;

public class Keys {
    public enum Button implements ButtonKey {
        Hello, UptakeRun, KickerRun, IntakeRun, ShooterRun, EjectCargo, DebugPrint,
        AutoAim, LowShoot, ResetDrivePosition, TrackCargo, autoTest, Hang,
        ExtendHanger, RetractHanger
    }

    public enum DebugButton implements ButtonKey.Optional {
        PrintFoo, PrintBar
    }

    public enum Axis implements AxisKey {
        TurnTurret, DriveSpeedX, DriveSpeedY, DriveRotation,
        TurretSpeed, AimerSpeed, ExtendSpeed, RotateSpeed
    }
}
