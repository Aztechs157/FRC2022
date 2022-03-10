// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.input.DriverInputs;
import frc.robot.input.Keys;

public class TeleopDrive extends CommandBase {
    private final Drive driveSubsystem;
    private final DriverInputs driverInputs;

    /** Creates a new TeleopDrive. */
    public TeleopDrive(final DriverInputs driverInputs, final Drive driveSubsystem) {
        this.driverInputs = driverInputs;
        this.driveSubsystem = driveSubsystem;
        addRequirements(driveSubsystem);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        final var speedY = driverInputs.axis(Keys.Axis.DriveSpeedY).get();
        final var speedX = driverInputs.axis(Keys.Axis.DriveSpeedX).get();
        final var rotation = driverInputs.axis(Keys.Axis.DriveRotation).get();

        driveSubsystem.driveCartesian(speedY, speedX, rotation);
    }
}
