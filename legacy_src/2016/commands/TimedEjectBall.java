package org.usfirst.frc.team1294.robot.commands;

import org.usfirst.frc.team1294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Austin Jenchi (timtim17)
 */
public class TimedEjectBall extends Command {
  private double timeout;

  public TimedEjectBall(double time) {
    timeout = time;
  }

  @Override
  protected void initialize() {
    setTimeout(timeout);
    
  }

  @Override
  protected void execute() {
	  Robot.ballHandleSubsystem.setIntake(-1);
  }

  @Override
  protected boolean isFinished() {
    return isTimedOut();
  }

  @Override
  protected void end() {
    Robot.ballHandleSubsystem.stopIntake();
  }

  @Override
  protected void interrupted() {
    Robot.ballHandleSubsystem.stopIntake();
  }
}
