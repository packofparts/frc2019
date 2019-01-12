package org.usfirst.frc.team1294.robot.subsystems;

import org.usfirst.frc.team1294.robot.RobotMap;
import org.usfirst.frc.team1294.robot.commands.ArcadeDriveCommand;
import org.usfirst.frc.team1294.robot.utilities.RobotDetector;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 * An example subsystem.
 */
public class DriveBase extends Subsystem {
	public CANTalon leftFrontTalon;
	public CANTalon rightFrontTalon;
	public CANTalon leftBackTalon;
	public CANTalon rightBackTalon;
	public RobotDrive drive;
	private Gyro gyro;
	private AnalogInput ultrasonic;
	private AnalogInput ultrasonic2;
	private AnalogInput ultrasonic3;
	private boolean isClosedLoopMode;

	public DriveBase() {
		leftFrontTalon = new CANTalon(RobotMap.leftFrontTalon);
		leftFrontTalon.setInverted(true); // Inverts the direction of the motor direction. Only works in vbus mode
		leftFrontTalon.reverseOutput(false); // flips the sign of the throttle values going into the motor on the talon in closed loop modes
		leftFrontTalon.reverseSensor(false); // flips the sign of the sensor values going into the talon in closed loop modes

		leftBackTalon = new CANTalon(RobotMap.leftBackTalon);
		leftBackTalon.setInverted(false);
		leftBackTalon.reverseOutput(false);
		leftBackTalon.changeControlMode(CANTalon.TalonControlMode.Follower);
		leftBackTalon.set(leftFrontTalon.getDeviceID());
		
		rightFrontTalon = new CANTalon(RobotMap.rightFrontTalon);
		rightFrontTalon.setInverted(true);
		rightFrontTalon.reverseOutput(false);
		rightFrontTalon.reverseSensor(false);
		
		rightBackTalon = new CANTalon(RobotMap.rightBackTalon);
		rightBackTalon.setInverted(false);
		rightBackTalon.reverseOutput(false);
		rightBackTalon.changeControlMode(CANTalon.TalonControlMode.Follower);
		rightBackTalon.set(rightFrontTalon.getDeviceID());

		drive = new RobotDrive(leftFrontTalon, rightFrontTalon);
		

		setTalonsToClosedLoopSpeed();
		AnalogInput ultrasonicSensor = new AnalogInput(1);
    	ultrasonic = ultrasonicSensor;
    	AnalogInput ultrasonicSensor2 = new AnalogInput(2);
    	ultrasonic2 = ultrasonicSensor2;
    	AnalogInput ultrasonicSensor3 = new AnalogInput(3);
    	ultrasonic3 = ultrasonicSensor3;

		//if (RobotDetector.getWhichRobot() == RobotDetector.WhichRobot.ROBOT_1)
			gyro = new ADXRS450_Gyro();
		//else gyro = new AnalogGyro(0);

		/*
		 * LiveWindow.addSensor(this.getName(), "AnalogGyro", (AnalogGyro)
		 * gyro); LiveWindow.addActuator("Drive", "Left Front", leftFrontTalon);
		 * LiveWindow.addActuator("Drive", "Left Rear", leftBackTalon);
		 * LiveWindow.addActuator("Drive", "Right Front", rightFrontTalon);
		 * LiveWindow.addActuator("Drive", "Right Rear", rightBackTalon);
		 */
	}

	@Override
	protected void initDefaultCommand() {
		//setDefaultCommand(new TankDriveWithJoystick());
		setDefaultCommand(new ArcadeDriveCommand());
	}

	public void tankDrive(Joystick left, Joystick right) {
		drive.tankDrive(left, right);
	}

	public void arcadeDrive(double speed, double rotation) {
		drive.arcadeDrive(speed, rotation);
	}

	public void setTalonsToClosedLoopSpeed() {
		leftFrontTalon.set(0);
		leftFrontTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		leftFrontTalon.changeControlMode(CANTalon.TalonControlMode.Speed);
		leftFrontTalon.setP(1);
		leftFrontTalon.setI(0);
		leftFrontTalon.setD(0);
		
		rightFrontTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightFrontTalon.changeControlMode(CANTalon.TalonControlMode.Speed);
		rightFrontTalon.set(0);
		rightFrontTalon.setP(1);
		rightFrontTalon.setI(0);
		rightFrontTalon.setD(0);
		
		drive.setMaxOutput(2000);

		isClosedLoopMode = true;
	}

	public void setTalonsToOpenLoop() {
		rightFrontTalon.set(0);
		leftFrontTalon.set(0);
		rightFrontTalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		leftFrontTalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		
		drive.setMaxOutput(1);

		isClosedLoopMode = false;
	}

	public void stop() {
		drive.drive(0, 0);
	}

	public void tankDrive(double leftSpeed, double rightSpeed) {
		drive.tankDrive(leftSpeed, rightSpeed);
	}

	public double getRawAngle() {
		return gyro.getAngle();
	}

	public double getNormalizedAngle() {
		double angle = gyro.getAngle() % 360;
		if (angle < 0)
			angle += 360;
		return angle;
	}

	public void resetGyro() {
		gyro.reset();
	}

	public double getLeftPosition() {
		return leftFrontTalon.getEncPosition() / RobotMap.getDistanceScaler();
	}

	public double getRightPosition() {
		return rightFrontTalon.getEncPosition() / RobotMap.getDistanceScaler();
	}

	public double getLeftSpeed() {
		return leftFrontTalon.getEncVelocity();
	}

	public double getRightSpeed() {
		return rightFrontTalon.getEncVelocity();
	}
	public double getUltrasonicDistanceFront(){
		double voltage = ultrasonic.getVoltage();
		return (voltage * (512.0/5.0));
	}
	public double getUltrasonicDistanceLeft(){
		double voltage = ultrasonic2.getVoltage();
		return (voltage *(512.0/5.0));
	}
	public double getUltrasonicDistanceRight(){
		double voltage = ultrasonic3.getVoltage();
		return (voltage *(512.0/5.0));
	}

	public boolean isClosedLoopMode() {
		return isClosedLoopMode;
	}
}
