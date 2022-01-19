package frc.robot.lib.vision.pixy2;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import edu.wpi.first.wpilibj.I2C;
import frc.robot.lib.vision.pixy2.Pixy2.RequestResponseType;

public class Pixy2Request {
    private static final int REQUEST_HEADER_SIZE = 4;
    private static final int REQUEST_SYNC = 0xc1ae;

    private final I2C pixy;
    private final RequestResponseType type;

    public final ByteBuffer buffer;

    public Pixy2Request(final I2C pixy, final RequestResponseType type, final int length) {
        this.pixy = pixy;
        this.type = type;

        // Create a buffer for the header
        buffer = ByteBuffer.allocate(REQUEST_HEADER_SIZE + length);
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        // Fill in the buffer
        buffer.putShort((short) REQUEST_SYNC); // Magic number
        buffer.put((byte) type.requestOpcode); // Request opcode
        buffer.put((byte) length); // Length
    }

    public Pixy2Response send() {
        pixy.writeBulk(buffer, buffer.capacity());
        return new Pixy2Response(pixy, type);
    }
}
