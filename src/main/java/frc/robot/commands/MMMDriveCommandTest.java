package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.RobotMap;

public class MMMDriveCommandTest extends Command {
  float m_time;
  boolean arcadeDrive;
  WPI_TalonSRX rearstrut = new WPI_TalonSRX(RobotMap.strutback);

  public MMMDriveCommandTest(float time) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driver);
    m_time = time;
  }
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.driver.treads.setSafetyEnabled(false);
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    rearstrut.set(1);
    Timer.delay(m_time);
    rearstrut.set(0);
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