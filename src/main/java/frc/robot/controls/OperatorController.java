package frc.robot.controls;

import static frc.robot.Constants.TurretConstants.TURRET_INPUT_SCALE;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.Constants.HangingConstants;
import frc.robot.lib.controls.ControllerBase;
import frc.robot.lib.controls.models.LogitechGamepadF310;

public class OperatorController extends ControllerBase<ButtonKey, AxisKey> {

    public OperatorController() {
        var defaultLayout = new Layout("Default");
        var logitech = new LogitechGamepadF310(1);

        defaultLayout.assign(AxisKey.TurretSpeed,
                logitech.leftStickX.scaled(TURRET_INPUT_SCALE));
        defaultLayout.assign(AxisKey.AimerSpeed, logitech.rightStickX);
        defaultLayout.assign(ButtonKey.ShooterRun, logitech.rightBumper);
        defaultLayout.assign(ButtonKey.AutoAim, logitech.leftBumper);
        defaultLayout.assign(ButtonKey.EjectCargo, logitech.start);
        defaultLayout.assign(ButtonKey.LowShoot, logitech.back);
        defaultLayout.assign(ButtonKey.TrackCargo, logitech.a);
        defaultLayout.assign(ButtonKey.autoTest, logitech.b);

        defaultLayout.assign(ButtonKey.ExtendHanger, logitech.y);
        defaultLayout.assign(ButtonKey.RetractHanger, logitech.x);

        defaultLayout.assign(AxisKey.ExtendSpeed, () -> 0);
        defaultLayout.assign(AxisKey.RotateSpeed, () -> 0);

        var hangingLayout = new Layout("Hanging Debug");

        hangingLayout.assign(AxisKey.TurretSpeed, () -> 0);
        hangingLayout.assign(AxisKey.AimerSpeed, () -> 0);

        hangingLayout.assign(ButtonKey.ShooterRun, logitech.rightBumper);
        hangingLayout.assign(ButtonKey.AutoAim, logitech.leftBumper);
        hangingLayout.assign(ButtonKey.EjectCargo, logitech.start);
        hangingLayout.assign(ButtonKey.LowShoot, logitech.back);
        hangingLayout.assign(ButtonKey.TrackCargo, logitech.a);
        hangingLayout.assign(ButtonKey.autoTest, logitech.b);

        hangingLayout.assign(ButtonKey.ExtendHanger, logitech.y);
        hangingLayout.assign(ButtonKey.RetractHanger, logitech.x);

        hangingLayout.assign(AxisKey.ExtendSpeed,
                logitech.rightStickY.scaled(HangingConstants.EXTEND_SPEED).inverted());
        hangingLayout.assign(AxisKey.RotateSpeed, logitech.leftStickX.scaled(HangingConstants.ROTATION_SPEED));

        Shuffleboard.getTab("Debug").add("Opeer", this);
        Shuffleboard.getTab("Hanging Debug").add("help us", hangingLayout);
    }
}
