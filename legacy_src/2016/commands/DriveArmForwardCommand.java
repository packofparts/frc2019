package org.usfirst.frc.team1294.robot.commands;

import org.usfirst.frc.team1294.robot.Robot;
import org.usfirst.frc.team1294.robot.subsystems.ArmSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Austin Jenchi (timtim17)
 */
public class DriveArmForwardCommand extends Command {
  public DriveArmForwardCommand() {
    requires(Robot.armSubsystem);
  }

  @Override
  protected void initialize() {
    Robot.armSubsystem.forwards();
    setTimeout(ArmSubsystem.TIMEOUT);
  }

  @Override
  protected void execute() {
    // do nothing
  }

  @Override
  protected boolean isFinished() {
    return Robot.armSubsystem.isForwardsLimitSwitchClosed() || isTimedOut();
  }

  @Override
  protected void end() {
    Robot.armSubsystem.stop();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
