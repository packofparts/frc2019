/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakeOffsetCommand extends Command {
  public double m_offset;
  public boolean isOffsetting;
  
  public IntakeOffsetCommand(double offset) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.isOffsetting = true;
    m_offset = offset;
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    /*if (Robot.m_oi.intakeOffset != 0) {p4-=]\.]] ] ]

        
      Robot.m_oi.intakeOffset = 0;
    }
    else {
      Robot.m_oi.intakeOffset = m_offset;
    }  This is code for a toggle style system */
    if(Robot.m_oi.getGameTriggerDrive() > 0) {
      isOffsetting = true;
    } 
    
    else  if(Robot.m_oi.getGameTriggerDrive() < 0) {
      isOffsetting = false;
    }
    
    if(isOffsetting) {
      Robot.m_oi.intakeOffset = m_offset;
    }
    else {
      Robot.m_oi.intakeOffset = 0;
    }


  }
  


  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
