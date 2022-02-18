package frc.robot.lib.controls;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * Interface for getting input from a button. This class has methods and static
 * methods to modify and compose {@link Button}s into a new
 * {@link Button}.
 */
public class Button extends edu.wpi.first.wpilibj2.command.button.Button {
    public interface Key {
    }

    public Button(final int deviceId, final int buttonId) {
        this(() -> DriverStation.getStickButton(deviceId, buttonId));
    }

    public Button(final BooleanSupplier isPressed) {
        super(isPressed);
    }

    public static final boolean DEFAULT_VALUE = false;
    public static final Button DEFAULT = new Button(() -> DEFAULT_VALUE);

    /**
     * Inverts the input; similar to a boolean `!`
     *
     * @return A new inverted input
     */
    public Button inverted() {
        return new Button(() -> !get());
    }

    /**
     * Checks that all inputs are true; similar to a boolean `&&`
     *
     * @param first The first input
     * @param rest  The rest of the inputs
     * @return A new input that is only true when all of the passed inputs are true
     */
    public static Button all(final Button first, final Button... rest) {
        return new Button(() -> {
            // Check each input individually
            // As soon as one input is false, return false
            // The first argument is explicit to prevent being given empty arrays

            if (first.get() == false) {
                return false;
            }

            for (final var input : rest) {
                if (input.get() == false) {
                    return false;
                }
            }

            // All inputs are true at this point, so return true
            return true;
        });
    }

    /**
     * Checks that any input is true; similar to a boolean `||`
     *
     * @param first The first input
     * @param rest  The rest of the inputs
     * @return A new input that is true when any of the passed inputs are true
     */
    public static Button any(final Button first, final Button... rest) {
        return new Button(() -> {
            // Check each input individually
            // As soon as one input is true, return true
            // The first argument is explicit to prevent being given empty arrays

            if (first.get()) {
                return true;
            }

            for (final var input : rest) {
                if (input.get()) {
                    return true;
                }
            }

            // All inputs are false at this point, so return false
            return false;
        });
    }
}
