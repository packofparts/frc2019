package org.usfirst.frc.team1294.robot.commands;

import org.usfirst.frc.team1294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Austin Jenchi (timtim17)
 */
public class StopArmCommand extends Command {
  public StopArmCommand() {
    requires(Robot.armSubsystem);
  }

  @Override
  protected void initialize() {
    Robot.armSubsystem.stop();
  }

  @Override
  protected void execute() {
    // do nothing
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
    // do nothing
  }

  @Override
  protected void interrupted() {
    // do nothing
  }
}
