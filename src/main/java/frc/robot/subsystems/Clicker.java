/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.commands.SingleClick;
/**
 * Add your docs here.
 */
public class Clicker extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static boolean checking = true;
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  @Override
  public void periodic() {
   /* if(Robot.m_oi.getMenuDrive() && checking) {
      Scheduler.getInstance().add(new SingleClick());
      checking = !checking;
    } */
  }
}
