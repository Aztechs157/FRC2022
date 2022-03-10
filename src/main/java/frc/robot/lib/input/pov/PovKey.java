package frc.robot.lib.input.pov;

public interface PovKey {
    public default boolean optional() {
        return false;
    }

    public interface Optional extends PovKey {
        @Override
        public default boolean optional() {
            return true;
        }
    }
}
