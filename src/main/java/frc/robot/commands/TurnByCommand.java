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

public class TurnByCommand extends Command {
  private double m_turnDegree = 0;
  private double m_targetDegree = 0;
  private boolean m_heading = true;
  private double startingDegree = 0;
  private boolean is_Finished = false;
  private double echo = 2;
  boolean arcadeDrive;

  public TurnByCommand(double turnDegree) {//, boolean addheading) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    is_Finished = false;
    if(turnDegree >= 0) {    
      m_turnDegree = turnDegree-echo;
    }
    else {
      m_turnDegree = turnDegree+echo;
    }
    requires(Robot.driver);
  }

  @Override
  protected void initialize() {
    Robot.driver.treads.setSafetyEnabled(false);
  //  Robot.driver.resetGyro();
    startingDegree = Robot.driver.getHeading();
    m_targetDegree = m_turnDegree + startingDegree;
    if (m_targetDegree > 360) {
      m_targetDegree -= 360;
    }
    else if (m_targetDegree < 0) {
      m_targetDegree += 360;
    }
  }

  @Override
  protected void execute() {
    is_Finished = false;
    //System.out.print("hi shrimp flamingos oo oo oo if you're");4
    double m_currentDegree = Robot.driver.getHeading();
    double m_speed = ((m_targetDegree-m_currentDegree+(m_turnDegree/12))/(m_turnDegree));
    System.out.print(m_speed + " ");

    if((m_targetDegree-m_currentDegree < 0 && m_turnDegree > 0)) {
      if(m_speed > 0)
        m_speed = -m_speed;
      System.out.println("Reversed");
    }
    else if ((m_targetDegree-m_currentDegree > 0 && m_turnDegree < 0)) {
      if(m_speed < 0)
        m_speed = -m_speed;
      System.out.println("Reversed");
    }
    if (m_speed > 1.0 && m_speed > 0) {
      m_speed = 1.0;
    }
    else if (m_speed < 0.5 && m_speed > 0) {
      m_speed = 0.5;
    }
    else if (m_speed < -1.0 && m_speed < 0) {
      m_speed = -1.0;
    }
    else if (m_speed > -0.5 && m_speed < 0) {
      m_speed = -0.5;
    }
    System.out.println(m_speed);

    if (m_turnDegree > 0) {
      Robot.driver.arcadeDrive(0, -m_speed);
      if(m_targetDegree-m_currentDegree > -echo && m_targetDegree-m_currentDegree < echo ) {
         Robot.driver.arcadeDrive(0, 0);
         Robot.driver.stop();
         is_Finished = true;
        }
      }
    else {
      Robot.driver.arcadeDrive(0, m_speed);
      if(m_targetDegree-m_currentDegree > -echo && m_targetDegree-m_currentDegree < echo ) {
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
    Scheduler.getInstance().add(new SwerveDriveCommand());
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}