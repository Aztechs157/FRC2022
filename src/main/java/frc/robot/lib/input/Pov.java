package frc.robot.lib.input;

import java.util.function.IntSupplier;

import edu.wpi.first.wpilibj.DriverStation;

public class Pov implements IntSupplier {
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

    public static final int DEFAULT_VALUE = -1;
    public static final Pov DEFAULT = new Pov(() -> DEFAULT_VALUE);

    public Button matchesValue(final int degrees) {
        return new Button(() -> get() == degrees);
    }

    public final Button center = matchesValue(-1);
    public final Button up = matchesValue(45 * 0);
    public final Button upRight = matchesValue(45 * 1);
    public final Button right = matchesValue(45 * 2);
    public final Button downRight = matchesValue(45 * 3);
    public final Button down = matchesValue(45 * 4);
    public final Button downLeft = matchesValue(45 * 5);
    public final Button left = matchesValue(45 * 6);
    public final Button upLeft = matchesValue(45 * 7);

    public final Axis x = new Axis(() -> Math.cos(Math.toRadians(get())));
    public final Axis y = new Axis(() -> Math.sin(Math.toRadians(get())));
}
