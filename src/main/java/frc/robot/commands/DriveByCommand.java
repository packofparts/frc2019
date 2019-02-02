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
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;

public class DriveByCommand extends Command {
  private double m_Distance = 0;
  private double m_targetDistance = 0;
  private double startingDistance = 0;
  private boolean is_Finished = false;
  private double delta = 15;


  public DriveByCommand(double distance) { 
  
    is_Finished = false;
    m_Distance = distance * 1694.15;
    requires(Robot.driver);
  }

  @Override
  protected void initialize() {
    Robot.driver.treads.setSafetyEnabled(false);
  //  Robot.driver.resetGyro();
    startingDistance = Robot.driver.getEncoderRight();
    m_targetDistance = m_Distance + startingDistance;
  }

  @Override
  protected void execute() {
    is_Finished = false;
  //  System.out.print("hi shrimp flamingos oo oo oo if you're multi colored thats cool too, you dount need to change, its boring being the same Flamingo ooooo yash is cool");
    double m_currentDistance = Robot.driver.getEncoderRight();
    double m_speed = ((m_targetDistance-m_currentDistance)/m_Distance)+0.3;
    if (m_speed > 1.0) {
      m_speed = 1.0;
    }
    if (m_speed < 0.5) {
      m_speed = 0.5;
    }
    
    if (m_Distance > 0) {
      Robot.driver.arcadeDrive(m_speed, 0);
      System.out.println(m_targetDistance-m_currentDistance);
      if(m_targetDistance-m_currentDistance < delta) {
         Robot.driver.arcadeDrive(0, 0);
         Robot.driver.stop();
         is_Finished = true;
        }
      }
    else {
      Robot.driver.arcadeDrive(-m_speed, 0);
      if(m_targetDistance-m_currentDistance > -delta) {
        Robot.driver.arcadeDrive(0, 0);
        Robot.driver.stop();
       is_Finished = true;
      }
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return is_Finished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driver.stop();
    Scheduler.getInstance().add(new ChezyDriveCommand());
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}