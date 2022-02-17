// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.kicker.Kicker;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.turret.Turret;
import frc.robot.subsystems.uptake.Uptake;

public class DebugPrints extends CommandBase {
    private Intake intake;
    private Kicker kicker;
    private Uptake uptake;
    private Shooter shooter;
    private Turret turret;

    /** Creates a new DebugPrints. */
    public DebugPrints(Intake intake, Kicker kicker, Uptake uptake, Shooter shooter, Turret turret) {
        // Use addRequirements() here to declare subsystem dependencies.
        this.intake = intake;
        this.kicker = kicker;
        this.uptake = uptake;
        this.shooter = shooter;
        this.turret = turret;
    }

    @Override
    public boolean runsWhenDisabled() {
        return true;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        intake.debugPrint();
        kicker.debugPrint();
        uptake.debugPrint();
        shooter.debugPrint();
        turret.debugPrint();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
}
