package org.firstinspires.ftc.teamcode.Camera;

import android.graphics.Bitmap;
import android.util.Size;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagSingleDetection;

import java.util.concurrent.atomic.AtomicReference;

public class AprilTag {
    public AprilTagProcessor detectionProcessor;
    public VisionPortal visionPortal;
    public static boolean detection;
    public static double x;
    public static double y;
    public static double z;
    public static double id;
    public static double yaw;
    public static double pitch;
    public static double roll;
    public static double range;
    public static double bearing;
    public static double elevation;

    public final AtomicReference<Bitmap> lastFrame = new AtomicReference<>(
            Bitmap.createBitmap(1,1,Bitmap.Config.RGB_565));

    public AprilTag(HardwareMap hardwareMap){
        YawPitchRollAngles orientationCamera = new YawPitchRollAngles(AngleUnit.DEGREES,
                0, -90, 0, 0);

        Position positionCamera = new Position(DistanceUnit.INCH, 0, 0, 0, 0);

        detectionProcessor = new AprilTagProcessor.Builder().
                setCameraPose(positionCamera, orientationCamera)
                .setTagFamily(AprilTagProcessor.TagFamily.TAG_36h11)
                .setTagLibrary(AprilTagGameDatabase.getBioBuzzTagLibrary())
                .setOutputUnits(DistanceUnit.CM, AngleUnit.DEGREES)
                .build();

        visionPortal = new VisionPortal.Builder()
                .setCamera(hardwareMap.get(WebcamName.class, "WebCam"))
                .addProcessor(detectionProcessor)
                .setCameraResolution(new Size(640,480))
                .setStreamFormat(VisionPortal.StreamFormat.MJPEG)
                .setAutoStartStreamOnBuild(true)
                .build();

        }
    public void cameraDetection() {
        if (detectionProcessor.getDetections().isEmpty()) {
            detection = false;
        } else {
            for (AprilTagDetection detection : detectionProcessor.getDetections()) {
                AprilTagSingleDetection tag = (AprilTagSingleDetection) detection;
                id = tag.id;

                x = detection.ftcPose.x;
                y = detection.ftcPose.y;
                z = detection.ftcPose.z;

                yaw = detection.ftcPose.yaw;
                pitch = detection.ftcPose.pitch;
                roll = detection.ftcPose.roll;

                range = detection.ftcPose.range;
                bearing = detection.ftcPose.bearing;
                elevation = detection.ftcPose.elevation;
            }
            detection = true;
        }

    }

}

