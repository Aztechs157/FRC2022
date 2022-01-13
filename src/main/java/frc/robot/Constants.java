// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // constant names subject to change
    public final class Compressor {
        public static final int COMPRESSOR_ID = 8;
    }

    public final class Intake {
        public static final int INTAKESOLENOID_ID = 0;
        public static final int INTAKEMOTOR_ID = 0;
        public static final int COLORSENSOR_ID = 0;
    }

    public final class TurretAiming {
        public static final int TURRETMOTOR_ID = 0;
        public static final int POSITIONSENSOR_ID = 0;
        public static final int AIMERSERVO_ID = 0;
    }

    public final class Shooter {
        public final static int SHOOTERMOTOR1_ID = 0;
        public final static int SHOOTERMOTOR2_ID = 0;
    }

    public final class Uptake {

    }
}
