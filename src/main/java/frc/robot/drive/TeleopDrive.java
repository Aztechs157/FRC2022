// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.input.AxisKey;
import frc.robot.input.DriverInputs;

public class TeleopDrive extends CommandBase {
    private final DriveSubsystem driveSubsystem;
    private final DriverInputs driverInputs;

    /** Creates a new TeleopDrive. */
    public TeleopDrive(final DriverInputs driverInputs, final DriveSubsystem driveSubsystem) {
        this.driverInputs = driverInputs;
        this.driveSubsystem = driveSubsystem;
        addRequirements(driveSubsystem);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        final var speedY = driverInputs.axis(AxisKey.DriveSpeedY).get();
        final var speedX = driverInputs.axis(AxisKey.DriveSpeedX).get();
        final var rotation = driverInputs.axis(AxisKey.DriveRotation).get();

        driveSubsystem.driveCartesian(speedY, speedX, rotation);
    }
}
