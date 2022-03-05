// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.hanging;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Counter.Mode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.HangingConstants;
import frc.robot.Constants.MiscConstants;
import frc.robot.lib.NumberUtil;

public class Hanging extends SubsystemBase {
    private CANSparkMax leftExtendMotor;
    private CANSparkMax rotateMotors;
    private CANSparkMax rightExtendMotor;
    private DigitalInput topLimitSwitch;
    private DigitalInput bottomLimitSwitch;
    private Counter absHangingRotation;

    /** Creates a new Hanging. */
    public Hanging() {
        leftExtendMotor = new CANSparkMax(HangingConstants.LEFT_EXTEND_MOTOR, MotorType.kBrushless);
        rotateMotors = new CANSparkMax(HangingConstants.ROTATE_MOTOR, MotorType.kBrushless);
        rightExtendMotor = new CANSparkMax(HangingConstants.RIGHT_EXTEND_MOTOR, MotorType.kBrushless);
        topLimitSwitch = new DigitalInput(HangingConstants.TOP_LIMIT_SWITCH);
        bottomLimitSwitch = new DigitalInput(HangingConstants.BOTTOM_LIMIT_SWITCH);
        absHangingRotation = new Counter(Mode.kSemiperiod);
        absHangingRotation.setSemiPeriodMode(true);
        absHangingRotation.setUpSource(HangingConstants.ABS_HANGING_ROTATION);
        absHangingRotation.reset();
        rotateMotors.setSmartCurrentLimit(MiscConstants.SMART_MOTOR_LIMIT);
        leftExtendMotor.setSmartCurrentLimit(MiscConstants.SMART_MOTOR_LIMIT);
        rightExtendMotor.setSmartCurrentLimit(MiscConstants.SMART_MOTOR_LIMIT);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    /**
     * This method rotates the hanger arms either direction.
     *
     * @param speed is the rotation speed.
     */
    public void rotateArms(double speed) {
        if (speed > 0 && getAbsEncoder() > HangingConstants.MAX_POS) {
            rotateMotors.set(0);
        } else if (speed < 0 && getAbsEncoder() < HangingConstants.MIN_POS) {
            rotateMotors.set(0);
        } else {
            rotateMotors.set(speed);
        }
    }

    /**
     * This method extends the hanger arms outwards or inwards.
     *
     * @param speed is the speed of extension.
     */
    public void extendArms(double speed) {
        if (speed > 0 && getTopLimit()) {
            rightExtendMotor.set(0);
            leftExtendMotor.set(0);
        } else if (speed < 0 && getBottomLimit()) {
            rightExtendMotor.set(0);
            leftExtendMotor.set(0);
        } else {
            rightExtendMotor.set(speed);
            leftExtendMotor.set(speed);
        }
    }

    /**
     * This method returns the value of the top limit switch.
     *
     * @return top limit switch value, true if the hanger arm is at it's highest
     *         point.
     */
    public boolean getTopLimit() {
        return topLimitSwitch.get();
    }

    /**
     * This method returns the value of the bottom limit switch.
     *
     * @return bottom limit switch value, true if the hanger arm is at it's lowest
     *         point.
     */
    public boolean getBottomLimit() {
        return bottomLimitSwitch.get();
    }

    /**
     * This method returns the value of the absolute encoder.
     *
     * @return absolute encoder value, position of arm rotation in degrees.
     */
    public double getAbsEncoder() {
        return NumberUtil.ticksToDegs(absHangingRotation.getPeriod()); // equation for degree per tick converted to
        // seconds.;
    }

    /**
     * This method resets the position of the extension motors.
     */
    public void resetExtensionPosition() {
        rightExtendMotor.getEncoder().setPosition(0);
    }

    /**
     * This method gets the position of the extension motors.
     *
     * @return extension motor position relative to the last reset.
     */
    public double getExtensionPosition() {
        return (rightExtendMotor.getEncoder().getPosition() + leftExtendMotor.getEncoder().getPosition()) / 2;
    }
}
