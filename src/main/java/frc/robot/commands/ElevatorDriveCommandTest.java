package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ElevatorDriveCommandTest extends Command {
  
  boolean arcadeDrive;
  public ElevatorDriveCommandTest() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driver);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.driver.treads.setSafetyEnabled(false);
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double direction = Robot.m_oi.getRightYGame();
    
    Robot.gamer.elevatorDrive(direction);
    //System.out.println("Arcade FTW");
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  Robot.gamer.stopE();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}