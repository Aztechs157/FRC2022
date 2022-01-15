// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.KickerConstants;

public class Kicker extends SubsystemBase {
    private CANSparkMax kickerMotor;
    private DigitalInput ballSensor;

    /** Creates a new Kicker. */
    public Kicker() {
        kickerMotor = new CANSparkMax(KickerConstants.KICKERMOTOR_ID, MotorType.kBrushless);
        ballSensor = new DigitalInput(KickerConstants.KICKERSENSOR_ID);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    /**
     * This method feeds the ball into the shooter.
     */
    public void KickerFeed() {
        kickerMotor.set(KickerConstants.FEED_SPEED);
    }

    /**
     * This method ejects the ball from the Kicker system.
     */
    public void KickerEject() {
        kickerMotor.set(KickerConstants.EJECT_SPEED);
    }

    /**
     * This method senses the ball, currently uncomplete and return type may change.
     *
     * @return
     */
    public boolean ballSensor() {
        return ballSensor.get();
    }
}
