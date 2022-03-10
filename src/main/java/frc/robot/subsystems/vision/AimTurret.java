// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.vision;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.TurretConstants;
import frc.robot.lib.util.DoubleRange;
import frc.robot.subsystems.kicker.Kicker;
import frc.robot.subsystems.shooter.ShootCargo;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.turret.Turret;
import frc.robot.subsystems.uptake.Uptake;

public class AimTurret extends CommandBase {
    private final Vision vision;
    private final Turret turret;
    private final DoubleRange visionRange = new DoubleRange(45, 90);
    private final DoubleRange aimerRange = new DoubleRange(TurretConstants.AIMER_HIGHER_BOUNDARY,
            TurretConstants.AIMER_LOWER_BOUNDARY);
    private final ShootCargo shootCargo;
    private final boolean useAimer;

    /** Creates a new AimTurret. */
    public AimTurret(
            final Vision vision,
            final Turret turret, Shooter shooter, Kicker kicker, Uptake uptake, boolean useAimer) {
        this.vision = vision;
        this.turret = turret;
        addRequirements(vision, turret);
        shootCargo = new ShootCargo(shooter, kicker, uptake, 3800);
        this.useAimer = useAimer;
        // Use addRequirements() here to declare subsystem dependencies.
    }

    public AimTurret(
            final Vision vision,
            final Turret turret, Shooter shooter, Kicker kicker, Uptake uptake) {
        this(vision, turret, shooter, kicker, uptake, true);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        vision.setLED(true);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (!vision.hasTarget()) {
            turret.turretStop();
            turret.stopAimer();
            return;
        }

        var turretThreshold = 10;
        var aimerThreshold = 50;
        var hubX = vision.getHubX();
        var hubDiagonal = vision.getDiagonal();

        var aimerPosition = turret.getActualPosition();
        turret.turretTurn(-turret.turretpid.calculate(hubX, 0) * TurretConstants.TURRET_SPEED);
        var aimerTarget = DoubleRange.scale(visionRange, hubDiagonal, aimerRange);

        if (useAimer) {
            var x = turret.aimerpid.calculate(aimerPosition, aimerTarget);
            turret.runAimer(-(x > 1 ? 1 : x < -1 ? -1 : x) * TurretConstants.AIMER_SPEED);
        }
        // System.out.println("aimer: " + (-(x > 1 ? 1 : x < -1 ? -1 : x) *
        // TurretConstants.AIMER_SPEED));

        if (hubX > -turretThreshold && hubX < turretThreshold && aimerPosition > aimerTarget - aimerThreshold
                && aimerTarget < aimerTarget + aimerThreshold) {
            shootCargo.schedule();
        } else {
            shootCargo.cancel();
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        vision.setLED(false);
        turret.turretStop();
        turret.stopAimer();
        shootCargo.cancel();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
