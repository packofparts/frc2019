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

/**
 * Add your docs here.
 */
public class PneumaticsSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Solenoid solenoid1 = new Solenoid(RobotMap.pcmcan, RobotMap.solenoid1);
  public Solenoid solenoid2 = new Solenoid(RobotMap.pcmcan, RobotMap.solenoid2);
  public Solenoid solenoid3 = new Solenoid(RobotMap.pcmcan, RobotMap.solenoid3);
  public Solenoid solenoid4 = new Solenoid(RobotMap.pcmcan, RobotMap.solenoid4);
  public Compressor compressor = new Compressor(RobotMap.pcmcan);
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here
  }
  public void solenoidOn(int solenoid) {
    if(solenoid == 1) {
      solenoid1.set(true);
    } else if(solenoid == 2) {
      solenoid2.set(true);
    } else if(solenoid == 3) {
      solenoid3.set(true);
    } else if(solenoid == 4) {
      solenoid4.set(true);
    }
  }
  public void solenoidOff(int solenoid) {
    if(solenoid == 1) {
      solenoid1.set(false);
    } else if(solenoid == 2) {
      solenoid2.set(false);
    } else if(solenoid == 3) {
      solenoid3.set(false);
    } else if(solenoid == 4) {
      solenoid4.set(false);
    }
  }
  public void compressorOn() {
    compressor.start();
  }
  public void compressorOff() {
    compressor.stop();
  }
//  public double compressorPressure() {
//    return compressor.getPressureSwitchValue();
//  }
}
