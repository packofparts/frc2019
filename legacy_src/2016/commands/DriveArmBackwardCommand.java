package org.usfirst.frc.team1294.robot.commands;

import org.usfirst.frc.team1294.robot.Robot;
import org.usfirst.frc.team1294.robot.subsystems.ArmSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Austin Jenchi (timtim17)
 */
public class DriveArmBackwardCommand extends Command {
  public DriveArmBackwardCommand() {
    requires(Robot.armSubsystem);
  }

  @Override
  protected void initialize() {
    Robot.armSubsystem.backwards();
    setTimeout(ArmSubsystem.TIMEOUT);
  }

  @Override
  protected void execute() {
    // do nothing
  }

  @Override
  protected boolean isFinished() {
    return Robot.armSubsystem.isBackwardsLimitSwitchClosed() || isTimedOut();
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
