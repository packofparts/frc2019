/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ArcadeDriveCommand extends Command {
  public ArcadeDriveCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driver);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.driver.treads.setSafetyEnabled(false);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double forward = -Robot.m_oi.getRightY();
    double turn = -Robot.m_oi.getRightX();
    Robot.driver.arcadeDrive(forward, turn);
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  Robot.driver.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
