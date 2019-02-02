/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ToggleDriveCommand;
import frc.robot.commands.ToggleDriveDirection;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
  public OI() {
    //X button toggles drive command
    JoystickButton xButton = new JoystickButton(driveJoyStick, 3);
    xButton.toggleWhenActive(new ToggleDriveCommand());
    xButton.close();
    


    //Y button toggles drive direction
    JoystickButton yButton = new JoystickButton(driveJoyStick, 4);
    yButton.toggleWhenActive(new ToggleDriveDirection());
    yButton.close();


    //JoystickButton leftBumper = new JoystickButton(driveJoyStick, 5);
    //leftBumper.whenPressed(new TurnByCommand(90));
    //leftBumper.close();
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
