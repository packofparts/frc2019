package org.usfirst.frc.team1294.robot.commands;

import org.usfirst.frc.team1294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Austin Jenchi (timtim17)
 */
public class DriveAtFullPowerCommand extends Command {
  public DriveAtFullPowerCommand() {
    requires(Robot.ballHandleSubsystem);
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    Robot.driveBase.tankDrive(1, 1);
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.driveBase.stop();
  }

  @Override
  protected void interrupted() {
    Robot.driveBase.stop();
  }
}
