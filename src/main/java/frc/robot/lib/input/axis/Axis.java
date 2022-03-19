package frc.robot.lib.input.axis;

import static frc.robot.lib.util.DoubleRange.scale;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.lib.util.DoubleRange;

/**
 * Class for getting input from a axis. This class has methods and static
 * methods to modify and compose {@link Axis}s into a new
 * {@link Axis}.
 */
public class Axis implements DoubleSupplier {
    private final DoubleSupplier value;

    public Axis(final int deviceId, final int axisId) {
        this(() -> DriverStation.getStickAxis(deviceId, axisId));
    }

    public Axis(final DoubleSupplier value) {
        this.value = value;
    }

    @Override
    public double getAsDouble() {
        return value.getAsDouble();
    }

    public double get() {
        return value.getAsDouble();
    }

    public static final double DEFAULT_VALUE = 0;
    public static final DoubleRange DEFAULT_RANGE = new DoubleRange(-1, 1);
    public static final Axis DEFAULT = new Axis(() -> DEFAULT_VALUE);

    /**
     * Inverts the input by negating the number's sign
     *
     * @return A new inverted input
     */
    public Axis inverted() {
        return new Axis(() -> -get());
    }

    /**
     * Scale the input with a scalar value.
     *
     * @param scale The value to scale by
     * @return A new input with the scale applied
     */
    public Axis scaled(final double scale) {
        return new Axis(() -> get() * scale);
    }

    /**
     * Scale the input with another input.
     *
     * @param scale The input to retrieve the scale from
     * @return A new input with the scale applied
     */
    public Axis scaled(final DoubleSupplier scale) {
        return new Axis(() -> get() * scale.getAsDouble());
    }

    public Axis offset(final double offset) {
        return new Axis(() -> get() + offset);
    }

    public Axis offset(final DoubleSupplier offset) {
        return new Axis(() -> get() + offset.getAsDouble());
    }

    /**
     * Clamp the input to a number within the provided range.
     *
     * @param range The range to clamp to
     * @return A new input with clamp applied
     */
    public Axis clamp(final DoubleRange range) {
        return new Axis(() -> range.applyClamp(get()));
    }

    /**
     * Create a deadzone on the current axis.
     *
     * Any value inside the deadzone will result in 0. Values to the left or right
     * will be scaled from 0 to their respective end. This code assumes the axis is
     * in range of -1 to 1, breaking this assumption results in an Error.
     *
     * @param deadzone The range of the deadzone
     * @return A new input with the deadzone applied
     */
    public Axis deadzone(final DoubleRange deadzone) {
        return deadzone(deadzone, DEFAULT_RANGE, DEFAULT_VALUE);
    }

    public Axis deadzone(final DoubleRange deadzone, final DoubleRange fullRange, final double center) {
        final var leftFullRange = new DoubleRange(fullRange.low, center);
        final var rightFullRange = new DoubleRange(center, fullRange.high);

        final var leftDeadzoneRange = new DoubleRange(fullRange.low, deadzone.low);
        final var rightDeadzoneRange = new DoubleRange(deadzone.high, fullRange.high);

        return new Axis(() -> {
            final var value = get();

            if (deadzone.contains(value)) {
                return 0;

            } else if (leftDeadzoneRange.contains(value)) {
                return scale(leftDeadzoneRange, value, leftFullRange);

            } else if (rightDeadzoneRange.contains(value)) {
                return scale(rightDeadzoneRange, value, rightFullRange);
            }

            throw new Error("Attempted to apply deadzone to axis value outside of full range "
                    + fullRange.low + " to " + fullRange.high);
        });
    }
}
