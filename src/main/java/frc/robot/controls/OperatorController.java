package frc.robot.controls;

import static frc.robot.Constants.TurretConstants.TURRET_INPUT_SCALE;
import frc.robot.lib.controls.ControllerBase;
import frc.robot.lib.controls.models.LogitechGamepadF310;

public class OperatorController extends ControllerBase<ButtonKey, AxisKey> {

    public OperatorController() {
        var defaultLayout = new Layout("Default");
        var logitech = new LogitechGamepadF310(1);

        defaultLayout.assign(AxisKey.TurretSpeed, logitech.leftStickX.scaled(TURRET_INPUT_SCALE));
        defaultLayout.assign(AxisKey.AimerSpeed, logitech.rightStickX);
        defaultLayout.assign(ButtonKey.ShooterRun, logitech.rightBumper);
        defaultLayout.assign(ButtonKey.AutoAim, logitech.leftBumper);
        defaultLayout.assign(ButtonKey.EjectCargo, logitech.start);
        defaultLayout.assign(ButtonKey.LowShoot, logitech.back);
        defaultLayout.assign(ButtonKey.TrackCargo, logitech.a);
    }
}
