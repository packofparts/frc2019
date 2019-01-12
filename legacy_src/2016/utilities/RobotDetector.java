package org.usfirst.frc.team1294.robot.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * A utility class that provides a method to detect which robot the code is currently running on.
 *
 * <p>It detects a file on the roboRIO's file system. Depending on the contents of this file (or the
 * lack of said file), we can determine which robot is running.</p>
 *
 * @author Austin Jenchi (timtim17)
 */
public class RobotDetector {
  private static final String WHICH_ROBOT_FILE_NAME = "robot.txt";

  private static WhichRobot whichRobot = null;

  /**
   * A constructor to prevent instantiation of this class.
   */
  private RobotDetector() {/* This is a utility class, it shouldn't be instantiated. */}

  /**
   * Returns which robot the code is currently running on. This is determined by a file on the
   * roboRIO that contains either a "1" or a "2". This can be used to set constants or run code that
   * works on one robot or the other. If the file can't be found, it defaults to robot #1.
   *
   * <p>This value can be used by comparing it to a value in the enum {@link WhichRobot}.</p>
   *
   * @return the robot that the code is currently running on.
   */
  public static WhichRobot getWhichRobot() {
    if (whichRobot == null) {
      Scanner scanner = null;

      try {
        scanner = new Scanner(new BufferedReader(new FileReader(WHICH_ROBOT_FILE_NAME)));
        String in = scanner.nextLine();
        whichRobot = in.equals("2") ? WhichRobot.ROBOT_2 : WhichRobot.ROBOT_1;
      } catch (FileNotFoundException | NumberFormatException e) {
        whichRobot = WhichRobot.ROBOT_1;
        System.out.println("AN ERROR WAS MADE");
      } finally {
        if (scanner != null) scanner.close();
      }
    }

    //System.out.println("THE ROBOT IS: ROBOT " + whichRobot);
    return whichRobot;
  }

  /**
   * Constants that represent the robot.
   */
  public enum WhichRobot {
    ROBOT_1, ROBOT_2
  }
}
