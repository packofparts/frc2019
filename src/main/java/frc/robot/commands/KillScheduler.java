/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Robot;

public class KillScheduler extends Command {
  public KillScheduler() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Scheduler.getInstance().removeAll();
    System.out.println("Restarting Essential Commands");
    Robot.m_oi.gameJoyStick.setRumble(RumbleType.kRightRumble, 1);
    Robot.m_oi.driveJoyStick.setRumble(RumbleType.kRightRumble, 1);
    Scheduler.getInstance().add(new ChezyDriveCommand());
    Timer.delay(1);
    Robot.m_oi.gameJoyStick.setRumble(RumbleType.kRightRumble, 0);
    Robot.m_oi.driveJoyStick.setRumble(RumbleType.kRightRumble, 0);
    System.out.println("Rebooting Complete!");
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
}
