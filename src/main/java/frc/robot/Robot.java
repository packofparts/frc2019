/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

//import com.ctre.phoenix.motorcontrol.FeedbackDevice;
//import edu.wpi.first.cameraserver.CameraServer;
//import edu.wpi.first.vision.VisionRunner;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ChezyDriveCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.IntakeOffsetCommand;
import frc.robot.commands.ResetEncoders;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.GameSubsystem;
import frc.robot.subsystems.PneumaticsSubsystem;
import frc.robot.subsystems.UltrasonicSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import frc.robot.RobotMap;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends TimedRobot {
  public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static OI m_oi;
  
  
  public static VisionSubsystem camera;
  public static final String ENCODER_PREFIX = "Drive/Encoders/";
  public static DriveSubsystem driver;
  public static GameSubsystem gamer;
  public static UltrasonicSubsystem ultrasonic;
  public static PneumaticsSubsystem pneumaticsController;
  boolean started = false;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  public Robot() {
    ultrasonic = new UltrasonicSubsystem();
    camera = new VisionSubsystem();
    driver = new DriveSubsystem();
    gamer = new GameSubsystem();
    pneumaticsController = new PneumaticsSubsystem(); 
  }

  @Override
  public void robotInit() {
    m_oi = new OI();
    m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
  
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
    SmartDashboard.putData("Reset Encoders", new ResetEncoders());
  }

  @Override
  public void robotPeriodic() {
    started = true;
    
    if (Robot.driver.pdp.getVoltage() < 12.5) {
      SmartDashboard.putString("Drive/Comms/1", "My battery is low and it's getting dark...");
    }
    else {
      SmartDashboard.putString("Drive/Comms/1", "90 days");
    }
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
    //System.out.println(started);
    if (Robot.driver.pdp.getVoltage() < 12.5) {
      SmartDashboard.putString("Drive/Comms/1", "My battery is low and it's getting dark...");
    }
    else {
      SmartDashboard.putString("Drive/Comms/1", "90 days");
    }
    started = false;
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();
    // schedule the autonomous command (example)
   // if (m_autonomousCommand != null) {
   //   m_autonomousCommand.start();
   // }

  driver.treads.setSafetyEnabled(true);
    Scheduler.getInstance().add(new ChezyDriveCommand());
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    driver.treads.setSafetyEnabled(true);
    Scheduler.getInstance().add(new ChezyDriveCommand());
    Scheduler.getInstance().add(new IntakeOffsetCommand(0.25));
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    System.out.println("Operator Control Started");
    
    
    while (isOperatorControl() && isEnabled()) {
      Scheduler.getInstance().run();
      Timer.delay(0.005);
      
    }
        
  }

  @Override
  public void testPeriodic() {
  }
}
