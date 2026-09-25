package org.firstinspires.ftc.teamcode.Camera;

import android.util.Size;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagSingleDetection;

import java.util.ArrayList;
import java.util.List;

public class AprilTagWebcam {
    private AprilTagProcessor aprilTagProcessor;
    private VisionPortal visionPortal;
    private List<AprilTagDetection> tagsDetected = new ArrayList<>();
    private Telemetry telemetry;

    public void init(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;
        aprilTagProcessor = new AprilTagProcessor.Builder()
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
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
            VisionPortal.Builder builder = new VisionPortal.Builder();
            builder.setCamera(webcamName);
            builder.setCameraResolution(new Size(640, 480));
            builder.addProcessor(aprilTagProcessor);
            visionPortal = builder.build();
        } else if (telemetry != null) {
            telemetry.addData("Webcam Error", "No webcam found in HardwareMap!");
        }

    }

    public void UpdateVisionPortal(){
        if (aprilTagProcessor != null) {
            tagsDetected = aprilTagProcessor.getDetections();
        }
    }

    public List<AprilTagDetection> getTagsDetected(){
        return tagsDetected;
    }


    /* CODIGO ORIGINAL:
    public void detectionTelemetry(AprilTagDetection aprilTagDetectionID){
        if(aprilTagDetectionID == null){return;}

        if (aprilTagDetectionID.metadata != null) {
            telemetry.addLine(String.format("\n==== (ID %d) %s", aprilTagDetectionID.id, aprilTagDetectionID.metadata.name));
            telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", aprilTagDetectionID.ftcPose.x, aprilTagDetectionID.ftcPose.y, aprilTagDetectionID.ftcPose.z));
            telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", aprilTagDetectionID.ftcPose.pitch, aprilTagDetectionID.ftcPose.roll, aprilTagDetectionID.ftcPose.yaw));
            telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", aprilTagDetectionID.ftcPose.range, aprilTagDetectionID.ftcPose.bearing, aprilTagDetectionID.ftcPose.elevation));
        } else {
            telemetry.addLine(String.format("\n==== (ID %d) Unknown",aprilTagDetectionID.id));
            telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", aprilTagDetectionID.center.x, aprilTagDetectionID.center.y));
        }
    }

    public AprilTagDetection getTagSpecific(int id){
        for (AprilTagDetection detection : tagsDetected){
            if(detection.id == id){
                return detection;
            }

        }
        return null;
    }
    */

    public void detectionTelemetry(AprilTagDetection aprilTagDetectionID){
        if(aprilTagDetectionID == null){return;}

        if (aprilTagDetectionID instanceof AprilTagSingleDetection) {
            AprilTagSingleDetection singleDet = (AprilTagSingleDetection) aprilTagDetectionID;

            if (singleDet.metadata != null) {
                telemetry.addLine(String.format("\n==== (ID %d) %s", singleDet.id, singleDet.metadata.name));
                if (singleDet.ftcPose != null) {
                    telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", singleDet.ftcPose.x, singleDet.ftcPose.y, singleDet.ftcPose.z));
                    telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", singleDet.ftcPose.pitch, singleDet.ftcPose.roll, singleDet.ftcPose.yaw));
                    telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", singleDet.ftcPose.range, singleDet.ftcPose.bearing, singleDet.ftcPose.elevation));
                }
            } else {
                telemetry.addLine(String.format("\n==== (ID %d) Unknown", singleDet.id));
                if (singleDet.center != null) {
                    telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", singleDet.center.x, singleDet.center.y));
                }
            }
        }
    }

    public AprilTagDetection getTagSpecific(int id){
        for (AprilTagDetection detection : tagsDetected){
            if (detection instanceof AprilTagSingleDetection) {
                AprilTagSingleDetection singleDet = (AprilTagSingleDetection) detection;
                if (singleDet.id == id) {
                    return singleDet;
                }
            }
        }
        return null;
    }

    public void stop(){
        if (visionPortal != null){
            visionPortal.close();
        }
    }


}