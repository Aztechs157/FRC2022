// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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
        // the CAN ID the rev pneumatics hub is attached to.
        public static final int COMPRESSOR_ID = 21;
    }

    public static final class IntakeConstants {
        // the CAN ID for the motor controlling the intake motor and conveyor. (TEMP)
        public static final int INTAKE_MOTOR_ID = 11;
        // the I2C port that the color sensor is connected to.
        public static final Port COLOR_SENSOR_ID = Port.kMXP;
        // the rev pneumatics hub port that the forward channel of the solenoid is
        // connected to.
        public static final int SOLENOID_REVERSE_ID = 3;
        // the rev pneumatics hub port that the reverse channel of the solenoid is
        // connected to.
        public static final int SOLENOID_FORWARD_ID = 2;
        // the power the Intake rollers are set to to feed a ball.
        public static final double FEED_SPEED = .8;
        // the power the Intake rollers are set to to eject a ball.
        public static final double EJECT_SPEED = -0.3;
        // The red ball target color.
        public static final Color RED_TARGET = new Color(0.326, 0.464, 0.209);
        // The blue ball target color.
        public static final Color BLUE_TARGET = new Color(0.273, 0.476, 0.249);
        // The minimum confidence to be read as the right color. How much leeway the
        // color sensor can have
        public static final double COLOR_CONFIDENCE = 0.9;

        public static final int PROX_CLOSE = 180;

        public static final int PROX_FAR = 120;
    }

    public static final class TurretConstants {
        // The CAN ID the turret motor is connected to. (TEMP)
        public static final int TURRET_MOTOR_ID = 6;
        // The Analog Input ID that the position sensor for the turret is
        // connected to.
        public static final int POSITION_SENSOR_ID = 0;
        // The servo ID for the Aimer mechanism.
        public static final int AIMER_MOTOR_ID = 7;
        // The upwards boundary for the aimer
        public static final double AIMER_HIGHER_BOUNDARY = 500;
        // The lower boundary for the aimer
        public static final double AIMER_LOWER_BOUNDARY = 65;
        // The port number for the absolute encoder.
        public static final int ABS_ENCODER_PORT = 1;
        // The speed for the aimer motor.
        public static final double AIMER_SPEED = .25;
        // The Counterclockwise Boundary for the turret set to 2620
        public static final int COUNTERCLOCKWISE_BOUNDARY = 2620;
        // The Clockwise Boundary for the turret 1250
        public static final int CLOCKWISE_BOUNDARY = 1250;
        // The rotational speed of the turret
        public static final double TURRET_SPEED = .45;
        // diagonal max 90, min 45

    }

    public static final class ShooterConstants {
        // The CAN ID for the 1st Shooter Motor. (TEMP)
        public final static int SHOOTER_MOTOR1_ID = 8;
        // The motor speed to eject a ball through the shooting mechanism. (specifically
        // dispelling, not shooting at a high speed!)
        public final static double EJECT_SPEED = 0.3;
    }

    public static final class KickerConstants {
        // The CAN ID for the Kicker Motor.
        public static final int KICKER_MOTOR_ID = 9;
        // The Digital Input ID for the Kicker Sensor.
        public static final int KICKER_SENSOR_ID = 0;
        // The speed at which the Kicker feeds a ball into the Shooter.
        public static final double FEED_SPEED = 1;
        // The speed at which the Kicker ejects a ball back to the Intake.
        public static final double EJECT_SPEED = 0.3;
    }

    public static final class UptakeConstants {
        // The CAN ID for the Uptake Motor.
        public static final int UPTAKE_MOTOR_ID = 10;
        // The speed at which the Uptake feeds a ball into the Kicker.
        public static final double FEED_SPEED = 0.8;
        // The speed at which the Uptake ejects a ball back through the Intake.
        public static final double EJECT_SPEED = -0.3;
    }

    public static final class DriveConstants {
        public static final int FRONT_LEFT_MOTOR_ID = 18;
        public static final int BACK_LEFT_MOTOR_ID = 19;
        public static final int FRONT_RIGHT_MOTOR_ID = 61;
        public static final int BACK_RIGHT_MOTOR_ID = 60;
    }

    public static final class AutoConstants {
        public static final double AUTO_SPEED = 0.2;
        public static final double AUTO_DISTANCE_TICKS = 19.72;
    }

    public enum MotorDirection {
        FEEDING, EJECTING, STOPPED
    }
}
