package frc.robot.lib;

public class DoubleRange {
    public final double low;
    public final double high;

    public DoubleRange(final double low, final double high) {
        this.low = low;
        this.high = high;
    }

    /**
     * Check if a value is contained within the range.
     *
     * @param value The value to check
     * @return The result of the check
     */
    public boolean contains(final double value) {
        return low <= value && value <= high;
    }

    /**
     * Get the length of the range.
     *
     * Specifically, the difference between the high and the low of the range. Also
     * known as the "range" of the range.
     *
     * @return The length of the range
     */
    public double length() {
        return high - low;
    }

    /**
     * Clamp a value to a number within this range.
     *
     * Specifically, any numbers lower than `this.low` will result in `this.low`,
     * any numbers higher than `this.high` will result in `this.high`, and anything
     * in between isn't modified.
     *
     * @param value The value to clamp
     * @return The clamped value
     */
    public double applyClamp(final double value) {
        return Math.max(low, Math.min(value, high));
    }

    /*
     * Scale a number within `inputRange` to the corresponding number within
     * `outputRange`.
     *
     * Example: Let input range be 0 to 2, and the output range be 0 to 10. The
     * input 1 will result in an output 5, the input 2 will result in 10, etc.
     */
    public static double scale(final DoubleRange inputRange, final double inputValue, final DoubleRange outputRange) {
        // scale
        var scale = outputRange.length() / inputRange.length();

        // zero base the input
        var outValue = inputValue - inputRange.low;

        // apply scale to value
        outValue = outValue * scale;

        // shift output back
        outValue = outValue + outputRange.low;

        return outValue;
    }
}
