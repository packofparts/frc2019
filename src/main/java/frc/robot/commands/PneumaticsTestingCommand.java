/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class PneumaticsTestingCommand extends Command {
  public PneumaticsTestingCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.pneumaticsController);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  Robot.pneumaticsController.compOn();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  
    if(Robot.m_oi.getAGame()) {
      Robot.pneumaticsController.on();
    } else{
      Robot.pneumaticsController.off();
    }
    if(Robot.m_oi.getYClickGame()) {
      if(Robot.pneumaticsController.testing.getClosedLoopControl()) {
        Robot.pneumaticsController.compOff();
      } else {
        Robot.pneumaticsController.compOn();
      }
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
    Robot.pneumaticsController.off();
    Robot.pneumaticsController.compOff();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
