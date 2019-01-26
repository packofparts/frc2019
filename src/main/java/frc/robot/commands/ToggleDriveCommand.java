/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class ToggleDriveCommand extends Command {
  public ToggleDriveCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }
  static boolean isArcadeDrive = false;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Scheduler.getInstance().add(new ChezyDriveCommand());
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (isArcadeDrive) {
      Scheduler.getInstance().add(new ChezyDriveCommand());
      //System.out.println("Now CheesyDrive!");
    }
    else {
      Scheduler.getInstance().add(new ArcadeDriveCommand());
      //System.out.println("Now ArcadeDrive!!");
    }
    isArcadeDrive = !isArcadeDrive;
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
