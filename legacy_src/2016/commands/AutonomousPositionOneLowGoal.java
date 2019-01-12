package org.usfirst.frc.team1294.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonomousPositionOneLowGoal extends CommandGroup {

	public AutonomousPositionOneLowGoal() {
		addSequential(new ResetGyro());
		addParallel(new DriveArmBackwardCommand());
		addSequential(new WaitCommand(0.5));
		
		// drive straight forward until defense is defeated
		addSequential(new DriveStraightDistance(-0.8, 6.20));
		addParallel(new DriveArmForwardCommand());
		addSequential(new DriveStraightDistance(-0.8, 1));
		
		// turn so that back end is pointing roughly at target
		addSequential(new TurnToHeading(250)); // TODO: validate this angle
		addSequential(new DriveStraightDistance(0.8, 2.5));
		
		// aim at the goal, drive forward, aim at the goal, drive forward
		//
		addSequential(new WaitCommand(2));
		addSequential(new TurnTowardsVisionTarget());
		addSequential(new DriveStraightDistance(0.8, 0.8));
		
		// shoot the ball
		addSequential(new TimedEjectBall(1));
	}
	
}
