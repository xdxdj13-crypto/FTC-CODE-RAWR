package org.firstinspires.ftc.teamcode;

//import static rg.firstinspires.ftc.teamcode.Camera_Autonomous.cameraOrientation;
//import static org.firstinspires.ftc.teamcode.RobotModes.Autos.Autonomous.Camera_Autonomous.cameraPosition;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Size;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.function.Consumer;
import org.firstinspires.ftc.robotcore.external.function.Continuation;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamSource;
import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagSingleDetection;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import java.util.concurrent.atomic.AtomicReference;

public class Camera_Detection{
    public Camera_Stream streamProcessor = new Camera_Stream();
    public AprilTagProcessor detectionProcessor;
    public VisionPortal visionPortal;

    public CameraCalibration cameraCalibration;
    public static double x;
    public static double y;
    public static double z;
    public static double yaw;
    public static double pitch;
    public static double roll;
    public static double bearing;
    public static double range;
    public static double elevation;
    public static double id;
    public static boolean detection;

    private final AtomicReference<Bitmap> lastFrame = new AtomicReference<>
            (Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565));

    public Camera_Detection(HardwareMap hardwareMap) {
        // We create the builder with our desired building for the AprilTag processor
        // and the VisionPortal
        //Set the orientation of the camera in the robot
        YawPitchRollAngles cameraOrientation = new YawPitchRollAngles(AngleUnit.DEGREES,
                0, -90, 0, 0);
        // Set the position of the camera in the robot
        Position cameraPosition = new Position(DistanceUnit.INCH,
                0, 0, 0, 0);

        detectionProcessor = new AprilTagProcessor.Builder()
                //Set Camera´s position and orientation in the robot
                .setCameraPose(cameraPosition, cameraOrientation)
                //Specify the april Tags that we will use this competition
                .setTagFamily(AprilTagProcessor.TagFamily.TAG_36h11)
                .setTagLibrary(AprilTagGameDatabase.getCurrentGameTagLibrary())
                //Specify the units we want to use for the output detections
                .setOutputUnits(DistanceUnit.CM, AngleUnit.DEGREES)
                .build();

        WebcamName webcamName = null;
        try {
            webcamName = hardwareMap.get(WebcamName.class, "WebCam");
        } catch (Exception e) {
            try {
                webcamName = hardwareMap.get(WebcamName.class, "webcam");
            } catch (Exception e2) {
                if (!hardwareMap.getAll(WebcamName.class).isEmpty()) {
                    webcamName = hardwareMap.getAll(WebcamName.class).get(0);
                }
            }
        }

        if (webcamName != null) {
            visionPortal = new VisionPortal.Builder()
                    .setCamera(webcamName)
                    .addProcessors(detectionProcessor, streamProcessor)
                    .setCameraResolution(new Size(1280,720))
                    .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
                    .setAutoStartStreamOnBuild(true)
                    .build();
        }
    }



    /* CODIGO ORIGINAL:
    // .setTagLibrary(AprilTagGameDatabase.getBiobuzzTagLibrary())

    public void cameraDetection() {
        if (detectionProcessor.getDetections().isEmpty()) {
            detection = false;
        } else {
            for (AprilTagDetection detection : detectionProcessor.getDetections()) {
                // We put the detection values into the detectionValues array
                id = detection.id;

                //Getting xDistance, yDistance and zDistance
                x = detection.ftcPose.x;
                y = detection.ftcPose.y;
                z = detection.ftcPose.z;

                //Getting Yaw, Pitch and Roll, used on angulation/orientation
                yaw = detection.ftcPose.yaw;
                pitch = detection.ftcPose.pitch;
                roll = detection.ftcPose.roll;

                //Getting range, bearing and elevation
                range = detection.ftcPose.range;
                bearing = detection.ftcPose.bearing;
                elevation = detection.ftcPose.elevation;
            }
            detection = true;
        }
    }
    */

    public void cameraDetection() {
        if (detectionProcessor.getDetections().isEmpty()) {
            detection = false;
        } else {
            for (AprilTagDetection detectionItem : detectionProcessor.getDetections()) {
                if (detectionItem instanceof AprilTagSingleDetection) {
                    AprilTagSingleDetection singleDetection = (AprilTagSingleDetection) detectionItem;

                    id = singleDetection.id;

                    if (singleDetection.ftcPose != null) {
                        // Getting xDistance, yDistance and zDistance
                        x = singleDetection.ftcPose.x;
                        y = singleDetection.ftcPose.y;
                        z = singleDetection.ftcPose.z;

                        // Getting Yaw, Pitch and Roll, used on angulation/orientation
                        yaw = singleDetection.ftcPose.yaw;
                        pitch = singleDetection.ftcPose.pitch;
                        roll = singleDetection.ftcPose.roll;

                        // Getting range, bearing and elevation
                        range = singleDetection.ftcPose.range;
                        bearing = singleDetection.ftcPose.bearing;
                        elevation = singleDetection.ftcPose.elevation;
                    }
                }
            }
            detection = true;
        }
    }

    public void stop() {
        if (visionPortal != null) {
            visionPortal.close();
        }
    }
}