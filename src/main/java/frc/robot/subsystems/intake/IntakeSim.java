// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.intake;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.Constants.IntakeConstants;

public class IntakeSim extends Intake {
    final ShuffleboardTab tab;
    final NetworkTableEntry intakeMotorSim;
    final NetworkTableEntry intakeSolenoidSim;
    SendableChooser<ColorResult> colorSensorSim;

    /** Creates a new IntakeSim. */
    public IntakeSim() {
        tab = Shuffleboard.getTab("Debug");
        intakeMotorSim = tab.add("Intake Speed", 0).getEntry();
        intakeSolenoidSim = tab.add("pneumatic on_off", false).getEntry();
        colorSensorSim = new SendableChooser<>();
        colorSensorSim.addOption("red", ColorResult.RED);
        colorSensorSim.addOption("blue", ColorResult.BLUE);
        colorSensorSim.setDefaultOption("null", ColorResult.NONE);
        tab.add("color sensor color", colorSensorSim);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    /**
     * Simulator code to raise the arm of the intake.
     */
    @Override
    public void raiseArm() {
        intakeSolenoidSim.setBoolean(false);
    }

    /**
     * Simulator code to lower the arm of the intake.
     */
    @Override
    public void lowerArm() {
        intakeSolenoidSim.setBoolean(true);
    }

    /**
     * Simulator code to run the rollers in a feeding direction.
     */
    @Override
    public void rollerFeed() {
        intakeMotorSim.setDouble(IntakeConstants.FEED_SPEED);
    }

    /**
     * Simulator code to run the rollers in an ejecting direction.
     */
    @Override
    public void rollerEject() {
        intakeMotorSim.setDouble(IntakeConstants.EJECT_SPEED);
    }

    /**
     * Simulator code to stop the rollers from spinning.
     */
    @Override
    public void rollerStop() {
        intakeMotorSim.setDouble(0);
    }

    /**
     * Simulator code to get the current color of the cargo that is stored
     * within the intake/uptake
     *
     * @return color of the cargo.
     */
    @Override
    public ColorResult currentColor() {
        return colorSensorSim.getSelected();
    }

}
