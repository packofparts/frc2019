package org.usfirst.frc.team1294.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Super simple autonomous command that simply drives forward far enough to reach the defense
 */
public class AutonomousReachDefense extends CommandGroup {

  protected static final double DISTANCE_TO_REACH_DEFENSE = 1.88; // TODO determine the correct distance
  protected static final double SPEED = -0.5;

  public AutonomousReachDefense() {
    addSequential(new DriveStraightDistance(SPEED, DISTANCE_TO_REACH_DEFENSE));
  }

}
