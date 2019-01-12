package org.usfirst.frc.team1294.robot.commands;

import org.usfirst.frc.team1294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Austin Jenchi (timtim17)
 */
public class SwitchToOpenLoopMode extends Command {
  public SwitchToOpenLoopMode() {
    super("Switch to CLOSED LOOP");

    requires(Robot.driveBase);
  }

  @Override
  protected void initialize() {
    Robot.driveBase.setTalonsToOpenLoop();
  }

  @Override
  protected void execute() {

  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {

  }

  @Override
  protected void interrupted() {

  }
}
