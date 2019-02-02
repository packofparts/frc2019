/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
//import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.PneumaticsToggleCommand;
import frc.robot.commands.ToggleDriveCommand;
import frc.robot.commands.ToggleDriveDirection;
import frc.robot.commands.TurnByCommand;
import frc.robot.commands.testingCommandGroup;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
  public OI() {
    //X button toggles drive command
    JoystickButton driveXButton = new JoystickButton(driveJoyStick, 3);
    driveXButton.toggleWhenActive(new ToggleDriveCommand());
    driveXButton.close();
    JoystickButton driveBButton = new JoystickButton(driveJoyStick, 2);
    driveBButton.toggleWhenActive(new testingCommandGroup());
    driveBButton.close();
    


    //Y button toggles drive direction
    JoystickButton driveYButton = new JoystickButton(driveJoyStick, 4);
    driveYButton.toggleWhenActive(new ToggleDriveDirection());
    driveYButton.close();


    JoystickButton leftBumper = new JoystickButton(driveJoyStick, 5);
    leftBumper.whenPressed(new TurnByCommand(-80));
    leftBumper.close();

    JoystickButton rightBumper = new JoystickButton(driveJoyStick, 6);
    rightBumper.whenPressed(new TurnByCommand(80));
    rightBumper.close();

    //This defines ABXY for game controller to toggle solenoids
    JoystickButton gameAButton = new JoystickButton(gameJoyStick, 1);
    gameAButton.toggleWhenActive(new PneumaticsToggleCommand(1));
    gameAButton.close();
    JoystickButton gameBButton = new JoystickButton(gameJoyStick, 2);
    gameBButton.toggleWhenActive(new PneumaticsToggleCommand(2));
    gameBButton.close();
    JoystickButton gameXButton = new JoystickButton(gameJoyStick, 3);
    gameXButton.toggleWhenActive(new PneumaticsToggleCommand(3));
    gameXButton.close();
    JoystickButton gameYButton = new JoystickButton(gameJoyStick, 4);
    gameYButton.toggleWhenActive(new PneumaticsToggleCommand(4));
    gameYButton.close();


    
  }
 
 
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.
  public XboxController driveJoyStick = new XboxController(0);
  public XboxController gameJoyStick = new XboxController(1);
  
  public double getRightXDrive() {
    return (driveJoyStick.getX(Hand.kRight));
  }
  public double getRightYDrive() {
    return (driveJoyStick.getY(Hand.kRight));
  }
  public double getLeftYDrive() {
    return (driveJoyStick.getY(Hand.kLeft));
  }
  public double getLeftTrigger() {
    return (driveJoyStick.getTriggerAxis(Hand.kLeft));
  }
  public double getRightTrigger() {
    return (driveJoyStick.getTriggerAxis(Hand.kLeft));
  }
  public boolean getAGame() {
    return (gameJoyStick.getAButton());
  }
  public boolean getBGame() {
    return (gameJoyStick.getBButton());
  }
  public boolean getXClickDrive() {
    return (driveJoyStick.getXButtonReleased());
  }
  public double getTriggerDrive() {
    return (driveJoyStick.getTriggerAxis(Hand.kRight) - driveJoyStick.getTriggerAxis(Hand.kLeft));
  }
  public boolean getYClickGame() {
    return (gameJoyStick.getYButtonReleased());
  }
  public int getDpad() {
    return (driveJoyStick.getPOV());
  }

  
  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
