package org.usfirst.frc.team1294.robot;

import org.usfirst.frc.team1294.robot.commands.DriveArmBackwardCommand;
import org.usfirst.frc.team1294.robot.commands.DriveArmForwardCommand;
import org.usfirst.frc.team1294.robot.commands.DriveStraightDistance;
import org.usfirst.frc.team1294.robot.commands.EjectBallCommand;
import org.usfirst.frc.team1294.robot.commands.IntakeBallCommand;
import org.usfirst.frc.team1294.robot.commands.StopArmCommand;
import org.usfirst.frc.team1294.robot.commands.SwitchCameraCommand;
import org.usfirst.frc.team1294.robot.commands.ToggleOpenLoopModeCommand;
import org.usfirst.frc.team1294.robot.commands.TurnTowardsVisionTarget;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

//import org.usfirst.frc.team1294.robot.commands.LaunchBallCommand;
//import org.usfirst.frc.team1294.robot.commands.MoveUpperArmMotor;


public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	Joystick stickLeft = new Joystick(0);
	Button leftButton1 = new JoystickButton(stickLeft, 7);
	Button leftButton6 = new JoystickButton(stickLeft, 6);
	Button leftButton10 = new JoystickButton(stickLeft, 10);
	//Joystick stickRight = new Joystick(1);
	//Button cam1 = new JoystickButton(stickLeft, 2);
	//Button cam2 = new JoystickButton(stickLeft, 4);
	Button switchCam = new JoystickButton(stickLeft, 3);
	//Button otherSwitchCam = new JoystickButton(stickRight, 3);
	//Joystick stickTheThird = new Joystick(2);
	//Button turnToTargetButton = new JoystickButton(stickTheThird, 3);
	//Button notSmartDashboard = new JoystickButton(stickTheThird, 8);
	Joystick mechStickOne = new Joystick(1);
	Button mechButtonA = new JoystickButton(mechStickOne, 1);
	Button mechButtonB = new JoystickButton(mechStickOne, 2);
	Button mechButtonX = new JoystickButton(mechStickOne, 3);
	Button mechButtonY = new JoystickButton(mechStickOne, 4);
	Button startButton = new JoystickButton(mechStickOne, 7);
	
	Button mechButtonLB = new JoystickButton(mechStickOne, 5);
	Button mechButtonRB = new JoystickButton(mechStickOne, 6);

	Button left11 = new JoystickButton(stickLeft, 11);
	

	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	public OI() {
		switchCam.whenPressed(new SwitchCameraCommand());


		mechButtonRB.whileHeld(new IntakeBallCommand());
		mechButtonLB.whileHeld(new EjectBallCommand());
		startButton.whenPressed(new ToggleOpenLoopModeCommand());
		//mechButtonA.whenPressed(new DriveStraightDistance(0.5, 1));
		mechButtonA.whenPressed(new TurnTowardsVisionTarget());
		mechButtonY.whenPressed(new DriveArmBackwardCommand());
		mechButtonX.whenPressed(new DriveArmForwardCommand());
		mechButtonB.whenPressed(new StopArmCommand());
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

	// Start the command when the button is released  and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	public Joystick getStickLeft() {
		return stickLeft;
	}

	/*public Joystick getStickRight() {
		return stickRight;
	}*/
	public Joystick getMechStickOne(){
		return mechStickOne;
	}

	public Joystick getStickRight() {
		// TODO Auto-generated method stub
		return null;
	}
}
