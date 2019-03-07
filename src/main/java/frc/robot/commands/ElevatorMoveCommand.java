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

public class ElevatorMoveCommand extends Command {
  private double m_Distance = 0;
  private double m_targetDistance = 0;
  private double startingDistance = 0;
  private boolean is_Finished = false;
  private double delta = 200;
  double m_speed = 1;
  private double topElevator = -35000.0;
  private double bottomElevator = -2000;
  private double oneRotationElevator = 4258;


  public ElevatorMoveCommand(double target) { 
  
    is_Finished = false;
    m_targetDistance = target;
    requires(Robot.gamer);
  }

  @Override
  protected void initialize() {
    Robot.gamer.elevator.setSafetyEnabled(false);
    double m_currentDistance = Robot.gamer.getElevatorEncoder();

   // if (m_targetDistance > m_currentDistance) {

      m_Distance = m_targetDistance - m_currentDistance;
    /*}
    else if (m_targetDistance < m_currentDistance) {
      m_Distance = -Math.abs(m_targetDistance - m_currentDistance);
    } *///
  }

  @Override
  protected void execute() {
    is_Finished = false;
    double m_currentDistance = Robot.gamer.getElevatorEncoder();
   //4258 encoder value is one full rotation
    if (Math.abs(m_targetDistance-m_currentDistance) > (4258 / 2)) {
      //m_speed = ((m_targetDistance-m_currentDistance)/m_Distance)+0.1;
      m_speed = 1.0;
    }
    else {
      //m_speed = ((m_targetDistance-m_currentDistance)/m_Distance)+0.5;
      m_speed = 0.8;
    }

    if (m_speed > 1.0) {
      m_speed = 1.0;
    }
    if (m_speed < 0.8) {
      m_speed = 0.8;
    }
    
    if (m_targetDistance > m_currentDistance) {
      Robot.gamer.elevatorDrive(m_speed);
      //System.out.println(m_targetDistance-m_currentDistance);
      if(m_targetDistance - m_currentDistance < delta) {
        Robot.gamer.elevatorDrive(0.3);
         is_Finished = true;
      }
    }
    else if (m_targetDistance < m_currentDistance) {
      Robot.gamer.elevatorDrive(-m_speed);
      if(m_targetDistance-m_currentDistance > -delta) {
        Robot.gamer.elevatorDrive(0.3);
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
    Robot.gamer.elevatorDrive(0.3);
    //Scheduler.getInstance().add(new ChezyDriveCommand());
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}