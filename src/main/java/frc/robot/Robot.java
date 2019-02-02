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
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.PneumaticsSubsystem;
import frc.robot.subsystems.UltrasonicSubsystem;
import frc.robot.subsystems.VisionSubsystem;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.GenericHID.Hand;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  //private static final String kDefaultAuto = "Default";
  //private static final String kCustomAuto = "My Auto";
  /*private final WPI_TalonSRX leftFront;
  private final WPI_TalonSRX leftRear;
  private final WPI_TalonSRX rightFront;
  private final WPI_TalonSRX rightRear;
  */
  //private final DifferentialDrive drive;

  //public final Joystick mainStick;
  public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static OI m_oi;
  
  
  public static VisionSubsystem camera;
  public static final String ENCODER_PREFIX = "Drive/Encoders/";
  public static DriveSubsystem driver;
  public static UltrasonicSubsystem ultrasonic;
  public static PneumaticsSubsystem pneumaticsController;
  
 // private final CameraServer cameraServer;

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
    pneumaticsController = new PneumaticsSubsystem(); 
  }

  @Override
  public void robotInit() {
    m_oi = new OI();
    m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
  
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
    
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();
    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
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
    driver.unabort();
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
