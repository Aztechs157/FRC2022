// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.vision;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.VisionConstants;
import frc.robot.lib.vision.LimeLight;
import frc.robot.lib.vision.LimeLight.LightMode;
import frc.robot.lib.vision.pixy2.Pixy2;
import frc.robot.lib.vision.pixy2.Pixy2.Pixy2Block;

public class Vision extends SubsystemBase {
    public final LimeLight limeLight = new LimeLight();
    private final Pixy2 pixy = new Pixy2(Port.kOnboard, VisionConstants.PIXY_PORT);

    /** Creates a new VisionSubsystem. */
    public Vision() {
        CameraServer.startAutomaticCapture();
    }

    // returns if the limelight has a valid target, IE. reflective tape.
    public boolean hasTarget() {
        return limeLight.hasValidTargets();
    }

    // sets the limelight's light to be on or off
    public void setLED(final boolean status) {
        limeLight.setLightMode(status ? LightMode.ForceOn : LightMode.ForceOff);
    }

    // Gets the x value for aiming towards the hub
    public double getHubX() {
        return limeLight.getXAxis();
    }

    // gets the diagonal to figure out how to angle the hood
    public double getDiagonal() {
        final var width = Math.pow(limeLight.getHorizontalSideLength(), 2);
        final var height = Math.pow(limeLight.getVerticalSideLength(), 2);

        return Math.sqrt(width + height);
    }

    // gets the red byte color for the pixy
    public int getRedCargoX() {
        return getNCargoX(VisionConstants.RED);
    }

    // gets the blue byte color for the pixy

    public int getBlueCargoX() {
        return getNCargoX(VisionConstants.BLUE);
    }

    // adds the byte values for both blue and red so we can collect both if wanted
    public int getAllCargoX() {
        return getNCargoX((byte) (VisionConstants.RED + VisionConstants.BLUE));
    }

    // logic to find cargo, split them up into blocks, and choose the closest block
    // (cargo with correct color)
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
