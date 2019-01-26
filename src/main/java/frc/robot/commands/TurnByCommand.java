/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

public class TurnByCommand extends Command {
  private int m_turnDegree = 0;
  boolean arcadeDrive;
  private final AHRS navX;

  public TurnByCommand(int turnDegree) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    m_turnDegree = turnDegree;
    navX = new AHRS(SPI.Port.kMXP);
    requires(Robot.driver);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.driver.treads.setSafetyEnabled(false);
  }

  public double getHeading() {
    double heading = navX.getAngle();
    if (heading < 0) {
        return 360 - (Math.abs(heading) % 360);
    } else {
        return Math.abs(heading) % 360;
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (m_turnDegree > 0) {
      while (m_turnDegree < getHeading()) {
        Robot.driver.arcadeDrive(0, 0.7);
      }
    }
    else {
      while (m_turnDegree > getHeading()) {
        Robot.driver.arcadeDrive(0, -0.7);
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
    Robot.driver.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
