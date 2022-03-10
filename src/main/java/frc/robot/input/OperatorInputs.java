package frc.robot.input;

import frc.robot.lib.input.Layout;
import frc.robot.lib.input.ShuffleLayoutChooser;
import frc.robot.lib.input.axis.Axis;
import frc.robot.lib.input.models.LogitechGamepadF310;
import static frc.robot.Constants.TurretConstants.TURRET_INPUT_SCALE;

public class OperatorInputs extends ShuffleLayoutChooser {

    public OperatorInputs() {
        final var defaultLayout = add(new Layout("Default"));
        final var logitech = new LogitechGamepadF310(1);

        defaultLayout.assign(Keys.Axis.TurretSpeed,
                logitech.leftStickX.scaled(TURRET_INPUT_SCALE));
        defaultLayout.assign(Keys.Axis.AimerSpeed, logitech.rightStickX);

        defaultLayout.assign(Keys.Button.ShooterRun, logitech.rightBumper);
        defaultLayout.assign(Keys.Button.AutoAim, logitech.leftBumper);
        defaultLayout.assign(Keys.Button.EjectCargo, logitech.start);
        defaultLayout.assign(Keys.Button.LowShoot, logitech.back);
        defaultLayout.assign(Keys.Button.TrackCargo, logitech.a);
        defaultLayout.assign(Keys.Button.autoTest, logitech.b);

        defaultLayout.assign(Keys.Button.ExtendHanger, logitech.y);
        defaultLayout.assign(Keys.Button.RetractHanger, logitech.x);

        defaultLayout.assign(Keys.Axis.ExtendSpeed, Axis.DEFAULT);
        defaultLayout.assign(Keys.Axis.RotateSpeed, Axis.DEFAULT);
    }
}
