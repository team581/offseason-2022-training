// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.elevator;

import edu.wpi.first.math.trajectory.TrapezoidProfile;

public enum ElevatorPosition {
  UP(1),
  DOWN(0),
  UNKNOWN(-1);
  public final TrapezoidProfile.State state;

  ElevatorPosition(double height) {
    this.state = new TrapezoidProfile.State(height, 0);
  }
}
