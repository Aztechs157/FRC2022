package frc.robot.lib.input.axis;

public interface AxisKey {
    public default boolean optional() {
        return false;
    }

    public interface Optional extends AxisKey {
        @Override
        public default boolean optional() {
            return true;
        }
    }
}
