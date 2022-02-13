// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.vision;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.vision.LimeLight;

public class VisionSubsystem extends SubsystemBase {
    private final LimeLight limeLight = new LimeLight();

    /** Creates a new VisionSubsystem. */
    public VisionSubsystem() {
        final var tab = Shuffleboard.getTab("Debug");
        tab.addNumber("Diagonal", this::getDiagonal);
    }

    public double getDiagonal() {
        final var width = Math.pow(limeLight.getHorizontalSideLength(), 2);
        final var height = Math.pow(limeLight.getVerticalSideLength(), 2);

        return Math.sqrt(width + height);
    }
}
