/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  public static final int CTRE_TIMEOUT_INIT = 10;
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;
  public static int pcmcan = 5; //This must match the CAN id of the PCM. If it does not, pneumatics breaks
  public static int pdp = 0;
  public static int rightFront = 1;
  public static int rightRear = 2; //encoder
  public static int leftFront = 3;
  public static int leftRear = 4; //encoder
  public static int elevator = 6;
  public static int strutback = 7;
  public static int intake = 8;
  public static int solenoid1 = 1;
  public static int solenoid2 = 2;
  public static int solenoid3 = 3;
  public static int solenoid4 = 4;
  
  
  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
