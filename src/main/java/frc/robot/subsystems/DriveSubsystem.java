// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.TeleopDriveCommand;

public class DriveSubsystem extends SubsystemBase {
  private final WPI_TalonFX frontRightMotor = new WPI_TalonFX(Constants.FRONT_RIGHT_MOTOR_PORT);
  private final WPI_TalonFX frontLeftMotor = new WPI_TalonFX(Constants.FRONT_LEFT_MOTOR_PORT);
  private final WPI_TalonFX backRightMotor = new WPI_TalonFX(Constants.BACK_RIGHT_MOTOR_PORT);
  private final WPI_TalonFX backLeftMotor = new WPI_TalonFX(Constants.BACK_LEFT_MOTOR_PORT);

  private final MotorControllerGroup rightMotorGroup =
      new MotorControllerGroup(frontRightMotor, backRightMotor);

  private final MotorControllerGroup leftMotorGroup =
      new MotorControllerGroup(frontLeftMotor, backLeftMotor);

  private final DifferentialDrive drive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);

  /** Creates a new DriveSubsystem. */
  public DriveSubsystem(XboxController controller) {
    rightMotorGroup.setInverted(true);
    setDefaultCommand(new TeleopDriveCommand(this, controller));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }

  public void setSpeed(double xSpeed, double zRotation) {
    drive.arcadeDrive(xSpeed, zRotation);
  }
}
