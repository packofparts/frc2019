package org.usfirst.frc.team1294.robot.commands;

import org.usfirst.frc.team1294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Austin Jenchi (timtim17)
 */
public class SwitchToClosedLoopMode extends Command {
  public SwitchToClosedLoopMode() {
    super("Switch to OPEN LOOP");

    requires(Robot.driveBase);
  }

  @Override
  protected void initialize() {
    Robot.driveBase.setTalonsToClosedLoopSpeed();
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
