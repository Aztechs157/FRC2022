// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.vision;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.lib.vision.LimeLight;
import frc.robot.lib.vision.LimeLight.LightMode;

public class Vision extends SubsystemBase {
    private final LimeLight limeLight = new LimeLight();

    /** Creates a new VisionSubsystem. */
    public Vision() {
    }

    public boolean hasTarget() {
        return limeLight.hasValidTargets();
    }

    public void setLED(final boolean status) {
        limeLight.setLightMode(status ? LightMode.ForceOn : LightMode.ForceOff);
    }

    public double getHubX() {
        return limeLight.getXAxis();
    }

    public double getDiagonal() {
        final var width = Math.pow(limeLight.getHorizontalSideLength(), 2);
        final var height = Math.pow(limeLight.getVerticalSideLength(), 2);

        return Math.sqrt(width + height);
    }
}
