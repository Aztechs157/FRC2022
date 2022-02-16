package frc.robot.lib.controls;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.button.Button;

/**
 * Interface for getting input from a button. This class has methods and static
 * methods to modify and compose {@link ButtonInput}s into a new
 * {@link ButtonInput}.
 */
public class ButtonInput extends Button {

    public ButtonInput(final BooleanSupplier isPressed) {
        super(isPressed);
    }

    /**
     * Inverts the input; similar to a boolean `!`
     *
     * @return A new inverted input
     */
    public ButtonInput inverted() {
        return new ButtonInput(() -> !get());
    }

    /**
     * Checks that all inputs are true; similar to a boolean `&&`
     *
     * @param first The first input
     * @param rest  The rest of the inputs
     * @return A new input that is only true when all of the passed inputs are true
     */
    public static ButtonInput all(final ButtonInput first, final ButtonInput... rest) {
        return new ButtonInput(() -> {
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
    public static ButtonInput any(final ButtonInput first, final ButtonInput... rest) {
        return new ButtonInput(() -> {
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
