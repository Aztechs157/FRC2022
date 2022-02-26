package frc.robot.lib.vision.pixy2;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.lib.vision.pixy2.Pixy2.RequestResponseType;

import static frc.robot.lib.NumberUtil.unsign;

public class Pixy2ResponseSPI {
    private static final int RESPONSE_HEADER_SIZE = 6 * 2;
    private static final int RESPONSE_SYNC = 0xc1af;
    private static final int MAX_UNSIGNED_SHORT = 0xffff;

    public final ByteBuffer buffer;

    public Pixy2ResponseSPI(final SPI pixy, final RequestResponseType type) {
        // Read just the header of the response
        final var header = ByteBuffer.allocate(RESPONSE_HEADER_SIZE);
        header.order(ByteOrder.LITTLE_ENDIAN);
        pixy.read(false, header, RESPONSE_HEADER_SIZE);

        var temp = header.array();
        for (byte b : temp) {
            System.out.println(Integer.toHexString(b));
        }

        // Check the sync value
        {
            final var sync = unsign(header.getShort());
            if (sync != RESPONSE_SYNC) {
                throw new Pixy2Exception(
                        "Pixy2: Fetched response has an invalid sync (expected: " + RESPONSE_SYNC + ", got:"
                                + sync + ")");
            }
        }

        // Check the received type
        {
            final var receivedType = unsign(header.get());
            if (receivedType != type.responseOpcode) {
                throw new Pixy2Exception(
                        "Pixy2: Fetched response type didn't match (expected: " + type.responseOpcode + ", got:"
                                + receivedType + ")");
            }
        }

        final var length = unsign(header.get());
        final var checksum = unsign(header.getShort());

        // Read the rest of the response
        buffer = ByteBuffer.allocate(length);
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        // If no payload then don't bother reading/checksum-ing
        if (length != 0) {
            pixy.read(true, buffer, length);

            // Checksum
            var sum = 0;

            // Checksum is defined as sum of all bytes of payload
            for (final var part : buffer.array()) {
                sum += unsign(part);
                sum %= MAX_UNSIGNED_SHORT;
            }

            if (checksum != sum) {
                throw new Pixy2Exception(
                        "Pixy2: Response checksum didn't match (expected: " + checksum + ", got:" + sum + ")");
            }

        }
    }

    public int readByte() {
        return unsign(buffer.get());
    }

    public int readShort() {
        return unsign(buffer.getShort());
    }

    public long readInt() {
        return unsign(buffer.getInt());
    }

    public Pixy2Response toNormal() {
        return new Pixy2Response(buffer);
    }
}
