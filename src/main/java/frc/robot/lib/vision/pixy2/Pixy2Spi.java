// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.lib.vision.pixy2;

import java.awt.Color;

import edu.wpi.first.wpilibj.SPI;
import frc.robot.lib.vision.pixy2.Pixy2.Pixy2Block;
import frc.robot.lib.vision.pixy2.Pixy2.Pixy2Resolution;
import frc.robot.lib.vision.pixy2.Pixy2.Pixy2Version;
import frc.robot.lib.vision.pixy2.Pixy2.RequestResponseType;
import edu.wpi.first.wpilibj.SPI.Port;;

/** Add your docs here. */
public class Pixy2Spi implements Pixy2Type {

    private static final int BYTES_PER_BLOCK = 14;

    private final SPI pixy;

    public Pixy2Spi(Port port) {
        this(new SPI(port));
    }

    public Pixy2Spi(SPI spi) {
        pixy = spi;
    }

    private Pixy2RequestSPI createRequest(final RequestResponseType type, final int length) {
        return new Pixy2RequestSPI(pixy, type, length);
    }

    @Override
    public Pixy2Version getVersion() {
        final var request = createRequest(RequestResponseType.GetVersion, 0);
        final var response = request.send();

        return new Pixy2Version(response.toNormal());
    }

    @Override
    public Pixy2Resolution getResolution() {
        final var request = createRequest(RequestResponseType.GetResolution, 1);
        request.buffer.put((byte) 0); // Unused, reserved for future pixy versions

        final var response = request.send();
        return new Pixy2Resolution(response.toNormal());
    }

    @Override
    public long setCameraBrightness(short brightness) {
        final var request = createRequest(RequestResponseType.SetCameraBrightness, 1);
        request.buffer.putShort(brightness);

        final var response = request.send();
        return response.readShort();
    }

    @Override
    public long setServos(short servo1, short servo2) {
        final var request = createRequest(RequestResponseType.SetServos, 4);
        request.buffer.putShort(servo1);
        request.buffer.putShort(servo2);

        final var response = request.send();
        return response.readShort();
    }

    @Override
    public long setLED(Color color) {
        final var request = createRequest(RequestResponseType.SetLED, 3);

        request.buffer.put((byte) color.getRed());
        request.buffer.put((byte) color.getGreen());
        request.buffer.put((byte) color.getBlue());

        final var response = request.send();
        return response.readShort();
    }

    @Override
    public long setLamp(boolean upper, boolean lower) {
        final var request = createRequest(RequestResponseType.SetLamp, 2);
        request.buffer.put((byte) (upper ? 1 : 0));
        request.buffer.put((byte) (lower ? 1 : 0));

        final var response = request.send();
        return response.readShort();
    }

    @Override
    public long getFPS() {
        final var request = createRequest(RequestResponseType.GetFPS, 0);
        final var response = request.send();
        return response.readShort();
    }

    @Override
    public Pixy2Block[] getBlocks(byte signatureBitField, byte maxReturnBlocks) {
        final var request = createRequest(RequestResponseType.GetBlocks, 2);
        request.buffer.put(signatureBitField);
        request.buffer.put(maxReturnBlocks);

        final var response = request.send();

        final var numberOfBlocks = response.buffer.remaining() / BYTES_PER_BLOCK;
        final var blocks = new Pixy2Block[numberOfBlocks];

        for (var currentBlock = 0; currentBlock < numberOfBlocks; currentBlock++) {
            blocks[currentBlock] = new Pixy2Block(response.toNormal());
        }

        return blocks;
    }

}
