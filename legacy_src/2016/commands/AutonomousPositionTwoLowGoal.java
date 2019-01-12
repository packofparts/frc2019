package org.usfirst.frc.team1294.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutonomousPositionTwoLowGoal extends CommandGroup {

	public AutonomousPositionTwoLowGoal() {
		addSequential(new ResetGyro());
		addSequential(new WaitCommand(0.5));
		
		// drive straight forward until at aiming point
		addSequential(new DriveStraightDistance(-0.8, 8.1)); // TODO: measure this distance
		
		// turn so that back end is pointing roughly at target
		addSequential(new TurnToHeading(250)); // TODO: validate this angle
		addSequential(new DriveStraightDistance(0.8, 0.75));
		
		// aim at the goal, drive forward, aim at the goal, drive forward
		addSequential(new WaitCommand(2.5));
		addSequential(new TurnTowardsVisionTarget());
		addSequential(new DriveStraightDistance(0.7, 0.5));
		
		
		// shoot the ball
		addSequential(new TimedEjectBall(1));
	}
}
