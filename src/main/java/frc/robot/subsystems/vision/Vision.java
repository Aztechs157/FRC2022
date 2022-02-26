// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.vision;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.VisionConstants;
import frc.robot.lib.vision.LimeLight;
import frc.robot.lib.vision.LimeLight.LightMode;
import frc.robot.lib.vision.pixy2.Pixy2;
import frc.robot.lib.vision.pixy2.Pixy2.Pixy2Block;

public class Vision extends SubsystemBase {
    private final LimeLight limeLight = new LimeLight();
    private final Pixy2 pixy = new Pixy2(Port.kMXP, VisionConstants.PIXY_PORT);

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

    public int getRedCargoX() {
        return getNCargoX((byte) 0b00000001);
    }

    public int getBlueCargoX() {
        return getNCargoX((byte) 0b00000010);
    }

    public int getAllCargoX() {
        return getNCargoX((byte) 0b00000011);
    }

    public int getNCargoX(byte n) {
        var blocks = pixy.getBlocks(n, (byte) 10);
        if (blocks.length > 0) {
            Pixy2Block largestBlock = null;
            var largestArea = 0;
            for (Pixy2Block block : blocks) {
                var blockArea = block.width * block.height;
                if (blockArea > largestArea) {
                    largestBlock = block;
                    largestArea = blockArea;
                }
            }
            return largestBlock.centerXAxis;
        } else {
            return -1;
        }
    }
}
