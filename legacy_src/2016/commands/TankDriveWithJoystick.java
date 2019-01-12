package org.usfirst.frc.team1294.robot.commands;

import org.usfirst.frc.team1294.robot.Robot;
import org.usfirst.frc.team1294.robot.subsystems.CameraSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Have the robot drive tank style using the PS3 Joystick until interrupted.
 *
 * @deprecated replaced with ArcadeDriveCommand.
 * This method no longer works due to the removal of the second joystick in OI.
 */
@Deprecated
public class TankDriveWithJoystick extends Command {
    
    public TankDriveWithJoystick() {
        requires(Robot.driveBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double left;
        double right;

        if (Robot.cameraSubsystem.getCurrentCamera() == CameraSubsystem.Camera.BACK) {
//            left = Robot.oi.getStickRight().getY() * -1;
            right = Robot.oi.getStickLeft().getY() * -1;
        } else {
            left = Robot.oi.getStickLeft().getY();
//            right = Robot.oi.getStickRight().getY();
        }

//        Robot.driveBase.tankDrive(left, right);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true; // Runs until interrupted
    }

    // Called once after isFinished returns true
    protected void end() {
        
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}