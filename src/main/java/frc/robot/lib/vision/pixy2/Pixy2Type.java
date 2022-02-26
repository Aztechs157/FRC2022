// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.lib.vision.pixy2;

import frc.robot.lib.vision.pixy2.Pixy2.Pixy2Block;
import frc.robot.lib.vision.pixy2.Pixy2.Pixy2Resolution;
import frc.robot.lib.vision.pixy2.Pixy2.Pixy2Version;
import java.awt.Color;

/** Add your docs here. */
public interface Pixy2Type {
    public Pixy2Version getVersion();

    public Pixy2Resolution getResolution();

    public long setCameraBrightness(final short brightness);

    public long setServos(final short servo1, final short servo2);

    public long setLED(final Color color);

    public long setLamp(final boolean upper, final boolean lower);

    public long getFPS();

    public Pixy2Block[] getBlocks(final byte signatureBitField, final byte maxReturnBlocks);
}
