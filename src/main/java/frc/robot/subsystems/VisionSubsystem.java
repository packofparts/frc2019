package frc.robot.subsystems;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.*;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;

import org.opencv.core.*;
import org.opencv.core.Core.*;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.*;
import org.opencv.objdetect.*;

public class VisionSubsystem extends Subsystem {
    private static final int IMG_WIDTH = 320;
    private static final int IMG_HEIGHT = 240;
    private static final int FPS = 30;
  //  private final CvSink cvSink;
  //  private final CvSource cvSource;
  //  private final UsbCamera usbCamera;

    public VisionSubsystem() {
        CameraServer.getInstance().startAutomaticCapture(0);
        CameraServer.getInstance().startAutomaticCapture(1);
     //   CameraServer.getInstance().getVideo();
     //   CameraServer.getInstance().putVideo("MM", 1080, 720);
   //  UsbCamera usbCamera = new UsbCamera("FLAMingo", 1);
   //  MjpegServer mjpegServer1 = new MjpegServer("serve_USB Camera 0", 1181);
    }

    @Override
    protected void initDefaultCommand() {
        //setDefaultCommand(new GetCameraStreamCommand());
    }

    @Override
    public void periodic() {
      //  SmartDashboard.putData("GRIP/MAGIC", convexHullsOutput());
    }
    //Outputs
    private Mat rgbThresholdOutput = new Mat();
    private Mat hsvThresholdOutput = new Mat();
    private Mat cvAddOutput = new Mat();
    private ArrayList<MatOfPoint> findContoursOutput = new ArrayList<MatOfPoint>();
    private ArrayList<MatOfPoint> convexHullsOutput = new ArrayList<MatOfPoint>();

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    /**
     * This is the primary method that runs the entire pipeline and updates the outputs.
     */
    public void process(Mat source0) {
        // Step RGB_Threshold0:
        Mat rgbThresholdInput = source0;
        double[] rgbThresholdRed = {201.79856115107913, 255.0};
        double[] rgbThresholdGreen = {87.14028776978417, 152.73890784982936};
        double[] rgbThresholdBlue = {89.43345323741006, 157.0904436860068};
        rgbThreshold(rgbThresholdInput, rgbThresholdRed, rgbThresholdGreen, rgbThresholdBlue, rgbThresholdOutput);

        // Step HSV_Threshold0:
        Mat hsvThresholdInput = source0;
        double[] hsvThresholdHue = {142.44604316546764, 180.0};
        double[] hsvThresholdSaturation = {103.19244604316546, 187.55119453924914};
        double[] hsvThresholdValue = {183.45323741007192, 255.0};
        hsvThreshold(hsvThresholdInput, hsvThresholdHue, hsvThresholdSaturation, hsvThresholdValue, hsvThresholdOutput);

        // Step CV_add0:
        Mat cvAddSrc1 = rgbThresholdOutput;
        Mat cvAddSrc2 = hsvThresholdOutput;
        cvAdd(cvAddSrc1, cvAddSrc2, cvAddOutput);

        // Step Find_Contours0:
        Mat findContoursInput = cvAddOutput;
        boolean findContoursExternalOnly = false;
        findContours(findContoursInput, findContoursExternalOnly, findContoursOutput);

        // Step Convex_Hulls0:
        ArrayList<MatOfPoint> convexHullsContours = findContoursOutput;
        convexHulls(convexHullsContours, convexHullsOutput);

    }

    /**
     * This method is a generated getter for the output of a RGB_Threshold.
     * @return Mat output from RGB_Threshold.
     */
    public Mat rgbThresholdOutput() {
        return rgbThresholdOutput;
    }

    /**
     * This method is a generated getter for the output of a HSV_Threshold.
     * @return Mat output from HSV_Threshold.
     */
    public Mat hsvThresholdOutput() {
        return hsvThresholdOutput;
    }

    /**
     * This method is a generated getter for the output of a CV_add.
     * @return Mat output from CV_add.
     */
    public Mat cvAddOutput() {
        return cvAddOutput;
    }

    /**
     * This method is a generated getter for the output of a Find_Contours.
     * @return ArrayList<MatOfPoint> output from Find_Contours.
     */
    public ArrayList<MatOfPoint> findContoursOutput() {
        return findContoursOutput;
    }

    /**
     * This method is a generated getter for the output of a Convex_Hulls.
     * @return ArrayList<MatOfPoint> output from Convex_Hulls.
     */
    public ArrayList<MatOfPoint> convexHullsOutput() {
        return convexHullsOutput;
    }


    /**
     * Segment an image based on color ranges.
     * @param input The image on which to perform the RGB threshold.
     * @param red The min and max red.
     * @param green The min and max green.
     * @param blue The min and max blue.
     * @param output The image in which to store the output.
     */
    private void rgbThreshold(Mat input, double[] red, double[] green, double[] blue,
        Mat out) {
        Imgproc.cvtColor(input, out, Imgproc.COLOR_BGR2RGB);
        Core.inRange(out, new Scalar(red[0], green[0], blue[0]),
            new Scalar(red[1], green[1], blue[1]), out);
    }

    /**
     * Segment an image based on hue, saturation, and value ranges.
     *
     * @param input The image on which to perform the HSL threshold.
     * @param hue The min and max hue
     * @param sat The min and max saturation
     * @param val The min and max value
     * @param output The image in which to store the output.
     */
    private void hsvThreshold(Mat input, double[] hue, double[] sat, double[] val,
        Mat out) {
        Imgproc.cvtColor(input, out, Imgproc.COLOR_BGR2HSV);
        Core.inRange(out, new Scalar(hue[0], sat[0], val[0]),
            new Scalar(hue[1], sat[1], val[1]), out);
    }

    /**
     * Calculates the sum of two Mats.
     * @param src1 the first Mat
     * @param src2 the second Mat
     * @param out the Mat that is the sum of the two Mats
     */
    private void cvAdd(Mat src1, Mat src2, Mat out) {
        Core.add(src1, src2, out);
    }

    /**
     * Sets the values of pixels in a binary image to their distance to the nearest black pixel.
     * @param input The image on which to perform the Distance Transform.
     * @param type The Transform.
     * @param maskSize the size of the mask.
     * @param output The image in which to store the output.
     */
    private void findContours(Mat input, boolean externalOnly,
        List<MatOfPoint> contours) {
        Mat hierarchy = new Mat();
        contours.clear();
        int mode;
        if (externalOnly) {
            mode = Imgproc.RETR_EXTERNAL;
        }
        else {
            mode = Imgproc.RETR_LIST;
        }
        int method = Imgproc.CHAIN_APPROX_SIMPLE;
        Imgproc.findContours(input, contours, hierarchy, mode, method);
    }

    /**
     * Compute the convex hulls of contours.
     * @param inputContours The contours on which to perform the operation.
     * @param outputContours The contours where the output will be stored.
     */
    private void convexHulls(List<MatOfPoint> inputContours,
        ArrayList<MatOfPoint> outputContours) {
        final MatOfInt hull = new MatOfInt();
        outputContours.clear();
        for (int i = 0; i < inputContours.size(); i++) {
            final MatOfPoint contour = inputContours.get(i);
            final MatOfPoint mopHull = new MatOfPoint();
            Imgproc.convexHull(contour, hull);
            mopHull.create((int) hull.size().height, 1, CvType.CV_32SC2);
            for (int j = 0; j < hull.size().height; j++) {
                int index = (int) hull.get(j, 0)[0];
                double[] point = new double[] {contour.get(index, 0)[0], contour.get(index, 0)[1]};
                mopHull.put(j, 0, point);
            }
            outputContours.add(mopHull);
        }
    }
}    