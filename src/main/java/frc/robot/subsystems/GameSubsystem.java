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
  public int Mode = 1;
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
    if (Mode == 1) {
        elevatorDrive(Robot.m_oi.getRightYGame());
    }

    if (Robot.m_oi.getDpadGame() == 0) {
      Mode = 1;
    }
    else if (Robot.m_oi.getDpadGame() == 90) {
      Mode = 0;
    }
    else if (Robot.m_oi.getDpadGame() == 180) {
      Mode = 0;
    }
        
    JoystickButton gameAButton = new JoystickButton(Robot.m_oi.gameJoyStick, 1);
    JoystickButton gameBButton = new JoystickButton(Robot.m_oi.gameJoyStick, 3);
    JoystickButton gameXButton = new JoystickButton(Robot.m_oi.gameJoyStick, 2);
    JoystickButton gameYButton = new JoystickButton(Robot.m_oi.gameJoyStick, 4);

    //This defines ABXY for game controller to toggle solenoids
    if (Mode == 0) {
      gameAButton.close();
      gameBButton.close();
      gameXButton.close();
      gameYButton.close();
      gameAButton.toggleWhenActive(new PneumaticsToggleCommand(1));
      gameBButton.toggleWhenActive(new PneumaticsToggleCommand(2));
      gameXButton.toggleWhenActive(new PneumaticsToggleCommand(3));
      gameYButton.toggleWhenActive(new PneumaticsToggleCommand(4));
      //System.out.println("Climb");
    }
    else if (Mode == 1) {
      gameAButton.close();
      gameBButton.close();
      gameXButton.close();
      gameYButton.close();
      //System.out.println("Game");
      gameAButton.toggleWhenActive(new ElevatorMoveCommand(0.0));
      gameBButton.toggleWhenActive(new ElevatorMoveCommand(28374.0));
      gameYButton.toggleWhenActive(new ElevatorMoveCommand(36313.0));
    }
    else if (Mode == 2) {
      gameAButton.close();
      gameBButton.close();
      gameXButton.close();
      gameYButton.close();
      System.out.println("Mode 2");
    }
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
