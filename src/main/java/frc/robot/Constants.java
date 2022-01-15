// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.util.Color;

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

    public static final class CompressorConstants {
        // the can ID the rev pneumatics hub is attached to.
        public static final int COMPRESSOR_ID = 8;
    }

    public static final class IntakeConstants {
        // the can ID the motor controlling the intake motor and conveyor.
        public static final int INTAKE_CONVERYER_MOTOR_ID = 0;
        // the I2C port that the color sensor is connected to.
        public static final Port COLORSENSOR_ID = Port.kMXP;
        // the rev pneumatics hub port that the forward channel of the solenoid is
        // connected to.
        public static final int SOLENOIDREVERSE_ID = 0;
        // the rev pneumatics hub port that the reverse channel of the solenoid is
        // connected to.
        public static final int SOLENOIDFORWARD_ID = 0;
        // the power the Intake rollers are set to to feed a ball.
        public static final double FEED_SPEED = 0.2;
        // the power the Intake rollers are set to to eject a ball.
        public static final double EJECT_SPEED = 0.2;
        // The red ball target color.
        public static final Color REDTARGET = new Color(0, 0, 0);
        // The blue ball target color.
        public static final Color BLUETARGET = new Color(0, 0, 0);
        // The minimum confidence to be read as the right color.
        public static final double COLORCONFIDENCE = 0.9;
    }

    public static final class TurretConstants {
        public static final int TURRETMOTOR_ID = 0;
        public static final int POSITIONSENSOR_ID = 0;
        public static final int AIMERSERVO_ID = 0;
    }

    public static final class ShooterConstants {
        public final static int SHOOTERMOTOR1_ID = 0;
        public final static int SHOOTERMOTOR2_ID = 0;
        public final static double EJECT_SPEED = 0.2;
    }

    public static final class KickerConstants {
        public static final int KICKERMOTOR_ID = 0;
        public static final int KICKERSENSOR_ID = 0;
        public static final double FEED_SPEED = 0;
        public static final double EJECT_SPEED = 0;
    }
}
