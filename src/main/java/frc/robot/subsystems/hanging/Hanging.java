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
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.HangingConstants;
import frc.robot.Constants.MiscConstants;
import frc.robot.lib.NumberUtil;
import frc.robot.subsystems.turret.Turret;
import frc.robot.subsystems.turret.TurretCenter;

public class Hanging extends SubsystemBase {
    private final CANSparkMax leftExtendMotor;
    private final CANSparkMax rotateMotorRight;
    private final CANSparkMax rotateMotorLeft;
    private final CANSparkMax rightExtendMotor;
    private final DigitalInput topLeftLimitSwitch;
    private final DigitalInput bottomLeftLimitSwitch;
    private final DigitalInput topRightLimitSwitch;
    private final DigitalInput bottomRightLimitSwitch;
    private final Counter rotationAbsEncoderRight;
    private final Counter rotationAbsEncoderLeft;
    private final Turret turret;
    private final Command centerTurretCommand;

    /** Creates a new Hanging. */
    public Hanging(final Turret turret) {
        this.turret = turret;
        centerTurretCommand = new TurretCenter(turret);
        leftExtendMotor = new CANSparkMax(HangingConstants.LEFT_EXTEND_MOTOR, MotorType.kBrushless);
        leftExtendMotor.setInverted(false);
        rotateMotorRight = new CANSparkMax(HangingConstants.ROTATE_MOTOR_RIGHT, MotorType.kBrushless);
        rotateMotorLeft = new CANSparkMax(HangingConstants.ROTATE_MOTOR_LEFT, MotorType.kBrushless);
        rotateMotorRight.setInverted(true);
        rightExtendMotor = new CANSparkMax(HangingConstants.RIGHT_EXTEND_MOTOR, MotorType.kBrushless);

        // rotateMotor.setSmartCurrentLimit(MiscConstants.SMART_MOTOR_LIMIT);
        leftExtendMotor.setSmartCurrentLimit(MiscConstants.SMART_MOTOR_LIMIT);
        rightExtendMotor.setSmartCurrentLimit(MiscConstants.SMART_MOTOR_LIMIT);

        rotateMotorRight.setIdleMode(IdleMode.kBrake);
        rotateMotorLeft.setIdleMode(IdleMode.kBrake);
        leftExtendMotor.setIdleMode(IdleMode.kBrake);
        rightExtendMotor.setIdleMode(IdleMode.kBrake);

        topLeftLimitSwitch = new DigitalInput(HangingConstants.TOP_LEFT_LIMIT_SWITCH);
        bottomLeftLimitSwitch = new DigitalInput(HangingConstants.BOTTOM_LEFT_LIMIT_SWITCH);
        topRightLimitSwitch = new DigitalInput(HangingConstants.TOP_RIGHT_LIMIT_SWITCH);
        bottomRightLimitSwitch = new DigitalInput(HangingConstants.BOTTOM_RIGHT_LIMIT_SWITCH);

        rotationAbsEncoderRight = new Counter(Mode.kSemiperiod);
        rotationAbsEncoderRight.setSemiPeriodMode(true);
        rotationAbsEncoderRight.setUpSource(HangingConstants.ABS_HANGING_ROTATION_RIGHT);
        rotationAbsEncoderRight.reset();

        rotationAbsEncoderLeft = new Counter(Mode.kSemiperiod);
        rotationAbsEncoderLeft.setSemiPeriodMode(true);
        rotationAbsEncoderLeft.setUpSource(HangingConstants.ABS_HANGING_ROTATION_LEFT);
        rotationAbsEncoderLeft.reset();

        final var tab = Shuffleboard.getTab("Hanging Debug");
        tab.addBoolean("Top Left Limit", this::getTopLeftLimit);
        tab.addBoolean("Top Right Limit", this::getTopRightLimit);
        tab.addBoolean("Bottom Left Limit", this::getBottomLeftLimit);
        tab.addBoolean("Bottom Right Limit", this::getBottomRightLimit);
        tab.addNumber("Right Rotation Position", this::getRightRotationPosition);
        tab.addNumber("Left Rotation Position", this::getLeftRotationPosition);
        tab.addNumber("Right Raw Rotation", this.rotationAbsEncoderRight::getPeriod);
        tab.addNumber("Left Raw Rotation", this.rotationAbsEncoderLeft::getPeriod);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public boolean isTurretSafeToMove() {
        return getRightRotationPosition() < HangingConstants.ROTATE_TURRET_SAFE_POS_RIGHT
                && getLeftRotationPosition() > HangingConstants.ROTATE_TURRET_SAFE_POS_LEFT;
    }

    /**
     * This method rotates the hanger arms either direction.
     *
     * @param speed is the rotation speed.
     */
    public void rotateArms(final double speed) {
        // if (!turret.isCentered()) {
        //     rotateMotorRight.set(0);
        //     rotateMotorLeft.set(0);
        //     if (Math.abs(speed) > .5) {
        //         centerTurretCommand.schedule();
        //     }
        // } else
        if (speed > 0 && getRightRotationPosition() > HangingConstants.ROTATE_MAX_POS_RIGHT
                && getLeftRotationPosition() < HangingConstants.ROTATE_MAX_POS_LEFT) {
            rotateMotorRight.set(0);
            rotateMotorLeft.set(0);
        } else if (speed < 0 && getRightRotationPosition() < HangingConstants.ROTATE_MIN_POS_RIGHT
                && getLeftRotationPosition() > HangingConstants.ROTATE_MIN_POS_LEFT) {
            rotateMotorRight.set(0);
            rotateMotorLeft.set(0);
        } else {
            rotateMotorRight.set(-speed);
            rotateMotorLeft.set(-speed);
        }
    }

    /**
     * This method extends the hanger arms outwards or inwards.
     *
     * @param speed is the speed of extension.
     */
    public void extendLeftArm(final double speed) {
        if (speed > 0 && getTopLeftLimit()) {
            leftExtendMotor.set(0);
        } else if (speed < 0 && getBottomLeftLimit()) {
            leftExtendMotor.set(0);
        } else {
            leftExtendMotor.set(speed);
        }
    }

    public void extendRightArm(final double speed) {
        if (speed > 0 && getTopRightLimit()) {
            rightExtendMotor.set(0);
        } else if (speed < 0 && getBottomRightLimit()) {
            rightExtendMotor.set(0);
        } else {
            rightExtendMotor.set(speed);
        }
    }

    /**
     * This method returns the value of the top limit switch.
     *
     * @return top limit switch value, true if the hanger arm is at it's highest
     *         point.
     */
    public boolean getTopLeftLimit() {
        return !topLeftLimitSwitch.get();
    }

    public boolean getTopRightLimit() {
        return !topRightLimitSwitch.get();
    }

    /**
     * This method returns the value of the bottom limit switch.
     *
     * @return bottom limit switch value, true if the hanger arm is at it's lowest
     *         point.
     */
    public boolean getBottomLeftLimit() {
        return !bottomLeftLimitSwitch.get();
    }

    public boolean getBottomRightLimit() {
        return !bottomRightLimitSwitch.get();
    }

    /**
     * This method returns the value of the absolute encoder.
     *
     * @return absolute encoder value, position of arm rotation in degrees.
     */
    public double getRightRotationPosition() {
        return NumberUtil.ticksToDegs(rotationAbsEncoderRight.getPeriod()); // equation for degree per tick converted to
        // seconds.;
    }

    public double getLeftRotationPosition() {
        return NumberUtil.ticksToDegs(rotationAbsEncoderLeft.getPeriod()); // equation for degree per tick converted to
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
