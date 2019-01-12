package org.usfirst.frc.team1294.robot.commands;

import org.usfirst.frc.team1294.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * An example command.
 */
public class SinBreakInCommand extends Command {
	Timer timer;

	public SinBreakInCommand() {
		super("Sin Wave break in");
		requires(Robot.driveBase);
	}

	/**
	 * Runs after {@link #start()} is called but before {@link #execute()}.
	 * Initialize variables, setup subsystems (i.e. Talon brake mode), or do
	 * things that should run once (i.e. {@code initialize()} is called, and
	 * then {@link #isFinished()} returns true to immediately end the command.
	 */
	@Override
	protected void initialize() {
		timer = new Timer();
		timer.start();
	}

	/**
	 * Called periodically while {@link #isFinished()} returns {@code false}.
	 */
	@Override
	protected void execute() {
		// https://www.desmos.com/calculator/wx1lpnt1a7
		Robot.driveBase.drive.arcadeDrive(Math.sin(0.31415 * timer.get()), 0);
	}

	/**
	 * Returns true if the command is ready to end.
	 *
	 * @return whether or not the command is ready to end
	 */

	@Override
	protected boolean isFinished() {
		return false;
	}

	/**
	 * Called after {@link #isFinished()} returns {@link true}. Use this to do
	 * things such as stopping motors.
	 */
	@Override
	protected void end() {
		Robot.driveBase.drive.setSafetyEnabled(true);
	}

	/**
	 * Called if the command does not end normally, such as if {@link #cancel()}
	 * is called. It is generally a good idea to call {@link #end()} in this
	 * method.
	 */
	@Override
	protected void interrupted() {
		end();
	}
}
