/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;
//import frc.robot.OI;
import frc.robot.Robot;

public class PneumaticsToggleCommand extends Command {
  boolean m_vals;
  public PneumaticsToggleCommand(int solenoidToToggle, boolean vals) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.solenoid = solenoidToToggle;
    requires(Robot.pneumaticsController);
    m_vals = vals;
  }
  int solenoid;
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  Robot.pneumaticsController.compressorOn();
      
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(solenoid == 1) {
      Robot.gamer.fs = m_vals;
      if(Robot.gamer.fs) {
        Robot.pneumaticsController.solenoidOn(1);
      } else {
        Robot.pneumaticsController.solenoidOff(1);
      }
      
    } else if(solenoid == 2) {
      Robot.gamer.as = m_vals;
      if(Robot.gamer.as) {
        Robot.pneumaticsController.solenoidOn(2);
      } else {
        Robot.pneumaticsController.solenoidOff(2);
      }
      Robot.gamer.addACycle();
    } else if(solenoid == 3) {
      Robot.gamer.bs = m_vals;
      if(Robot.gamer.bs) {
        Robot.pneumaticsController.solenoidOn(3);
      } else {
        Robot.pneumaticsController.solenoidOff(3);
      }
    } else if(solenoid == 4) {
      Robot.gamer.ws = m_vals;
      if(Robot.gamer.ws) {
        Robot.pneumaticsController.solenoidOn(4);
      } else {
        Robot.pneumaticsController.solenoidOff(4);
      }
      Robot.gamer.addACycle();
    }
    
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

  public boolean getValue(boolean Solenoid){
    return Solenoid;
  }




}

