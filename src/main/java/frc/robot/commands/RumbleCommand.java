/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Robot;

public class RumbleCommand extends Command {
  double m_mmmmmmmmmmmmmmmmmmmmmmm;
  Timer m_time;
  String m_controller;
  Boolean areWeFinished = false;
  public RumbleCommand(double mmmmmmmmmmmmmmmmmmmmmmm, String controller) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.m_mmmmmmmmmmmmmmmmmmmmmmm = mmmmmmmmmmmmmmmmmmmmmmm;
    this.m_controller = controller;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    m_time = new Timer();
    m_time.reset();
    m_time.start();
    areWeFinished =  false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println(m_time.get());
    if (m_controller == "drive") {
      Robot.m_oi.driveJoyStick.setRumble(RumbleType.kRightRumble, 1);
      if(m_time.get() > m_mmmmmmmmmmmmmmmmmmmmmmm) {
        Robot.m_oi.driveJoyStick.setRumble(RumbleType.kRightRumble, 0);
        areWeFinished = true;
      }
    }
    else if (m_controller == "game") {
      Robot.m_oi.gameJoyStick.setRumble(RumbleType.kRightRumble, 1);
      if(m_time.get() > m_mmmmmmmmmmmmmmmmmmmmmmm) {
        Robot.m_oi.gameJoyStick.setRumble(RumbleType.kRightRumble, 0);
        areWeFinished = true;
      }
    } else {
      throw new SecurityException("You made a typo. You are fired! GET OUT!");
    }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return areWeFinished;
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
