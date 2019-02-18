/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.*;

public class ChezyDriveCommand extends Command {
  float yomamadelta = 50;
  double yomama = 0;
  double yolastmama = 0;
  boolean yomamaFirstTime = false;
  public ChezyDriveCommand() {
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
    double forward = -Robot.m_oi.getLeftYDrive();
    double turn = -Robot.m_oi.getRightXDrive() + yomama;
    Robot.driver.arcadeDrive(forward, turn);

/*    if (Robot.m_oi.getRightXDrive() == 0 && Robot.m_oi.getLeftYDrive() != 0) {
      float difference = Robot.driver.rightFront.getSelectedSensorVelocity() - Robot.driver.leftFront.getSelectedSensorVelocity();
      SmartDashboard.putNumber("Drive/Encoders/1", difference*forward); 
      SmartDashboard.putNumber("Drive/Encoders/2", difference);
      if(difference 
      > yomamadelta)
      {
        yomama += 0.05;
      }
      else if (difference < -yomamadelta)
      {
        yomama -= 0.05;
      }
    }
  /*  if ((Robot.m_oi.getRightXDrive() < 0.1 && Robot.m_oi.getRightXDrive() > -0.1) && (forward > 0.1 || forward < -0.1)) {
      if (yomamaFirstTime) {
        Robot.driver.resetGyro();
      }

      yolastmama = Robot.driver.getHeading();
      double yomamasangle = Robot.driver.getHeading();
      System.out.println(yomamasangle);

      if (Robot.driver.getHeading() > 180) {
        yomamasangle = -(360-Robot.driver.getHeading());
      }

      if (yomamasangle < yolastmama){
        yomama -= 0.1;
      }
      if (yomamasangle > yolastmama) {
        yomama += 0.1;
      } 
      yomamaFirstTime = false;
    }
    else {
      yomamaFirstTime = true;
    } */
    //System.out.println("ChezyDrive is successfully Chezying");
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
