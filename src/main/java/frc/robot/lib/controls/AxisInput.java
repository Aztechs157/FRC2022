package frc.robot.lib.controls;

import edu.wpi.first.math.MathUtil;

/**
 * Interface for getting input from a axis. This class has methods and static
 * methods to modify and compose {@link AxisInput}s into a new
 * {@link AxisInput}.
 */
@FunctionalInterface
public interface AxisInput {

    /**
     * Get an input from this {@link AxisInput}
     *
     * @return The number representing the input
     */
    public double get();

    /**
     * Inverts the input by negating the number's sign
     *
     * @return A new inverted input
     */
    public default AxisInput inverted() {
        return () -> -get();
    }

    /**
     *
     * @param minimum
     * @param maximum
     * @return
     */
    public default AxisInput clamp(final double minimum, final double maximum) {
        return () -> MathUtil.clamp(get(), minimum, minimum);
    }

    /**
     *
     * @param threshold
     * @return
     */
    public default AxisInput deadzone(final double threshold) {
        return () -> {
            final var value = get();

            if (Math.abs(value) < threshold) {
                return 0;
            }

            return value;
        };
    }
}
