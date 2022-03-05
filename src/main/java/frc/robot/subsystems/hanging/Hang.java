// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.hanging;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class Hang extends SequentialCommandGroup {
    /** Creates a new Hang. */
    public Hang(final Hanging hanging) {
        addCommands(new ExtendArms(hanging, 1234));
    }
}
