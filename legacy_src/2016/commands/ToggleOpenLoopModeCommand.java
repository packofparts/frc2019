package org.usfirst.frc.team1294.robot.commands;

import org.usfirst.frc.team1294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Austin Jenchi (timtim17)
 */
public class ToggleOpenLoopModeCommand extends Command {
  public ToggleOpenLoopModeCommand() {
    requires(Robot.driveBase);
  }

  @Override
  protected void initialize() {
    if (Robot.driveBase.isClosedLoopMode())
      Robot.driveBase.setTalonsToOpenLoop();
    else Robot.driveBase.setTalonsToClosedLoopSpeed();
    System.out.println("TOGGLED");
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
