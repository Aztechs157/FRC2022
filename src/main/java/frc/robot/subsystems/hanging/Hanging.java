// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.hanging;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Counter.Mode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.HangingConstants;
import frc.robot.Constants.MiscConstants;
import frc.robot.lib.NumberUtil;

public class Hanging extends SubsystemBase {
    private final CANSparkMax leftExtendMotor;
    private final CANSparkMax rotateMotor;
    private final CANSparkMax rightExtendMotor;
    private final DigitalInput topLeftLimitSwitch;
    private final DigitalInput bottomLeftLimitSwitch;
    private final DigitalInput leftBarSwitch;
    private final DigitalInput topRightLimitSwitch;
    private final DigitalInput bottomRightLimitSwitch;
    private final DigitalInput rightBarSwitch;
    private final Counter rotationAbsEncoder;

    /** Creates a new Hanging. */
    public Hanging() {
        leftExtendMotor = new CANSparkMax(HangingConstants.LEFT_EXTEND_MOTOR, MotorType.kBrushless);
        rotateMotor = new CANSparkMax(HangingConstants.ROTATE_MOTOR, MotorType.kBrushless);
        rightExtendMotor = new CANSparkMax(HangingConstants.RIGHT_EXTEND_MOTOR, MotorType.kBrushless);

        rotateMotor.setSmartCurrentLimit(MiscConstants.SMART_MOTOR_LIMIT);
        leftExtendMotor.setSmartCurrentLimit(MiscConstants.SMART_MOTOR_LIMIT);
        rightExtendMotor.setSmartCurrentLimit(MiscConstants.SMART_MOTOR_LIMIT);

        rotateMotor.setIdleMode(IdleMode.kBrake);
        leftExtendMotor.setIdleMode(IdleMode.kBrake);
        rightExtendMotor.setIdleMode(IdleMode.kBrake);

        topLeftLimitSwitch = new DigitalInput(HangingConstants.TOP_LEFT_LIMIT_SWITCH);
        bottomLeftLimitSwitch = new DigitalInput(HangingConstants.BOTTOM_LEFT_LIMIT_SWITCH);
        leftBarSwitch = new DigitalInput(HangingConstants.LEFT_BAR_LIMIT_SWITCH);
        topRightLimitSwitch = new DigitalInput(HangingConstants.TOP_RIGHT_LIMIT_SWITCH);
        bottomRightLimitSwitch = new DigitalInput(HangingConstants.BOTTOM_RIGHT_LIMIT_SWITCH);
        rightBarSwitch = new DigitalInput(HangingConstants.RIGHT_BAR_LIMIT_SWITCH);

        rotationAbsEncoder = new Counter(Mode.kSemiperiod);
        rotationAbsEncoder.setSemiPeriodMode(true);
        rotationAbsEncoder.setUpSource(HangingConstants.ABS_HANGING_ROTATION);
        rotationAbsEncoder.reset();
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
    public void rotateArms(final double speed) {
        if (speed > 0 && getRotationPosition() > HangingConstants.MAX_POS) {
            rotateMotor.set(0);
        } else if (speed < 0 && getRotationPosition() < HangingConstants.MIN_POS) {
            rotateMotor.set(0);
        } else {
            rotateMotor.set(speed);
        }
    }

    /**
     * This method extends the hanger arms outwards or inwards.
     *
     * @param speed is the speed of extension.
     */
    public void extendArms(final double speed) {
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
        return topLeftLimitSwitch.get();
    }

    /**
     * This method returns the value of the bottom limit switch.
     *
     * @return bottom limit switch value, true if the hanger arm is at it's lowest
     *         point.
     */
    public boolean getBottomLimit() {
        return bottomLeftLimitSwitch.get();
    }

    /**
     * This method returns the value of the absolute encoder.
     *
     * @return absolute encoder value, position of arm rotation in degrees.
     */
    public double getRotationPosition() {
        return NumberUtil.ticksToDegs(rotationAbsEncoder.getPeriod()); // equation for degree per tick converted to
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
