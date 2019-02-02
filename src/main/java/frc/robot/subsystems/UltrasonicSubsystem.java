/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

<<<<<<< HEAD
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Timer;
=======
>>>>>>> 2d37573ef9e08b4c6a2e5de3827bcb3ff1e6cd6f
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
<<<<<<< HEAD
import frc.robot.OI;
=======
>>>>>>> 2d37573ef9e08b4c6a2e5de3827bcb3ff1e6cd6f

public class UltrasonicSubsystem extends Subsystem {
  protected double longnumber;
  AnalogInput frontUltrasonic = new AnalogInput(3);
  AnalogInput rearUltrasonic = new AnalogInput(2);
  public XboxController driveJoyStick = new XboxController(0);
  
  public UltrasonicSubsystem() {
    longnumber = 105.02223949561687610657684501447;
  }

  @Override
  public void periodic() {
    double frontDis = frontUltrasonic.getAverageVoltage();
    double rearDis = rearUltrasonic.getAverageVoltage();
    SmartDashboard.putNumber("frontultrasonic/raw", frontDis*longnumber);
    SmartDashboard.putNumber("rearultrasonic/raw", rearDis*longnumber);

    if (frontDis < 20) {
      System.out.println("Rumbling");
    //  driveJoyStick.setRumble(RumbleType.kLeftRumble, 1);
     // driveJoyStick.setRumble(RumbleType.kRightRumble, 1);
    }
    else {
      System.out.println("No Rumbling");
    //  driveJoyStick.setRumble(RumbleType.kLeftRumble, 0);
     // driveJoyStick.setRumble(RumbleType.kRightRumble, 0);
    }
  //  Timer.delay(0.1);
  }


  @Override
  protected void initDefaultCommand() {
  }


  public void arcadeDrive(double forward, double turn) {
  }

  public void stop() {
  }
}
