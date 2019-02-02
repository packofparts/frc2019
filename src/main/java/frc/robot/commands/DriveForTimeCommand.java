/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Robot;

public class DriveForTimeCommand extends Command {
  double time;
  double speed;
  public DriveForTimeCommand(double timeToDrive, double speedToDriveAt) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.time = timeToDrive;
    this.speed = speedToDriveAt;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.driver.treads.setSafetyEnabled(false);
    Robot.driver.arcadeDrive(speed, 0);
    Timer.delay(time);
    Robot.driver.stop();
    Robot.driver.treads.setSafetyEnabled(true);
    Scheduler.getInstance().add(new ChezyDriveCommand());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
