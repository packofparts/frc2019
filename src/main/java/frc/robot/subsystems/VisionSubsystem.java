package frc.robot.subsystems;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class VisionSubsystem extends Subsystem {
    public VisionSubsystem() {
        CameraServer.getInstance().startAutomaticCapture();
    }

    @Override
    protected void initDefaultCommand() {
    }
}