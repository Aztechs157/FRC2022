// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.lib.util.DoubleRange;

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
        // close proximity for the intake color sensor
        public static final int PROX_CLOSE = 270;
        // far proximity for the intake color sensor
        public static final int PROX_FAR = 120;
    }

    public static final class TurretConstants {
        // The CAN ID the turret motor is connected to. (TEMP)
        public static final int TURRET_MOTOR_ID = 6;
        // The Analog Input ID that the position sensor for the turret is
        // connected to.
        public static final int POSITION_SENSOR_ID = 0;
        // The servo ID for the Aimer mechanism.
        // public static final int AIMER_MOTOR_ID = 7;
        // The upwards boundary for the aimer, old = 297
        // public static final double AIMER_HIGHER_BOUNDARY = 2975;
        // original value for absolute encoder: 307;
        // The lower boundary for the aimer, old = 10
        // public static final double AIMER_LOWER_BOUNDARY = 1695;
        // original value for absolute encoder: 47;
        // The port number for the 3 turn pot encoder.
        // public static final int AIMER_ENCODER_PORT = 1;
        // The speed for the aimer motor.
        // public static final double AIMER_SPEED = .3;
        // The Counterclockwise Boundary for the turret set to 2620
        public static final int COUNTERCLOCKWISE_BOUNDARY = 2620;
        // The Clockwise Boundary for the turret 1250
        public static final int CLOCKWISE_BOUNDARY = 1250;
        // The rotational speed of the turret
        public static final double TURRET_SPEED = .45;
        // turret speed scale
        public static final double TURRET_INPUT_SCALE = 0.5;
        // diagonal max 90, min 45

        public static final DoubleRange TURRET_CENTER_POS = new DoubleRange(1920, 2020);
    }

    public static final class ShooterConstants {
        // The CAN ID for the 1st Shooter Motor. (TEMP)
        public static final int SHOOTER_MOTOR1_ID = 8;
        public static final int SHOOTER_MOTOR2_ID = 7;
        // The motor speed to eject a ball through the shooting mechanism. (specifically
        // dispelling, not shooting at a high speed!)
        public static final double EJECT_SPEED = 0.45;
        // shooter rpm for top hub
        public static final double SHOOTER_RPM = 3920;
        // shooter rpm for bottom hub
        public static final double LOW_SHOOTER_RPM = 2000;
    }

    public static final class KickerConstants {
        // The CAN ID for the Kicker Motor.
        public static final int KICKER_MOTOR_ID = 9;
        // The Digital Input ID for the Kicker Sensor.
        public static final int KICKER_SENSOR_ID = 0;
        // The speed at which the Kicker feeds a ball into the Shooter.
        public static final double FEED_SPEED = 1;
        // The speed at which the Kicker ejects a ball back to the Intake.
        public static final double EJECT_SPEED = -0.3;
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
        // drive speed scale
        public static final double DRIVE_INPUT_SCALE = 0.7;
        // ids for the drive motors
        public static final int FRONT_LEFT_MOTOR_ID = 18;
        public static final int BACK_LEFT_MOTOR_ID = 19;
        public static final int FRONT_RIGHT_MOTOR_ID = 61;
        public static final int BACK_RIGHT_MOTOR_ID = 60;

        public static final DoubleRange DRIVE_DEADZONE = new DoubleRange(-0.05, 0.05);
    }

    public static final class HangingConstants {
        // ids for the hanging mechanisms
        public static final int ROTATE_MOTOR_RIGHT = 15;
        public static final int ROTATE_MOTOR_LEFT = 4;
        public static final int RIGHT_EXTEND_MOTOR = 5;
        public static final int LEFT_EXTEND_MOTOR = 17;

        public static final int TOP_LEFT_LIMIT_SWITCH = 8;
        public static final int BOTTOM_LEFT_LIMIT_SWITCH = 9;
        public static final int TOP_RIGHT_LIMIT_SWITCH = 7;
        public static final int BOTTOM_RIGHT_LIMIT_SWITCH = 6;

        public static final int ABS_HANGING_ROTATION_LEFT = 2;
        public static final int ABS_HANGING_ROTATION_RIGHT = 3;
        // limit switch position limits
        public static final double ROTATE_MAX_POS_RIGHT = 208;
        public static final double ROTATE_MIN_POS_RIGHT = 90;
        public static final double ROTATE_MAX_POS_LEFT = 150;
        public static final double ROTATE_MIN_POS_LEFT = 248;
        // arm rotation speed (NOT USED)
        public static final double ROTATION_SPEED = 1;
        // arm extension speed
        // make faster
        public static final double EXTEND_SPEED_LEFT = .90;
        public static final double EXTEND_SPEED_RIGHT = .88;
        public static final double RETRACT_SPEED = -1;
        // how far the rotate arms have to be to allow the turret to move
        public static final double ROTATE_TURRET_SAFE_POS_RIGHT = 50;
        public static final double ROTATE_TURRET_SAFE_POS_LEFT = 280;
    }

    public static final class AutoConstants {
        // autonomous drive speed
        public static final double AUTO_SPEED = 0.60;

        // autonomous drive distance
        public static final double AUTO_DISTANCE_TICKS = 23.72;
        // find cargo x-target scalar
        public static final int X_TARGET = 157;
        // gyro degrees for turning the robot
        public static final double TURN_DEGREES = 150;
    }

    public static final class VisionConstants {
        // port for the pixy camera
        public static final int PIXY_PORT = 0x55;
        // blue and red binary values for cargo tracking
        public static final byte RED = 0b00000001;
        public static final byte BLUE = 0b00000010;
    }

    public static final class MiscConstants {
        // smart motor current limit to hopefully stop brown-outs
        public static final int SMART_MOTOR_LIMIT = 40;
        public static final int REDUCED_MOTOR_LIMIT = 30;
    }

    // motor direction enum
    public enum MotorDirection {
        FEEDING, EJECTING, STOPPED
    }
}
