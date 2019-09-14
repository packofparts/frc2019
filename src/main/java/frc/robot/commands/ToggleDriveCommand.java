/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Robot;


public class ToggleDriveCommand extends Command {

  boolean isDone;

  public ToggleDriveCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }
  

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("Now checking for dpad input");
    isDone = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
   if(Robot.m_oi.getDpad() == -1) {
    
    } 
   else if(Robot.m_oi.getDpad() == 0) {
      Scheduler.getInstance().add(new ArcadeDriveCommand());
      isDone = true;
    }  
   else if(Robot.m_oi.getDpad() == 90) {
      Scheduler.getInstance().add(new ChezyDriveCommand());
      isDone = true;
    }  
   else if(Robot.m_oi.getDpad() == 180) {
    Scheduler.getInstance().add(new SwerveDriveCommand());
    isDone = true;
    }  
   else if(Robot.m_oi.getDpad() == 270) {
      Scheduler.getInstance().add(new CallumDriveCommand());
      isDone = true;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isDone;
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
