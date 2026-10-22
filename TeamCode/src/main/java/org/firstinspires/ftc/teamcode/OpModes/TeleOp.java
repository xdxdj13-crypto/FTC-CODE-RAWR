package org.firstinspires.ftc.teamcode.OpModes;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Camera.AprilTag;
import org.firstinspires.ftc.teamcode.Chassis;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp", group = "TeleOp")
public class TeleOp extends OpMode {
    double x;
    double y;
    double turn;
    Chassis chassis;
    TelemetryPacket packet;

    FtcDashboard ftcDashboard = FtcDashboard.getInstance();
//    IntakeMotor intakeMotor;

    AprilTag aprilTag;

    @Override
    public void init() {

        aprilTag = new AprilTag(hardwareMap);
        FtcDashboard.getInstance().startCameraStream(aprilTag.visionPortal, 0);


        chassis = new Chassis(hardwareMap);
//        intakeMotor = new IntakeMotor(hardwareMap);


    }

    @Override
    public void loop() {

        UpdateControllers();
        chassis.drive(x, y, turn);

        packet = new TelemetryPacket();

        packet.fieldOverlay()
                .drawImage("/dash/ftc.jpg", 24, 24, 48, 48)
                .drawImage("/dash/ftc.jpg", 24, 24, 48, 48, Math.toRadians(90), 24, 24, false)
                .drawGrid(0, 0, 144, 144, 7, 7)
                .drawGrid(-24, 24, 48, 48, 4, 4, Math.toRadians(45), 24, 24, false)
                .fillText("Origin", 0, 0, "8px Arial", -Math.toRadians(45), false)
                .fillRect(0, 0, 20, 20)
                .setTranslation(0, 6 * 12)
                .fillRect(0, 0, 20, 20)
                .setRotation(-Math.toRadians(90))
                .setScale(1.0 / 0.0254, 1.0 / 0.0254)
                .setStrokeWidth(1)
                .setStroke("red")
                .setFill("green")
                .setAlpha(1.0)
                .fillRect(AprilTag.x, AprilTag.y, 18, 20);
        ftcDashboard.sendTelemetryPacket(packet);
        aprilTag.calibrarCamara();
        aprilTag.cameraDetection();

        UpdateTelemetry();


//        if (gamepad1.a){ //cambio para poder escupir polen con b
//            intakeMotor.setPower(-1);
//        } else if(gamepad1.b) {
//            intakeMotor.setPower(1);
//        } else {
//            intakeMotor.Stop();
//        }
    }

    public void UpdateControllers(){
        x = gamepad1.left_stick_x;
        y = -gamepad1.left_stick_y;
        turn = gamepad1.right_stick_x;
    }

    //Añadimos esta función para facilitar que la telemetria se actualice
    // y que sea más comprensible el código
    public void UpdateTelemetry(){
        telemetry.update();
        telemetry.addData("Eje Y", y);
        telemetry.addData("Eje X", x);
        telemetry.addData("Giro", turn);


       // telemetry.addData("FPS : ", aprilTag.visionPortal.getFps());
        telemetry.addData("Distance X : ", AprilTag.x);
        telemetry.addData("Distance Y : ", AprilTag.y);
        telemetry.addData("Distance z: ", AprilTag.z);

        telemetry.addData("Range :", AprilTag.range);
        telemetry.addData("Bearing :", AprilTag.bearing);
        telemetry.addData("Elevation :", AprilTag.elevation);

    }

}