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

public class SwerveDriveCommand extends Command {
  
  boolean arcadeDrive;
  public SwerveDriveCommand() {
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
    Timer.delay(0.1);
    double forward = -Robot.m_oi.getRightYDrive();
    double turn = -Robot.m_oi.getRightXDrive();
    //Robot.driver.arcadeDrive(forward, turn);
    System.out.println(Robot.m_oi.getDpad());
    if(Robot.m_oi.getDpad() != -1) {
      Timer.delay(1);
      if(Robot.m_oi.getDpad() != -1) {
        Turn(Robot.m_oi.getDpad());
      }
    }
    //System.out.println("Arcade FTW");
  }

  // Make this return true when this Command no longer needs to run execute()
  protected void Turn(int angle) {
    if(angle <= 180) {
      Scheduler.getInstance().add(new TurnByCommand(angle));
    }
    else {
      Scheduler.getInstance().add(new TurnByCommand(angle-360));
    }
  }

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
