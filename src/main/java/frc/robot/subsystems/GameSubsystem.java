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
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.ToggleDriveCommand;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.commands.TurnByCommand;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
//import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.AbortCommand;
import frc.robot.commands.BallCargoShip;
import frc.robot.commands.BallFloor;
import frc.robot.commands.BallLVL1;
import frc.robot.commands.BallLVL2;
import frc.robot.commands.BallLVL3;
import frc.robot.commands.DriveByCommand;
import frc.robot.commands.HonkCommand;
import frc.robot.commands.PneumaticsToggleCommand;
import frc.robot.commands.ToggleDriveCommand;
import frc.robot.commands.ToggleDriveDirection;
import frc.robot.commands.TurnByCommand;
import frc.robot.commands.ElevatorMoveCommand;
import frc.robot.commands.HatchLVL1;
import frc.robot.commands.HatchLVL2;
import frc.robot.commands.HatchLVL3;
import frc.robot.commands.HatchLVLFloor;
import frc.robot.commands.testingCommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;


public class GameSubsystem extends Subsystem {
  public int Mode = 1;
  public int Cycles = 0;
  public double elevatoor;
  public static boolean fs = false;
  public static boolean ws = false;
  public static boolean bs = false;
  public static boolean as = false;
  public static boolean hs = false;

    public static WPI_TalonSRX elevator;
    public static WPI_TalonSRX intake;
/**
   * Add your docs here.
   */
  public GameSubsystem() {
    intake = new WPI_TalonSRX(RobotMap.intake);
    intake.setSafetyEnabled(true);
    elevator = new WPI_TalonSRX(RobotMap.elevator);
    elevator.setSafetyEnabled(true);
  }
  
  @Override
  protected void initDefaultCommand() {
    //setDefaultCommand(new ElevatorDriveCommandTest());
  }

  @Override
  public void periodic() {

  //  if (Mode == 2 || Mode == 3) {
     //   elevatorDrive(elevatoor);
        
   // }

    if (Robot.m_oi.getDpadGame() == 0) {
      Robot.m_oi.gameJoyStick.setRumble(RumbleType.kRightRumble, 1);
      Timer.delay(0.25);  
      Robot.m_oi.gameJoyStick.setRumble(RumbleType.kRightRumble, 0);
      Mode = 3;
    }
    else if (Robot.m_oi.getDpadGame() == 90) {
      Robot.m_oi.gameJoyStick.setRumble(RumbleType.kRightRumble, 1);
      Timer.delay(0.25);  
      Robot.m_oi.gameJoyStick.setRumble(RumbleType.kRightRumble, 0);
      Mode = 1;
    }
    else if (Robot.m_oi.getDpadGame() == 180) {
      Robot.m_oi.gameJoyStick.setRumble(RumbleType.kRightRumble, 1);
      Timer.delay(0.25);  
      Robot.m_oi.gameJoyStick.setRumble(RumbleType.kRightRumble, 0);
      Mode = 2;
    }
   else if (Robot.m_oi.getDpadGame() == 270) {
      Robot.m_oi.gameJoyStick.setRumble(RumbleType.kRightRumble, 1);
      Timer.delay(0.25);  
      Robot.m_oi.gameJoyStick.setRumble(RumbleType.kRightRumble, 0);
      Mode = 4;
    }

    if (Mode == 1) {
      //climb
      if (Robot.m_oi.gameJoyStick.getAButtonPressed()) {
        System.out.println("A mode 1");
        Scheduler.getInstance().add(new PneumaticsToggleCommand(1, !fs));
      }
      if (Robot.m_oi.gameJoyStick.getXButtonPressed()) {
        System.out.println("X mode 1");
        Scheduler.getInstance().add(new PneumaticsToggleCommand(2, !as));
      }
      if (Robot.m_oi.gameJoyStick.getBButtonPressed()) {
        System.out.println("B mode 1");
        Scheduler.getInstance().add(new PneumaticsToggleCommand(3, !bs));
      }
      if (Robot.m_oi.gameJoyStick.getYButtonPressed()) {
        System.out.println("Y mode 1");
        Scheduler.getInstance().add(new PneumaticsToggleCommand(1, !fs));
        Scheduler.getInstance().add(new PneumaticsToggleCommand(3, !bs));
      }
      if (Robot.m_oi.gameJoyStick.getBumper(Hand.kRight)) {
        System.out.println("Bumper mode 1");
        Scheduler.getInstance().add(new PneumaticsToggleCommand(5, !hs));
      }
      while (Robot.m_oi.getGameTriggerDrive() != 0)
      {
      //  WPI_TalonSRX rearstrut = new WPI_TalonSRX(RobotMap.strutback);
      //  rearstrut.set(-Robot.m_oi.getGameTriggerDrive());
      }
    }
    else if (Mode == 3) {
      //ball
      if (Robot.m_oi.gameJoyStick.getAButtonPressed()) {
        System.out.println("A mode 3");
        Scheduler.getInstance().add(new BallFloor());
      }
      if (Robot.m_oi.gameJoyStick.getBButtonPressed()) {
        System.out.println("B mode 3");
        Scheduler.getInstance().add(new BallLVL1());
      }
      if (Robot.m_oi.gameJoyStick.getYButtonPressed()) {
        System.out.println("Y mode 3");
        Scheduler.getInstance().add(new BallLVL2());
      }
      if (Robot.m_oi.gameJoyStick.getXButtonPressed()) {
        System.out.println("X mode 3");
        Scheduler.getInstance().add(new BallLVL3());
      }
      if (Robot.m_oi.gameJoyStick.getBumperPressed(Hand.kRight)) {
        System.out.println("Right Bumper mode 3");
        Scheduler.getInstance().add(new BallCargoShip());
      }
      if(Robot.m_oi.gameJoyStick.getBumperPressed(Hand.kLeft)) {
        System.out.println("Left Bumper mode 3");
        Scheduler.getInstance().add(new PneumaticsToggleCommand(2, !as));
      }
      if(Robot.m_oi.gameJoyStick.getStickButtonPressed(Hand.kLeft)) {
        System.out.println("Left Stick Pressed");
        Scheduler.getInstance().add(new PneumaticsToggleCommand(4, !ws));
      }
      if (Robot.m_oi.gameJoyStick.getY(Hand.kRight) != 0)
      {
        elevator.set(Robot.m_oi.gameJoyStick.getY(Hand.kRight));
      }
      
        intake.set(Robot.m_oi.getGameTriggerDrive());
      
    }
    else if (Mode == 2) {
      //hatch
      if (Robot.m_oi.gameJoyStick.getAButtonPressed()) {
        System.out.println("A mode 2");
        Scheduler.getInstance().add(new HatchLVLFloor());
      }
      if (Robot.m_oi.gameJoyStick.getBButtonPressed()) {
        System.out.println("B mode 2");
        Scheduler.getInstance().add(new HatchLVL1());
      }
      if (Robot.m_oi.gameJoyStick.getXButtonPressed()) {
        System.out.println("X mode 2");
        Scheduler.getInstance().add(new HatchLVL2());
      }
      if (Robot.m_oi.gameJoyStick.getYButtonPressed()) {
        System.out.println("Y mode 2");
        Scheduler.getInstance().add(new HatchLVL3());
      }
      if(Robot.m_oi.gameJoyStick.getBumperPressed(Hand.kLeft)) {
        System.out.println("Left Bumper mode 3");
        Scheduler.getInstance().add(new PneumaticsToggleCommand(2, !as));
      }
      if(Robot.m_oi.gameJoyStick.getStickButtonPressed(Hand.kLeft)) {
        System.out.println("Left Stick Pressed");
        Scheduler.getInstance().add(new PneumaticsToggleCommand(4, !ws));
      }
      if (Robot.m_oi.gameJoyStick.getY(Hand.kRight) != 0)
      {
        elevator.set(Robot.m_oi.gameJoyStick.getY(Hand.kRight));
      }
      if (Robot.m_oi.getGameTriggerDrive() != 0)
      {
        intake.set(Robot.m_oi.getGameTriggerDrive());
      }
    }
    else if (Mode == 4) 
    {
    //debug
    if (Robot.m_oi.gameJoyStick.getAButtonPressed()) {
      System.out.println("A mode 4");
      Scheduler.getInstance().add(new PneumaticsToggleCommand(1, !fs));
    }
    if (Robot.m_oi.gameJoyStick.getXButtonPressed()) {
      System.out.println("X mode 4");
    }
    if (Robot.m_oi.gameJoyStick.getBButtonPressed()) {
      System.out.println("B mode 4");
      Scheduler.getInstance().add(new PneumaticsToggleCommand(3, !bs));
    }
    if (Robot.m_oi.gameJoyStick.getYButtonPressed()) {
      System.out.println("Y mode 4");
    }
    if(Robot.m_oi.gameJoyStick.getBumperPressed(Hand.kLeft)) {
      System.out.println("Left Bumper mode 3");
      Scheduler.getInstance().add(new PneumaticsToggleCommand(2, !as));
    }
    if(Robot.m_oi.gameJoyStick.getStickButtonPressed(Hand.kLeft)) {
      System.out.println("Left Stick Pressed");
      Scheduler.getInstance().add(new PneumaticsToggleCommand(4, !ws));
    }
    if (Robot.m_oi.gameJoyStick.getY(Hand.kRight) != 0)
    {
      elevator.set(Robot.m_oi.gameJoyStick.getY(Hand.kRight));
    }
    if (Robot.m_oi.getGameTriggerDrive() != 0)
    {
      intake.set(Robot.m_oi.getGameTriggerDrive());
    }
   }
  
  
  }
   //System.out.println(rightRear.getSelectedSensorPosition(0));
  
  public double getElevatorEncoder(){
    return elevator.getSelectedSensorPosition();
  }
  
  public void elevatorDrive(double go){
    elevator.set(go);
  }

  public void stopE(){
    elevator.stopMotor();
  }
  public void resetElevatorEncoder() {
    elevator.setSelectedSensorPosition(0);
  }

  public void addACycle() {
    Cycles++;
  }

  public int getCycles() {
    return Cycles;
  }
  
}
