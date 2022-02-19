package frc.robot.controls;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.lib.controls.ControllerBase;
import frc.robot.lib.controls.models.LogitechGamepadF310;

public class OperatorController extends ControllerBase<ButtonKey, AxisKey> {

    public OperatorController() {
        var defaultLayout = new Layout("Default");
        var logitech = new LogitechGamepadF310(1);

        final var turretInputScale = 0.5;

        // defaultLayout.assign(ButtonKey.DebugPrint, logitech.back);
        defaultLayout.assign(AxisKey.TurretSpeed, logitech.leftStickX.scaled(turretInputScale));
        defaultLayout.assign(AxisKey.AimerSpeed, logitech.rightStickX);
        defaultLayout.assign(ButtonKey.ShooterRun, logitech.rightBumper);
        defaultLayout.assign(ButtonKey.autoAim, logitech.leftBumper);
        defaultLayout.assign(ButtonKey.EjectCargo, logitech.start);
        defaultLayout.assign(ButtonKey.emergencyEject, logitech.back);
    }
}
