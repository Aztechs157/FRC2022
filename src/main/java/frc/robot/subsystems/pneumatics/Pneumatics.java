// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.pneumatics;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CompressorConstants;

public class Pneumatics extends SubsystemBase {

    public Compressor compressor;

    /** Creates a new Compressor. */
    public Pneumatics() {
        compressor = new Compressor(CompressorConstants.COMPRESSOR_ID, PneumaticsModuleType.REVPH);
        compressor.enableDigital();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

}
