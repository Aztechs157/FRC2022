package frc.robot.lib.controls;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import edu.wpi.first.math.MathUtil;

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

    public default AxisInput scaled(final double scale) {
        return () -> getAsDouble() * scale;
    }

    public default AxisInput scaled(final DoubleSupplier scale) {
        return () -> getAsDouble() * scale.getAsDouble();
    }

    /**
     *
     * @param minimum
     * @param maximum
     * @return
     */
    public default AxisInput clamp(final double minimum, final double maximum) {
        return () -> MathUtil.clamp(getAsDouble(), minimum, minimum);
    }

    /**
     *
     * @param threshold
     * @return
     */
    public default AxisInput deadzone(final double threshold) {
        return () -> {
            final var value = getAsDouble();

            if (Math.abs(value) < threshold) {
                return 0;
            }

            return value;
        };
    }
}
