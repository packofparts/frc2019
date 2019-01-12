package org.usfirst.frc.team1294.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SquareAutonomousCommand extends CommandGroup{
	public SquareAutonomousCommand(){		
		addSequential(new DrivePid(0, -0.5, 1));
		addSequential(new DrivePid(90, 0.5, 0));
		addSequential(new DrivePid(90, -0.5, 1));
		addSequential(new DrivePid(180, 0.5, 0));
		addSequential(new DrivePid(180, -0.5, 1));
		addSequential(new DrivePid(270, 0.5, 0));
		addSequential(new DrivePid(270, -0.5, 1));
		addSequential(new DrivePid(0, 0.5, 0));
	}
}