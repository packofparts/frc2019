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
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.ElevatorDriveCommandTest;
import frc.robot.commands.ToggleDriveCommand;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.commands.TurnByCommand;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
//import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.AbortCommand;
import frc.robot.commands.DriveByCommand;
import frc.robot.commands.HonkCommand;
import frc.robot.commands.PneumaticsToggleCommand;
import frc.robot.commands.ToggleDriveCommand;
import frc.robot.commands.ToggleDriveDirection;
import frc.robot.commands.TurnByCommand;
import frc.robot.commands.ElevatorMoveCommand;
import frc.robot.commands.testingCommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;


public class GameSubsystem extends Subsystem {
  public int Mode = 0;
    public static WPI_TalonSRX elevator;
/**
   * Add your docs here.
   */
  public GameSubsystem() {
    elevator = new WPI_TalonSRX(RobotMap.elevator);
  }
  
  @Override
  protected void initDefaultCommand() {
    //setDefaultCommand(new ElevatorDriveCommandTest());
  }

  @Override
  public void periodic() {
    if (Mode == 0) {
        elevatorDrive(Robot.m_oi.getRightYGame());
    }

    if (Robot.m_oi.getDpadGame() == 0) {
      Mode = 3;
    }
    else if (Robot.m_oi.getDpadGame() == 90) {
      Mode = 1;
    }
    else if (Robot.m_oi.getDpadGame() == 180) {
      Mode = 2;
    }
   // else if (Robot.m_oi.getDpadGame() == 270) {
     // Mode = 0;
    //}

    //This defines ABXY for game controller to toggle solenoids
    if (Mode == 1) {
      //climb
      if (Robot.m_oi.gameJoyStick.getAButtonPressed()) {
        System.out.println("A mode 1");
        Scheduler.getInstance().add(new PneumaticsToggleCommand(1));
      }
      if (Robot.m_oi.gameJoyStick.getXButtonPressed()) {
        System.out.println("X mode 1");
        Scheduler.getInstance().add(new PneumaticsToggleCommand(2));
      }
      if (Robot.m_oi.gameJoyStick.getBButtonPressed()) {
        System.out.println("B mode 1");
        Scheduler.getInstance().add(new PneumaticsToggleCommand(3));
      }
      if (Robot.m_oi.gameJoyStick.getYButtonPressed()) {
        System.out.println("Y mode 1");
        Scheduler.getInstance().add(new PneumaticsToggleCommand(4));
      }
      while (Robot.m_oi.getGameTriggerDrive() != 0)
      {
        WPI_TalonSRX rearstrut = new WPI_TalonSRX(RobotMap.strutback);
        rearstrut.set(-Robot.m_oi.getGameTriggerDrive());
      }
     // System.out.println("limb");
    }
    else if (Mode == 3) {
      //ball
      if (Robot.m_oi.gameJoyStick.getAButtonPressed()) {
        System.out.println("A mode 0");
        Scheduler.getInstance().add(new ElevatorMoveCommand(-2000.0));
      }
      if (Robot.m_oi.gameJoyStick.getBButtonPressed()) {
        System.out.println("B mode 0");
        Scheduler.getInstance().add(new ElevatorMoveCommand(-20000.0));
      }
      if (Robot.m_oi.gameJoyStick.getYButtonPressed()) {
        System.out.println("Y mode 0");
        Scheduler.getInstance().add(new ElevatorMoveCommand(-32000.0));
      }
      if (Robot.m_oi.gameJoyStick.getXButtonPressed()) {
        System.out.println("X mode 0");
        Scheduler.getInstance().add(new ElevatorMoveCommand(-35000.0));
       //hi 
      }
      if (Robot.m_oi.gameJoyStick.getBumperPressed(Hand.kRight)) {
        System.out.println("Right Bumper mode 0");
        Scheduler.getInstance().add(new ElevatorMoveCommand(-30000.0));
       //hi 
      }
     // while (Robot.m_oi.gameJoyStick.getTriggerAxis(Hand.kLeft) != 0) {
       // double intakeRate = Robot.m_oi.getTriggerDrive(); 
       // Scheduler.getInstance().add(new ElevatorMoveCommand(Robot.m_oi.gameJoyStick.getTriggerAxis(Hand.kLeft) * 1000));
        //System.out.println(Robot.m_oi.gameJoyStick.getTriggerAxis(Hand.kLeft));
        //intake.set(Robot.m_oi.gameJoyStick.getTriggerAxis(Hand.kLeft));
      //}
      //System.out.println("G-man");
      while (Robot.m_oi.getGameTriggerDrive() != 0)
      {
        WPI_TalonSRX intake = new WPI_TalonSRX(RobotMap.intake);
        elevator.set(-Robot.m_oi.getGameTriggerDrive());
      }

    }
    else if (Mode == 2) {
      //hatch
      if (Robot.m_oi.gameJoyStick.getAButtonPressed()) {
        System.out.println("A mode 2");
        Scheduler.getInstance().add(new ElevatorMoveCommand(-2000.0));
      }
      if (Robot.m_oi.gameJoyStick.getBButtonPressed()) {
        System.out.println("B mode 2");
        Scheduler.getInstance().add(new ElevatorMoveCommand(-24000.0));
      }
      if (Robot.m_oi.gameJoyStick.getXButtonPressed()) {
        System.out.println("X mode 2");
        //Scheduler.getInstance().add(new PneumaticsToggleCommand(3));
      }
      if (Robot.m_oi.gameJoyStick.getYButtonPressed()) {
        System.out.println("Y mode 2");
        Scheduler.getInstance().add(new ElevatorMoveCommand(-32000.0));
      }
      //System.out.println("sdguivhsdfixc");
    }
    //else if (Mode == 0) 
    //{
      //ball
     // if (Robot.m_oi.gameJoyStick.getAButtonPressed()) {
      //  System.out.println("A mode 0");
       // Scheduler.getInstance().add(new ElevatorMoveCommand(-2000.0));
     // }
     // if (Robot.m_oi.gameJoyStick.getBButtonPressed()) {
       // System.out.println("B mode 0");
        //Scheduler.getInstance().add(new ElevatorMoveCommand(-20000.0));
     // }
      //if (Robot.m_oi.gameJoyStick.getYButtonPressed()) {
       // System.out.println("Y mode 0");
        //Scheduler.getInstance().add(new ElevatorMoveCommand(-32000.0));
     // }
      //if (Robot.m_oi.gameJoyStick.getXButtonPressed()) {
       // System.out.println("X mode 0");
        //Scheduler.getInstance().add(new ElevatorMoveCommand(-35000.0));
       //hi 
      //}
   // }
  }
   //System.out.println(rightRear.getSelectedSensorPosition(0));
  
  public double getElevatorEncoder(){//[][][][][][][][][]
    return elevator.getSelectedSensorPosition();//[][][][][][][][][]
  }//[][][][][][][][][]
  
  public void elevatorDrive(double go){
    elevator.set(go);
  }

  public void stopE(){
    elevator.stopMotor();//[][][][][][][][][]
  }
 
}
