package frc.robot.lib.controls;

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

    /**
     * Create a artificial axis that will emit `value` either positive, negative, or
     * zero depending on two respective button inputs. When neither or both buttons
     * are active, the axis has a value of zero.
     *
     * @param value          The value to emit.
     * @param positiveButton When this button is active, the axis becomes the value
     *                       as is.
     * @param negativeButton When this button is active, the axis becomes the value
     *                       inverted.
     * @return The value, made to be positive, negative, or zero.
     */
    public static AxisInput fromButtons(
            final double value,
            final ButtonInput positiveButton,
            final ButtonInput negativeButton) {
        return () -> {
            var positivePressed = positiveButton.getAsBoolean();
            var negativePressed = negativeButton.getAsBoolean();

            if (positivePressed && negativePressed) {
                return 0.0;
            } else if (positivePressed) {
                return value;
            } else if (negativePressed) {
                return -value;
            } else {
                return 0.0;
            }
        };
    }
}
