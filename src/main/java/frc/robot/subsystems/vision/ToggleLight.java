// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.vision;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.lib.vision.LimeLight;
import frc.robot.lib.vision.LimeLight.LightMode;

public class ToggleLight extends CommandBase {
    private final LimeLight limeLight;

    /** Creates a new ToggleLight. */
    public ToggleLight(final Vision vision) {
        this.limeLight = vision.limeLight;
        // Use addRequirements() here to declare subsystem dependencies.
    }

    @Override
    public boolean runsWhenDisabled() {
        return true;
    }

    // Called when the command is initially scheduled.
    @Override
    public void execute() {
        final var current = limeLight.getLightMode();

        if (current == LightMode.ForceOff) {
            limeLight.setLightMode(LightMode.ForceOn);
        } else {
            limeLight.setLightMode(LightMode.ForceOff);
        }
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
}
