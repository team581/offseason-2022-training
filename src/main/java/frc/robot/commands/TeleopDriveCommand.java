// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class TeleopDriveCommand extends CommandBase {
  private final DriveSubsystem driveSubsystem;
  private final XboxController controller;

  /** Creates a new TeleopDriveCommand. */
  public TeleopDriveCommand(DriveSubsystem driveSubsystem, XboxController controller) {
    this.driveSubsystem = driveSubsystem;
    this.controller = controller;

    addRequirements(driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    final var xAxis = controller.getLeftY();
    final var zRotation = controller.getLeftX();
    driveSubsystem.setSpeed(xAxis, zRotation);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
