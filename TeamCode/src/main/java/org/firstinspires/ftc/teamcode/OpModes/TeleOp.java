package org.firstinspires.ftc.teamcode.OpModes;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Camera.AprilTag;
import org.firstinspires.ftc.teamcode.Chassis;
import org.firstinspires.ftc.teamcode.subsistemas.IntakeMotor;
import org.firstinspires.ftc.teamcode.subsistemas.Ramp;
import org.firstinspires.ftc.teamcode.subsistemas.Shooter;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp", group = "TeleOp")
public class TeleOp extends OpMode {
    double x;
    double y;
    boolean y2;
    double turn;
    double in;
    double shoot;
    Chassis chassis;
    TelemetryPacket packet;

    FtcDashboard ftcDashboard = FtcDashboard.getInstance();
    IntakeMotor intakeMotor;
    Shooter shooter;

    AprilTag aprilTag;
    Ramp ramp;

    @Override
    public void init() {

        aprilTag = new AprilTag(hardwareMap);
        FtcDashboard.getInstance().startCameraStream(aprilTag.visionPortal, 30);
//danny me golpeo 67 veces antier y luego a jostin lo macheteo ayer

        chassis = new Chassis(hardwareMap);
        intakeMotor = new IntakeMotor(hardwareMap);
        shooter = new Shooter(hardwareMap);
        ramp = new Ramp(hardwareMap);





    }

    @Override
    public void loop() {

        UpdateControllers();
        UpdateTelemetry();
        chassis.mover(x,y,turn);
        intakeMotor.setPower(in);
        shooter.setPower(shoot);

        aprilTag.calibrarCamara();
        aprilTag.cameraDetection();

        packet = new TelemetryPacket();
        packet.fieldOverlay()
                .setFill("blue")
                .fillRect(-20, -20, 40, 40)
                .drawGrid(0, 0, 144, 144, 7, 7)
                .drawGrid(-24, 24, 48, 48, 4, 4, Math.toRadians(45), 24, 24, false)
                .fillText("Origin", 0, 0, "8px Arial", -Math.toRadians(0))
                .setTranslation(0, 6 * 12)
                .setRotation(-Math.toRadians(90))
                .setStrokeWidth(1)
                .setStroke("red")
                .setFill("green")
                .setAlpha(1.0)
                .fillRect(-AprilTag.x, AprilTag.y, 18,20);

        ftcDashboard.sendTelemetryPacket(packet);

        if (gamepad1.y) {
            ramp.setAngle(165);
        } else ramp.setAngle(0);






//        if (gamepad1.a){ //cambio para poder escupir polen con b
//            intakeMotor.setPower(-1);
//        } else if(gamepad1.b) {
//            intakeMotor.setPower(1);
//        } else {
//            intakeMotor.Stop();
//        }
    }

    public void UpdateControllers(){
        x = -gamepad1.left_stick_x;
        y = -gamepad1.left_stick_y;
        turn = -gamepad1.right_stick_x;

        in = gamepad2.left_stick_y;
        shoot = gamepad2.right_trigger;
        y2 = gamepad2.y;
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