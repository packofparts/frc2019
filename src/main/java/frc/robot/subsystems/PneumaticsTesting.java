/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.PneumaticsTestingCommand;
/**
 * Add your docs here.
 */
public class PneumaticsTesting extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Solenoid testing1 = new Solenoid(RobotMap.pcmcan, RobotMap.pneumaticTest);
  public Compressor testing = new Compressor();
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new PneumaticsTestingCommand());

  }
  public void on() {
    testing1.set(true);
  }
  
  public void off() {
    testing1.set(false);
  }
  public void compOn() {
    testing.start();
  }
  public void compOff() {
    testing.stop();
  }
}
