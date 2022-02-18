package frc.robot.lib.util;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;

public class ShuffleUtil {
    public static BooleanSupplier booleanInput(
            final ShuffleboardTab tab,
            final String title,
            final boolean defaultValue) {

        final var entry = tab.add(title, defaultValue).getEntry();
        return () -> entry.getBoolean(defaultValue);
    }

    public static DoubleSupplier numberInput(
            final ShuffleboardTab tab,
            final String title,
            final double defaultValue) {

        final var entry = tab.add(title, defaultValue).getEntry();
        return () -> entry.getDouble(defaultValue);
    }

    public static Supplier<String> stringInput(
            final ShuffleboardTab tab,
            final String title,
            final String defaultValue) {

        final var entry = tab.add(title, defaultValue).getEntry();
        return () -> entry.getString(defaultValue);
    }
}
