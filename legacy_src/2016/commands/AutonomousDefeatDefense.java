package org.usfirst.frc.team1294.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousDefeatDefense extends CommandGroup {
	
	public AutonomousDefeatDefense() {
		addSequential(new ResetGyro());
		addSequential(new DriveStraightDistance(-0.8, 5));
	}
	
}
