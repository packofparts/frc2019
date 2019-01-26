/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.GenericHID.Hand;
//import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.robot.OI;
import frc.robot.RobotMap;
//import frc.robot.commands.ArcadeDriveCommand;

public class DriveSubsystem extends Subsystem {
  public static WPI_TalonSRX leftFront;
  public static WPI_TalonSRX rightFront;
  public static WPI_TalonSRX rightRear;
  public static WPI_TalonSRX leftRear;
  public DifferentialDrive treads;
  public XboxController m_mainJoyStick;

  public double leftRaw;
  public double rightRaw;
/**
   * Add your docs here.
   */
  public DriveSubsystem() {
    WPI_TalonSRX leftFront = new WPI_TalonSRX(RobotMap.leftFront);
    WPI_TalonSRX leftRear = new WPI_TalonSRX(RobotMap.leftRear);
    WPI_TalonSRX rightFront = new WPI_TalonSRX(RobotMap.rightFront);
    WPI_TalonSRX rightRear = new WPI_TalonSRX(RobotMap.rightRear);
   

    SpeedControllerGroup leftSide = new SpeedControllerGroup(leftFront, leftRear);
    SpeedControllerGroup RightSide = new SpeedControllerGroup(rightFront, rightRear);
    
    treads = new DifferentialDrive(leftSide, RightSide);
/*
    Encoder leftEnc = new Encoder(1, 2, false, Encoder.EncodingType.k4X);
   // Encoder rightEnc = new Encoder(0, 3, false, Encoder.EncodingType.k4X);
    int count = leftEnc.get();
    double leftRaw = leftEnc.getRaw();
  //  double rightRaw = rightEnc.getRaw();
    double distance = leftEnc.getDistance();
    double period = leftEnc.getPeriod();
    double rate = leftEnc.getRate();
  //  boolean direction = sampleEncoder.getDirection();
   // boolean stopped = sampleEncoder.getStopped();
   leftEnc.close();
  */
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("/left/raw", 1);
   // SmartDashboard.putNumber("/right/raw", rightRaw);
  }

  @Override
  protected void initDefaultCommand() {
    //setDefaultCommand(new ArcadeDriveCommand());
    
  }


  public void arcadeDrive(double forward, double turn) {
    treads.arcadeDrive(forward, turn);
  }

  public void stop() {
    treads.stopMotor();
  }
}
