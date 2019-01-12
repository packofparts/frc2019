package org.usfirst.frc.team1294.robot.commands;

import org.usfirst.frc.team1294.robot.Robot;
import org.usfirst.frc.team1294.robot.subsystems.CameraSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Austin Jenchi (timtim17)
 */
public class SwitchCameraCommand extends Command {
  public SwitchCameraCommand() {
    requires(Robot.cameraSubsystem);
  }

  @Override
  protected void initialize() {
    if (Robot.driveBase.getLeftSpeed() > 100 || Robot.driveBase.getRightSpeed() > 100) return;

    switch (Robot.cameraSubsystem.getCurrentCamera()) {
      case FRONT:
        Robot.cameraSubsystem.startStream(CameraSubsystem.Camera.BACK);
        break;
      case BACK:
        Robot.cameraSubsystem.startStream(CameraSubsystem.Camera.FRONT);
        break;
      default:
        throw new IllegalStateException("Not implemented - what are you even doing");
    }
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
