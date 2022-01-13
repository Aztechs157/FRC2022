// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    enum color {
        RED, BLUE, NONE
    }

    /** Creates a new Intake. */
    public Intake() {
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public void raiseArm() {

    }

    public void lowerArm() {

    }

    public void rollerFeed() {

    }

    public void rollerEject() {

    }

    public color currentColor() {
        return color.NONE;
    }
}
