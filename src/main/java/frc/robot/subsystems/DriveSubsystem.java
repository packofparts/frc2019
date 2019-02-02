/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
<<<<<<< HEAD
import frc.robot.commands.ToggleDriveCommand;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.commands.TurnByCommand;
import edu.wpi.first.wpilibj.command.Scheduler;
=======
//import frc.robot.commands.ToggleDriveCommand;
>>>>>>> 144e05b0290bf67a5b580f7c1dc447ab9dfc3d14

public class DriveSubsystem extends Subsystem {
  public static WPI_TalonSRX leftFront;
  public static WPI_TalonSRX rightFront;
  public static WPI_TalonSRX rightRear;
  public static WPI_TalonSRX leftRear;
  public DifferentialDrive treads;
  public XboxController m_mainJoyStick;
  public static boolean isBackward;

  public double leftRaw;
  public double rightRaw;
  private final AHRS navX;
/**
   * Add your docs here.
   */
  public DriveSubsystem() {
    leftFront = new WPI_TalonSRX(RobotMap.leftFront);
    leftRear = new WPI_TalonSRX(RobotMap.leftRear);
    rightFront = new WPI_TalonSRX(RobotMap.rightFront);
    rightRear = new WPI_TalonSRX(RobotMap.rightRear);
    leftRear.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    isBackward = false;
    
    //rightRear.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);


    
  //  System.out.println(rightRear.getSelectedSensorPosition(0));

    navX = new AHRS(SPI.Port.kMXP);

    SpeedControllerGroup leftSide = new SpeedControllerGroup(leftFront, leftRear);
    SpeedControllerGroup rightSide = new SpeedControllerGroup(rightFront, rightRear);
    
    
    treads = new DifferentialDrive(leftSide, rightSide);

  }
  public double getHeading() {
    double heading = navX.getAngle();
    if (heading < 0) {
        return 360 - (Math.abs(heading) % 360);
    } else {
        return Math.abs(heading) % 360;
    }
  }

public void resetGyro() {
  navX.reset();
}
  @Override
  public void periodic() {
<<<<<<< HEAD
    //SmartDashboard.putNumber("/left/raw", 1);
   // SmartDashboard.putNumber("/right/raw", rightRaw);

    //SmartDashboard.putNumber("Drive/Encoders/Encoder R", this.getEncoderRight());
    //SmartDashboard.putNumber("Drive/Encoders/Encoder L", this.getEncoderLeft());
   // SmartDashboard.putNumber("Drive/Encoders/left/raw", leftFront.getRaw());
    // SmartDashboard.putNumber("/right/raw", rightRaw);
    SmartDashboard.putNumber("Drive/Gyro/Angle", getHeading());

    
    if (Robot.m_oi.getRightTrigger() > 0.7) {
      if (Robot.driver.getHeading() > 0 && Robot.driver.getHeading() < 90) {
        Scheduler.getInstance().add(new TurnByCommand(90-(Robot.driver.getHeading()-10)));
      }
      if (Robot.driver.getHeading() > 90 && Robot.driver.getHeading() < 180) {
        Scheduler.getInstance().add(new TurnByCommand(180-(Robot.driver.getHeading()-10)));
      }
      if (Robot.driver.getHeading() > 180 && Robot.driver.getHeading() < 270) {
        Scheduler.getInstance().add(new TurnByCommand((270-Robot.driver.getHeading()-10)));
      }
      if (Robot.driver.getHeading() > 270 && Robot.driver.getHeading() < 360) {
        Scheduler.getInstance().add(new TurnByCommand((360-Robot.driver.getHeading()-10)));
      }
    }
=======
   SmartDashboard.putNumber("Drive/Gyro/Angle", getHeading());
   SmartDashboard.putNumber("Drive/Encoders/Right", rightRear.getSelectedSensorPosition());
>>>>>>> 144e05b0290bf67a5b580f7c1dc447ab9dfc3d14
  }
   //System.out.println(rightRear.getSelectedSensorPosition(0));

  @Override
  protected void initDefaultCommand() {
    //setDefaultCommand(new ArcadeDriveCommand());
  }

  public double getEncoderLeft() {
    return leftRear.getSelectedSensorPosition();
  }
  public double getEncoderRight() {
    return rightRear.getSelectedSensorPosition();
  } 
  
  public void arcadeDrive(double forward, double turn) {

    if(isBackward) {
      treads.arcadeDrive(forward, turn);
    } else {
      treads.arcadeDrive(-forward, turn);
    }
    //FORWARD: treads.arcadeDrive(-forward, turn);
    
  }
  public void flipDrive() {
    isBackward = !isBackward;
  }

  public void stop() {
    treads.stopMotor();
  }
}
