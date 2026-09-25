package org.firstinspires.ftc.teamcode.Camera;

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
import org.firstinspires.ftc.vision.apriltag.AprilTagClusterDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagSingleDetection;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import java.util.concurrent.atomic.AtomicReference;

public class WEB_MEMIS {
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

    public WEB_MEMIS(HardwareMap hardwareMap) {
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
                .setTagLibrary(AprilTagGameDatabase.getDecodeTagLibrary())
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
                    .addProcessors(detectionProcessor)
                    .setCameraResolution(new Size(1280,720))
                    .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
                    .setAutoStartStreamOnBuild(true)
                    .build();
        }
    }



    public void cameraDetection() {
        if (detectionProcessor == null || detectionProcessor.getDetections().isEmpty()) {
            detection = false;
        } else {
            boolean found = false;
            for (AprilTagDetection detectionItem : detectionProcessor.getDetections()) {
                if (detectionItem instanceof AprilTagSingleDetection) {
                    AprilTagSingleDetection tag = (AprilTagSingleDetection) detectionItem;
                    id = tag.id;

                    if (tag.ftcPose != null) {
                        x = tag.ftcPose.x;
                        y = tag.ftcPose.y;
                        z = tag.ftcPose.z;

                        yaw = tag.ftcPose.yaw;
                        pitch = tag.ftcPose.pitch;
                        roll = tag.ftcPose.roll;

                        range = tag.ftcPose.range;
                        bearing = tag.ftcPose.bearing;
                        elevation = tag.ftcPose.elevation;
                    }
                    found = true;
                }
            }
            detection = found;
        }
    }

    public void stop() {
        if (visionPortal != null) {
            visionPortal.close();
        }
    }

}