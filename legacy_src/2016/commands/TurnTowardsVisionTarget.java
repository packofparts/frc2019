package org.usfirst.frc.team1294.robot.commands;

import org.usfirst.frc.team1294.robot.Robot;

public class TurnTowardsVisionTarget extends TurnToBearing {
	private static final double FOV = 60;
	private static final double WIDTH = 640;
	private static final double DEGREES_PER_PIXEL = FOV / WIDTH;
	private static final double MAX_AGE_OF_VISION_DATA = 1000;

	public TurnTowardsVisionTarget() {
		super(0);
		this.requires(Robot.visionSubsystem);
	}

	@Override
	protected void initialize() {
		
		if (Robot.visionSubsystem.getTimeSinceLastUpdate() <= MAX_AGE_OF_VISION_DATA && Robot.visionSubsystem.isTargetAcquired()) {
			bearing = (Robot.visionSubsystem.getTargetX() - WIDTH / 2) * DEGREES_PER_PIXEL;
			System.out.println("targetX " + Robot.visionSubsystem.getTargetX() + " bearing " + bearing);
		} else {
			System.out.println("Did not see the target");
		}
		super.initialize();
	}
	
	
}
