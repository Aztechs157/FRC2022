package frc.robot.lib.input;

import java.util.function.IntSupplier;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * Class for getting input from a pov.
 */
public class Pov implements IntSupplier {
    public interface Key {
    }

    private final IntSupplier degrees;

    public Pov(final int deviceId, final int povId) {
        this(() -> DriverStation.getStickPOV(deviceId, povId));
    }

    public Pov(final IntSupplier degrees) {
        this.degrees = degrees;
    }

    @Override
    public int getAsInt() {
        return degrees.getAsInt();
    }

    public int get() {
        return degrees.getAsInt();
    }

    public static final int CENTER = -1;
    public static final int UP = 45 * 0;
    public static final int UP_RIGHT = 45 * 1;
    public static final int RIGHT = 45 * 2;
    public static final int DOWN_RIGHT = 45 * 3;
    public static final int DOWN = 45 * 4;
    public static final int DOWN_LEFT = 45 * 5;
    public static final int LEFT = 45 * 6;
    public static final int UP_LEFT = 45 * 7;

    public static final int DEFAULT_VALUE = CENTER;
    public static final Pov DEFAULT = new Pov(() -> DEFAULT_VALUE);

    public Button matchesValue(final int degrees) {
        return new Button(() -> get() == degrees);
    }

    public final Button center = matchesValue(CENTER);
    public final Button up = matchesValue(UP);
    public final Button upRight = matchesValue(UP_RIGHT);
    public final Button right = matchesValue(RIGHT);
    public final Button downRight = matchesValue(DOWN_RIGHT);
    public final Button down = matchesValue(DOWN);
    public final Button downLeft = matchesValue(DOWN_LEFT);
    public final Button left = matchesValue(LEFT);
    public final Button upLeft = matchesValue(UP_LEFT);

    public final Axis x = new Axis(() -> {
        final var value = get();
        if (value == CENTER) {
            return 0;
        }
        return Math.round(Math.sin(Math.toRadians(value)));
    });

    public final Axis y = new Axis(() -> {
        final var value = get();
        if (value == CENTER) {
            return 0;
        }
        return Math.round(Math.cos(Math.toRadians(value)));
    });
}
