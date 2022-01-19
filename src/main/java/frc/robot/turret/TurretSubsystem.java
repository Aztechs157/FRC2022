// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.turret;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TurretConstants;

public class TurretSubsystem extends SubsystemBase {
    private final AnalogInput positionInput = new AnalogInput(TurretConstants.POTENTIOMETER_ID);
    private final CANSparkMax motor = new CANSparkMax(TurretConstants.TURRET_MOTOR_ID, MotorType.kBrushless);

    /** Creates a new TurretSubsystem. */
    public TurretSubsystem() {
        Shuffleboard.getTab("Debug").addNumber("Turret Position", this::getPosition);
    }

    public double getPosition() {
        return positionInput.getVoltage();
    }

    public void setSpeed(final double speed) {
        var position = getPosition();

        if (TurretConstants.TURRET_MIN < position && position < TurretConstants.TURRET_MAX) {
            motor.set(speed);
        } else if (position <= TurretConstants.TURRET_MIN && speed > 0) {
            motor.set(speed);
        } else if (position >= TurretConstants.TURRET_MAX && speed < 0) {
            motor.set(speed);
        } else {
            motor.set(0);
        }
    }
}
