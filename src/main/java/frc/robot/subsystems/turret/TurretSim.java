// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.turret;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.Constants.TurretConstants;
import frc.robot.input.OperatorInputs;

public class TurretSim extends Turret {
    final ShuffleboardTab tab;
    final NetworkTableEntry turretMotorSim;
    final NetworkTableEntry posSensorSim;
    final NetworkTableEntry aimerMotorSim;
    final NetworkTableEntry absEncoderSim;
    final NetworkTableEntry actualValueSim;
    final NetworkTableEntry updateEncoderSim;

    /** Creates a new TurretSim. */
    public TurretSim(OperatorInputs operatorController) {
        super(operatorController);
        tab = Shuffleboard.getTab("Debug");
        turretMotorSim = tab.add("Turret Speed", 0).getEntry();
        posSensorSim = tab.add("Turret Position", 0).getEntry();
        aimerMotorSim = tab.add("Aimer Speed", 0).getEntry();
        absEncoderSim = tab.add("Aimer Position", 0).getEntry();
        actualValueSim = tab.add("Aimer Actual Position", 0).getEntry();
        updateEncoderSim = tab.add("check encoder", true).getEntry();
    }

    @Override
    public void periodic() {
        if (updateEncoderSim.getBoolean(true)) {
            // absEncoderUpdate();
        }
        // actualValueSim.setDouble(getActualPosition());
    }

    // simulates running the turret. Runs through shuffleboard.
    @Override
    public void turretTurn(double speed) {
        if (speed > 0 && readPositionSensor() > TurretConstants.CLOCKWISE_BOUNDARY) {
            turretMotorSim.setDouble(0);
        } else if (speed < 0 && readPositionSensor() < TurretConstants.COUNTERCLOCKWISE_BOUNDARY) {
            turretMotorSim.setDouble(0);
        } else {
            turretMotorSim.setDouble(speed);
        }
    }

    /**
     * This method simulates stopping the turret. Runs through shuffleboard.
     */
    @Override
    public void turretStop() {
        turretMotorSim.setDouble(0);
    }

    /**
     * This method simulates reading the position of the sensor.
     *
     * @return the integer from a double of the position sensor
     */
    @Override
    public int readPositionSensor() {
        return (int) Math.round(posSensorSim.getDouble(0));
    }

    /**
     * This method simulates getting the position of the Absolute Encoder.
     *
     * @return the absolute encoder position
     */
    // @Override
    // public double getAimerPosition() {
    // return absEncoderSim.getDouble(0);
    // }

    /**
     * This method is the logic that allows the aimer to simulate movement.
     *
     * @param speed aimer should run at.
     */
    // @Override
    // public void runAimer(double speed) {
    // if (speed > 0 && getActualPosition() > TurretConstants.AIMER_HIGHER_BOUNDARY)
    // {
    // aimerMotorSim.setDouble(0);
    // } else if (speed < 0 && getActualPosition() <
    // TurretConstants.AIMER_LOWER_BOUNDARY) {
    // aimerMotorSim.setDouble(0);
    // } else {
    // aimerMotorSim.setDouble(speed);
    // }
    // }
}
