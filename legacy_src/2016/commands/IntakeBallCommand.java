package org.usfirst.frc.team1294.robot.commands;

import org.usfirst.frc.team1294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Austin Jenchi (timtim17)
 */
public class IntakeBallCommand extends Command {
  public IntakeBallCommand() {
    requires(Robot.ballHandleSubsystem);
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    Robot.ballHandleSubsystem.setIntake(0.5);//Robot.oi.getMechStickOne().getRawAxis(3));
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.ballHandleSubsystem.stopIntake();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
