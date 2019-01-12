package org.usfirst.frc.team1294.robot.commands;

import org.usfirst.frc.team1294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BallHandlingCommand extends Command {

	public BallHandlingCommand() {
	    requires(Robot.ballHandleSubsystem);
	  }
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		double right = Robot.oi.getMechStickOne().getRawAxis(3);
		double left = Robot.oi.getMechStickOne().getRawAxis(2);
		double output = right - left;
		SmartDashboard.putNumber("Ball Intake Output", output);
		
		Robot.ballHandleSubsystem.setIntake(output);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
