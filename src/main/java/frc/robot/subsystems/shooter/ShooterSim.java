// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.shooter;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class ShooterSim extends Shooter {
    final ShuffleboardTab tab;
    final NetworkTableEntry velocityMotorSim;
    final NetworkTableEntry motorPowerSim;

    /** Creates a new ShooterSim. */
    public ShooterSim() {
        tab = Shuffleboard.getTab("Debug");
        velocityMotorSim = tab.add("Shooter Speed", 0).getEntry();
        motorPowerSim = tab.add("Motor Power", 0).getEntry();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    /**
     * simulator code to test getting the velocity of the shooter motor.
     * Runs through shuffleboard.
     *
     * @return
     */
    @Override
    public double measureFrontVelocity() {
        return velocityMotorSim.getDouble(0);
    }

    /**
     * simulator code to test setting the power for the shooter. Runs
     * through shuffleboard.
     *
     * @param power
     */
    @Override
    public void setBothPower(double power) {
        motorPowerSim.setDouble(power);
    }
}
