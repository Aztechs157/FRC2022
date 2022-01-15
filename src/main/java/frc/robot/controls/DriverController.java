package frc.robot.controls;

import frc.robot.lib.controls.AxisInput;
import frc.robot.lib.controls.ControllerBase;
import frc.robot.controls.models.FlightModel;
import frc.robot.controls.models.LogitechModel;

public class DriverController extends ControllerBase<ButtonKey, AxisKey> {

    public DriverController() {
        var defaultLayout = new Layout("Default");
        addDefault(defaultLayout);

        var logitech = new LogitechModel(0);

        defaultLayout.assign(AxisKey.DriveSpeedX, logitech.leftStickX.scaled(0.3));
        defaultLayout.assign(AxisKey.DriveSpeedY, logitech.leftStickY.scaled(0.3).inverted());
        defaultLayout.assign(AxisKey.DriveRotation, logitech.rightStickX.scaled(0.3));

        defaultLayout.assign(ButtonKey.Hello, logitech.a);

        var flightLayout = new Layout("Flight Stick");
        add(flightLayout);

        var flight = new FlightModel(1);

        flightLayout.assign(AxisKey.DriveSpeedX, flight.stickX);
        flightLayout.assign(AxisKey.DriveSpeedY, flight.stickY);
        flightLayout.assign(AxisKey.DriveRotation,
                AxisInput.fromButtons(0.5, flight.middleLeft, flight.middleRight));

    }
}
