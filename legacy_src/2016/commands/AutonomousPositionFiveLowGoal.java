package org.usfirst.frc.team1294.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonomousPositionFiveLowGoal extends CommandGroup {

	public AutonomousPositionFiveLowGoal() {
		addSequential(new ResetGyro());
		addSequential(new WaitCommand(0.5));
		
		// drive straight forward until at aiming point
		addSequential(new DriveStraightDistance(-0.8, 7.2)); // TODO: measure this distance
		
		// turn so that back end is pointing roughly at target
		addSequential(new TurnToHeading(125)); // TODO: validate this angle
		
		// aim at the goal
		addSequential(new WaitCommand(2.5));
		addSequential(new TurnTowardsVisionTarget());
		
		// drive until at goal shooting distance
		addSequential(new DriveStraightDistance(0.8, 1.3)); // TODO: measure this distance
		
		// shoot the ball
		addSequential(new TimedEjectBall(1));
	}
	
}
