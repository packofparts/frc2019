package org.usfirst.frc.team1294.robot;

import org.usfirst.frc.team1294.robot.utilities.RobotDetector;

/**
 * Constants should be defined here, such as CANTalon ids.
 * <p>
 * All values in this file should be {@code public static final}
 * and either {@code int} or {@code double}.
 */
public class RobotMap {
	public static final int intakeTalon = 1;
	public static final int spareTalon = 2;
	public static final int leftBackTalon = 3;
	public static final int leftFrontTalon = 4;
	public static final int rightFrontTalon = 5;
	public static final int rightBackTalon = 6;
	
	public static final double intakeSpeed = 1;
	public static final double catapultSpeed = 1;
	private static final String frontCamera = "cam2";
	private static final String backCamera = "cam3";
	private static final String frontCamera2 = "cam1";
	private static final String backCamera2 = "cam0";
	private static final double distanceScaler = 5300;
	private static final double distanceScaler2 = 3560;
	
	public static double getDistanceScaler() {
		if (RobotDetector.getWhichRobot() == RobotDetector.WhichRobot.ROBOT_1) return distanceScaler;
		else return distanceScaler2;
	}

	public static String getFrontCameraId() {
		if (RobotDetector.getWhichRobot() == RobotDetector.WhichRobot.ROBOT_1) return frontCamera;
		else return frontCamera2;
	}

	public static String getBackCameraId() {
		if (RobotDetector.getWhichRobot() == RobotDetector.WhichRobot.ROBOT_1) return backCamera;
		else return backCamera2;
	}
}
