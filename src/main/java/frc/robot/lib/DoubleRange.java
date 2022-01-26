package frc.robot.lib;

public class DoubleRange {
    public final double low;
    public final double high;

    public DoubleRange(final double low, final double high) {
        this.low = low;
        this.high = high;
    }

    public boolean contains(final double value) {
        return low <= value && value <= high;
    }

    public double length() {
        return high - low;
    }

    /*
     * scale(inputRange, value, outputRange)
     *
     * inputRange.scale(value, outputRange)
     */
    public static double scale(final DoubleRange inputRange, final double inputValue, final DoubleRange outputRange) {
        // return (((value - inputRange.high) * outputRange.length()) /
        // inputRange.length()) + outputRange.high;

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
