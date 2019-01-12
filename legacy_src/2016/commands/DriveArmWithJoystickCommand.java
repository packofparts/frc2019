package org.usfirst.frc.team1294.robot.commands;

import org.usfirst.frc.team1294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Austin Jenchi (timtim17)
 */
public class DriveArmWithJoystickCommand extends Command {
  public DriveArmWithJoystickCommand() {
    requires(Robot.armSubsystem);
  }

  @Override
  protected void initialize() {

  }

  @Override
  protected void execute() {
    Robot.armSubsystem.setTalon(Robot.oi.getMechStickOne().getRawAxis(1) * 0.25);
  }

  @Override
  protected boolean isFinished() {
    return false;
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
