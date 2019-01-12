package org.usfirst.frc.team1294.robot;

import org.usfirst.frc.team1294.robot.commands.AutonomousDefeatDefense;
import org.usfirst.frc.team1294.robot.commands.AutonomousDefeatDefensePos1;
import org.usfirst.frc.team1294.robot.commands.AutonomousDoNothing;
import org.usfirst.frc.team1294.robot.commands.AutonomousPositionFiveLowGoal;
import org.usfirst.frc.team1294.robot.commands.AutonomousPositionOneLowGoal;
import org.usfirst.frc.team1294.robot.commands.AutonomousPositionTwoLowGoal;
import org.usfirst.frc.team1294.robot.commands.SwitchToClosedLoopMode;
import org.usfirst.frc.team1294.robot.commands.SwitchToOpenLoopMode;
import org.usfirst.frc.team1294.robot.subsystems.ArmSubsystem;
import org.usfirst.frc.team1294.robot.subsystems.BallHandlingSubsystem;
import org.usfirst.frc.team1294.robot.subsystems.CameraSubsystem;
import org.usfirst.frc.team1294.robot.subsystems.DriveBase;
import org.usfirst.frc.team1294.robot.subsystems.Vision;
import org.usfirst.frc.team1294.robot.utilities.VersionInformation;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The main class for the robot. The roboRIO automatically runs this program at the specified
 * times.
 */
public class Robot extends IterativeRobot {
  public static DriveBase driveBase;
  public static CameraSubsystem cameraSubsystem;
  public static OI oi;
  public static Vision visionSubsystem;
  public static BallHandlingSubsystem ballHandleSubsystem;
  public static ArmSubsystem armSubsystem;
  private static Command autoCommand;
  private SendableChooser autoChooser;

  /**
   * This method is called when the robot is first started up. This method is run before it is
   * marked as ready in FMS. Use this to initialize things like gyros and encoders.
   */
  @Override
  public void robotInit() {
	ballHandleSubsystem = new BallHandlingSubsystem();  
    visionSubsystem = new Vision();
    driveBase = new DriveBase();
    cameraSubsystem = new CameraSubsystem();
    armSubsystem = new ArmSubsystem();
    oi = new OI();

    VersionInformation vi = new VersionInformation();
    SmartDashboard.putString("Version", vi.getVersion());
    SmartDashboard.putString("Git-Author", vi.getAuthor());

      SmartDashboard.putData(new SwitchToClosedLoopMode());
      SmartDashboard.putData(new SwitchToOpenLoopMode());



    SmartDashboard.putData(Scheduler.getInstance());

    // SendableChooser for Autonomous Commands
    autoChooser = new SendableChooser();
    autoChooser.addDefault("Defeat Defense", new AutonomousDefeatDefense());
    autoChooser.addObject("Defeat Defense Position 1", new AutonomousDefeatDefensePos1());
    autoChooser.addObject("Low Goal Position 1", new AutonomousPositionOneLowGoal());
    autoChooser.addObject("Low Goal Position 2", new AutonomousPositionTwoLowGoal());
    autoChooser.addObject("Low Goal Position 5", new AutonomousPositionFiveLowGoal());
    autoChooser.addObject("Do Nothing", new AutonomousDoNothing());
    SmartDashboard.putData("Auton Chooser", autoChooser);
  }

  /**
   * Called when the robot first enters disabled (i.e. the robot has just left auto, teleop, or
   * robotInit).
   */
  @Override
  public void disabledInit() {
  }

  /**
   * Called periodically while the robot is disabled.
   */
  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * Called when the robot first enters auto.
   */
  @Override
  public void autonomousInit() {
    autoCommand = (Command) autoChooser.getSelected();
    if (autoCommand != null) {
      autoCommand.start();
      SmartDashboard.putString("AUTO COMMAND: ", autoCommand.toString());
    } else {
      SmartDashboard.putString("AUTO COMMAND: ", "ERROR");
    }
  }

  /**
   * Called periodically during auto.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * Called when the robot first enters teleop.
   */
  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (autoCommand != null) autoCommand.cancel();
  }

    /**
     * Called periodically during teleop.
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putNumber("Gyro Angle", driveBase.getNormalizedAngle());
        SmartDashboard.putNumber("Left Enc", driveBase.getLeftSpeed());
        SmartDashboard.putNumber("Right Enc", driveBase.getRightSpeed());
      SmartDashboard.putBoolean("Is Closed Loop", driveBase.isClosedLoopMode());
    }

  /**
   * Called when the robot first enters test mode.
   */
  @Override
  public void testInit() {
  }

  /**
   * Called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    LiveWindow.run();
  }

}
