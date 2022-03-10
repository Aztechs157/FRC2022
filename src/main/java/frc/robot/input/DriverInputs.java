package frc.robot.input;

import frc.robot.lib.input.Layout;
import frc.robot.lib.input.ShuffleLayoutChooser;
import frc.robot.lib.input.models.LogitechGamepadF310;
import static frc.robot.Constants.DriveConstants.DRIVE_INPUT_SCALE;

public class DriverInputs extends ShuffleLayoutChooser {

    public DriverInputs() {
        final var defaultLayout = add(new Layout("Default"));
        final var logitech = new LogitechGamepadF310(0);

        defaultLayout.assign(
                Keys.Axis.DriveSpeedX,
                logitech.leftStickX.scaled(DRIVE_INPUT_SCALE));
        defaultLayout.assign(
                Keys.Axis.DriveSpeedY,
                logitech.leftStickY.scaled(DRIVE_INPUT_SCALE).inverted());
        defaultLayout.assign(
                Keys.Axis.DriveRotation,
                logitech.rightStickX.scaled(DRIVE_INPUT_SCALE));

        defaultLayout.assign(Keys.Button.Hello, logitech.back);
        defaultLayout.assign(Keys.Button.IntakeRun, logitech.rightBumper);
        defaultLayout.assign(Keys.Button.LowShoot, logitech.start);
        defaultLayout.assign(Keys.Button.Hang, logitech.b);
    }
}
