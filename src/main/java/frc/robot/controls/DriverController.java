package frc.robot.controls;

import frc.robot.lib.controls.AxisInput;
import frc.robot.lib.controls.ControllerBase;
import frc.robot.controls.models.FlightModel;
import frc.robot.controls.models.LogitechModel;

public class DriverController extends ControllerBase<ButtonKey, AxisKey> {

    public DriverController() {
        var defaultLayout = new Layout("Default");
        var logitech = new LogitechModel(0);

        defaultLayout.assign(AxisKey.DriveSpeedX, logitech.leftStickX);
        defaultLayout.assign(AxisKey.DriveSpeedY, logitech.leftStickY);
        defaultLayout.assign(AxisKey.DriveRotation, logitech.rightStickX);

        var flightLayout = new Layout("Flight Stick");
        var flight = new FlightModel(1);

        flightLayout.assign(AxisKey.DriveSpeedX, flight.stickX);
        flightLayout.assign(AxisKey.DriveSpeedY, flight.stickY);
        flightLayout.assign(AxisKey.DriveRotation,
                AxisInput.fromButtons(0.5, flight.middleLeft, flight.middleRight));
    }
}
