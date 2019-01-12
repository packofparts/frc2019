package org.usfirst.frc.team1294.robot.subsystems;

import org.usfirst.frc.team1294.robot.RobotMap;
import org.usfirst.frc.team1294.robot.commands.BallHandlingCommand;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Austin Jenchi (timtim17)
 */
public class BallHandlingSubsystem extends Subsystem {

  //private CANTalon talonCatapult;
  private CANTalon talonBallIntake;
  //private CANTalon talonCatapultGear;
  //private DigitalInput gearLimitSwitch;

  public BallHandlingSubsystem() {
    //talonCatapult = new CANTalon(RobotMap.catapultTalon);
    talonBallIntake = new CANTalon(RobotMap.intakeTalon);
    //talonCatapultGear = new CANTalon(RobotMap.catapultGearTalon);
    //gearLimitSwitch = new DigitalInput(RobotMap.gearLimitSwitch);
  }

  // TODO: delete this? not sure if we need this
  @Deprecated
  public void forwardsCatatpult() {
    //talonCatapult.set(RobotMap.catapultSpeed);
  }

  public void backwardsCatapult() {
    //talonCatapult.set(-RobotMap.catapultSpeed);
  }

  /**
   * @param direction the direction to move the intake motors in.
   * @deprecated use #setIntake(double) instead.
   */
  @Deprecated
  public void setIntakeDirection(IntakeDirection direction) {
    if (direction == IntakeDirection.IN) talonBallIntake.set(RobotMap.intakeSpeed);
    else if (direction == IntakeDirection.OUT) talonBallIntake.set(-RobotMap.intakeSpeed);
    else talonBallIntake.set(0);
  }

  public void setGear(double speed) {
    //talonCatapultGear.set(speed);
  }

  public void stopGear() {
    //talonCatapultGear.set(0);
  }

//  public boolean isCatapultLimitSwitchClosed() {
//    //return talonCatapult.isRevLimitSwitchClosed();
//  }

//  public boolean isGearLimitSwitchClosed() {
//    return gearLimitSwitch.get();
//  }

  @Override
  protected void initDefaultCommand() {
    setDefaultCommand(new BallHandlingCommand());
  }

  public void stopCatapult() {
    //talonCatapult.set(0);
  }

  public void setIntake(double intake) {
    talonBallIntake.set(intake);
  }

  public void stopIntake() {
    talonBallIntake.set(0);
  }

  /**
   * @deprecated
   */
  @Deprecated
  public enum IntakeDirection {IN, OUT, STOP}
}
