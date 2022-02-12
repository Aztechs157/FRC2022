// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.uptake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorDirection;
import frc.robot.Constants.UptakeConstants;

public class Uptake extends SubsystemBase {
    private CANSparkMax uptakeMotor;
    private MotorDirection direction;

    /** Creates a new Uptake. */
    public Uptake() {
        uptakeMotor = new CANSparkMax(UptakeConstants.UPTAKE_MOTOR_ID, MotorType.kBrushless);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    /**
     * This method runs the uptake rollers to feed the ball into the kicker.
     */
    public void uptakeFeed() {
        uptakeMotor.set(UptakeConstants.FEED_SPEED);
        direction = MotorDirection.FEEDING;
    }

    /**
     * This method runs the uptake rollers to eject balls back through the intake.
     */
    public void uptakeEject() {
        uptakeMotor.set(UptakeConstants.EJECT_SPEED);
        direction = MotorDirection.EJECTING;
    }

    /**
     * This method Stops the Uptake System.
     */
    public void uptakeStop() {
        uptakeMotor.set(0);
        direction = MotorDirection.STOPPED;
    }

    /**
     * This method gets the current direction of the running uptake motor.
     *
     * @return direction of motor.
     */
    public MotorDirection getDirection() {
        return direction;
    }
}
