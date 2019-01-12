package org.usfirst.frc.team1294.robot.commands;

import org.usfirst.frc.team1294.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ResetGyro extends Command {

	public ResetGyro() {
		// TODO Auto-generated constructor stub
		requires(Robot.driveBase);
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		Robot.driveBase.resetGyro();
	}

	@Override
	protected void execute() {
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
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
