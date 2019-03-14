/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.GettingDownFromTheStep;

public class DriveSubsystem extends Subsystem {
  public static WPI_TalonSRX leftFront;
  public static WPI_TalonSRX rightFront;
  public static WPI_TalonSRX rightRear;
  public static WPI_TalonSRX leftRear;
  public static WPI_TalonSRX rearstrut;

  public static double rampSeconds = .5;

  public DifferentialDrive treads;
  public XboxController m_mainJoyStick;
  public PowerDistributionPanel pdp;
  public static boolean isBackward;
  public static String defaultDrive = "ChezyDriveCommand";

  public double leftRaw;
  public double rightRaw;
  private final AHRS navX;

  public boolean coastMode;
/**
   * Add your docs here.
   */
  public DriveSubsystem() {
    leftFront = new WPI_TalonSRX(RobotMap.leftFront);
    leftRear = new WPI_TalonSRX(RobotMap.leftRear);
    rightFront = new WPI_TalonSRX(RobotMap.rightFront);
    rightRear = new WPI_TalonSRX(RobotMap.rightRear);
    leftFront.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    isBackward = false;
    pdp = new PowerDistributionPanel(RobotMap.pdp);
    
    rearstrut = new WPI_TalonSRX(RobotMap.strutback);
    rightFront.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

    
    
  //  System.out.println(rightRear.getSelectedSensorPosition(0));

    navX = new AHRS(SPI.Port.kMXP);

    SpeedControllerGroup leftSide = new SpeedControllerGroup(leftFront, leftRear);
    SpeedControllerGroup rightSide = new SpeedControllerGroup(rightRear, rightFront);
    
    treads = new DifferentialDrive(leftSide, rightSide);
    
    leftFront.configOpenloopRamp(rampSeconds);
    leftRear.configOpenloopRamp(rampSeconds);
    rightFront.configOpenloopRamp(rampSeconds);
    rightRear.configOpenloopRamp(rampSeconds);
  }
  
  @Override
  protected void initDefaultCommand() {
    //setDefaultCommand(new ElevatorDriveCommandTest());
  }

  public double getHeading() {
    double heading = navX.getAngle();
    if (heading < 0) {
        return 360 - (Math.abs(heading) % 360);
    } else {
        return Math.abs(heading) % 360;
    }
  }
  public double getTilt() {
    return navX.getRoll();
  }
public void resetGyro() {
  navX.reset();
}
  @Override
  public void periodic() {
    
    //SmartDashboard.putNumber("/left/raw", 1);
   // SmartDashboard.putNumber("/right/raw", rightRaw);
   // strutDrive();

    SmartDashboard.putNumber("Drive/Encoders/Encoder R", this.getEncoderRight());
    SmartDashboard.putNumber("Drive/Encoders/Encoder L", this.getEncoderLeft());
    SmartDashboard.putNumber("Drive/Encoders/Encoder E", Robot.gamer.getElevatorEncoder());
    SmartDashboard.putNumber("Drive/Gyro/Angle", getHeading());
    float difference = Robot.driver.rightFront.getSelectedSensorVelocity() - Robot.driver.leftFront.getSelectedSensorVelocity();
    SmartDashboard.putNumber("Drive/Encoders/1", difference*-Robot.m_oi.getLeftYDrive()); 
    SmartDashboard.putNumber("Drive/Encoders/2", difference);
    SmartDashboard.putNumber("Drive/Gamemech/Mode", Robot.gamer.Mode);
    SmartDashboard.putNumber("PDP/Currents/12", pdp.getCurrent(12));
    SmartDashboard.putNumber("PDP/Currents/13", pdp.getCurrent(13));
    SmartDashboard.putNumber("PDP/Voltage", pdp.getVoltage());
    SmartDashboard.putNumber("help/acme/kraken/cowpoop/Cycles", Robot.gamer.getCycles());
    
    if (Robot.m_oi.getADrive() && Robot.m_oi.getLeftBumperDrive()){
    Scheduler.getInstance().add(new GettingDownFromTheStep());
    }
    if (Robot.m_oi.getBDrive() && Robot.m_oi.getLeftBumperDrive()) {
      //Scheduler.getInstance().add(new GettingUpToLaSteppe());
    }

   

    //BEGIN THE DEBUG (TOGGLE BETWEEN BRAKE AND COAST WITH RIGHT BUMPER)
    //coastMode = false; (MOVE UP BECAUSE WE'RE IN PERIODIC AND WE DON'T WANT TO ALWAYS BE FALSE)
    //if (Robot.m_oi.driveJoyStick.getBumperPressed(Hand.kRight)) 
    //{
      //ADD VARIALBE TO MAKE SURE IT ONLY GOES THROUGH ONCE
      //coastMode = !(coastMode);
      //if (coastMode)
     // {
       // leftFront.setNeutralMode(NeutralMode.Coast);
        //rightFront.setNeutralMode(NeutralMode.Coast);
        //leftRear.setNeutralMode(NeutralMode.Coast);
        //rightRear.setNeutralMode(NeutralMode.Coast);
        //System.out.println("Coast");
     // }
      //else 
     // {
       // leftFront.setNeutralMode(NeutralMode.Brake);
       // rightFront.setNeutralMode(NeutralMode.Brake);
        //leftRear.setNeutralMode(NeutralMode.Brake);
       // rightRear.setNeutralMode(NeutralMode.Brake);
       // System.out.println("Brake");
     // }

    //}
    //END THE DEBUG
  }
   //System.out.println(rightRear.getSelectedSensorPosition(0));

  public double getEncoderLeft() {
    return leftFront.getSelectedSensorPosition();
  }
  public double getEncoderRight() {
    return rightFront.getSelectedSensorPosition();
  } 
  
  public void arcadeDrive(double forward, double turn) {
    if(isBackward) {
      treads.arcadeDrive(forward, turn);
    } else {
      treads.arcadeDrive(-forward, turn);
    }
    //FORWARD: treads.arcadeDrive(-forward, turn);
  }

  //public void strutDrive(){
    //if (Robot.gamer.Mode == 0) {
     // rearstrut.set(-Robot.m_oi.getGameTriggerDrive());
    //}
  //}

  public void flipDrive() {
    isBackward = !isBackward;
  }

  public void stop() {
    treads.stopMotor();
  }

public void resetEncoders() {
  leftFront.setSelectedSensorPosition(0);
  rightFront.setSelectedSensorPosition(0);
  Robot.gamer.resetElevatorEncoder();
}
}
