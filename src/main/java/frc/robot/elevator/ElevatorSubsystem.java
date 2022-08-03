// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.elevator;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase {
  private static final ElevatorFeedforward FEEDFORWARD = new ElevatorFeedforward(0, 0, 0);
  private static final TrapezoidProfile.Constraints CONSTRAINTS =
      new TrapezoidProfile.Constraints(0, 0);
  private final WPI_TalonFX motor = new WPI_TalonFX(Constants.ELEVATOR_MOTOR_PORT);
  private final ProfiledPIDController pid = new ProfiledPIDController(0, 0, 0, CONSTRAINTS);
  /** Creates a new ElevatorSubsystem. */
  public ElevatorSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    doPositionControlLoop();
  }

  public void setGoal(ElevatorPosition position) {
    pid.setGoal(position.state);
  }

  public boolean atGoal(ElevatorPosition position) {
    return position.state == pid.getGoal() && pid.atGoal();
  }

  private void doPositionControlLoop() {
    final var setpoint = pid.getSetpoint();
    final var feedforward = FEEDFORWARD.calculate(setpoint.velocity);
    // TODO: Need to setup elevator position sensor for feedback
    final var feedback = pid.calculate(0);
    final var voltage = feedforward + feedback;
    motor.setVoltage(voltage);
  }
}
