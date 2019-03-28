/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * Add your docs here.
 */
public class VisionSubsystem extends Subsystem {  
  // Put methods for controlling this subsystem
  // here. Call these from Commands.\
  UsbCamera usbcamera = CameraServer.getInstance().startAutomaticCapture(0);
  CvSink cvSink = CameraServer.getInstance().getVideo();
  CvSource outputStream = CameraServer.getInstance().putVideo("GrayScale", -1, -1);
  Mat source = new Mat();
  Mat output = new Mat();

  public VisionSubsystem() {
   // camera.setResolution(640, 480);
    //camera.setFPS(30);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
     //setDefaultCommand(new GrayScaleCommand());
  }

  @Override
  public void periodic() {
  //  cvSink.grabFrame(source);
  //  Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
  //  outputStream.putFrame(output);
  }
  
}