package frc.robot.lib.controls;

import java.util.function.DoubleSupplier;
import frc.robot.lib.DoubleRange;
import static frc.robot.lib.DoubleRange.scale;

/**
 * Interface for getting input from a axis. This class has methods and static
 * methods to modify and compose {@link AxisInput}s into a new
 * {@link AxisInput}.
 */
@FunctionalInterface
public interface AxisInput extends DoubleSupplier {

    /**
     * Create a {@link AxisInput} using a {@link DoubleSupplier}
     *
     * @param supplier
     * @return
     */
    public static AxisInput wrap(final DoubleSupplier supplier) {
        return supplier::getAsDouble;
    }

    /**
     * Inverts the input by negating the number's sign
     *
     * @return A new inverted input
     */
    public default AxisInput inverted() {
        return () -> -getAsDouble();
    }

    /**
     * Scale the input with a scalar value.
     *
     * @param scale The value to scale by
     * @return A new input with the scale applied
     */
    public default AxisInput scaled(final double scale) {
        return () -> getAsDouble() * scale;
    }

    /**
     * Scale the input with another input.
     *
     * @param scale The input to retrieve the scale from
     * @return A new input with the scale applied
     */
    public default AxisInput scaled(final DoubleSupplier scale) {
        return () -> getAsDouble() * scale.getAsDouble();
    }

    /**
     * Clamp the input to a number within the provided range.
     *
     * @param range The range to clamp to
     * @return A new input with clamp applied
     */
    public default AxisInput clamp(final DoubleRange range) {
        return () -> range.applyClamp(getAsDouble());
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
    public default AxisInput deadzone(final DoubleRange deadzone) {
        final var fullRange = new DoubleRange(-1, 1);
        final var leftRange = new DoubleRange(fullRange.low, deadzone.low);
        final var rightRange = new DoubleRange(deadzone.high, fullRange.high);

        return () -> {
            final var value = getAsDouble();

            if (deadzone.contains(value)) {
                return 0;
            } else if (leftRange.contains(value)) {
                return scale(new DoubleRange(-1, deadzone.low), value, new DoubleRange(-1, 0));
            } else if (rightRange.contains(value)) {
                return scale(new DoubleRange(deadzone.high, 1), value, new DoubleRange(0, 1));
            }

            throw new Error("Attempted to apply deadzone to axis value outside of full range "
                    + fullRange.low + " to " + fullRange.high);
        };
    }
}
