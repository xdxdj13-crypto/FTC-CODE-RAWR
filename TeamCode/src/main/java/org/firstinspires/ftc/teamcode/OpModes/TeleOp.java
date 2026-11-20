package org.firstinspires.ftc.teamcode.OpModes;
import static org.firstinspires.ftc.teamcode.Camera.AprilTag.range;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Camera.AprilTag;
import org.firstinspires.ftc.teamcode.subsistemas.Chassis;
import org.firstinspires.ftc.teamcode.subsistemas.Intake;
import org.firstinspires.ftc.teamcode.subsistemas.MecanumLib;
import org.firstinspires.ftc.teamcode.subsistemas.Ramp;
import org.firstinspires.ftc.teamcode.subsistemas.Rampa;
import org.firstinspires.ftc.teamcode.subsistemas.ShooterLib;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp", group = "TeleOp")
public class TeleOp extends OpMode {
    double shooting;
    double x;
    double y;
    boolean b;
    boolean y2;
    double turn;
    double in;
    boolean shoot;
    boolean RB1;
    MecanumLib mecanum;
    TelemetryPacket packet;
    FtcDashboard ftcDashboard = FtcDashboard.getInstance();
    //Shooter2 shooter;
    ShooterLib shooter;
//    AprilTag aprilTag;
    Ramp ramp;
    Intake intake;
    boolean a;


    @Override
    public void init() {

//        aprilTag = new AprilTag(hardwareMap);
//        FtcDashboard.getInstance().startCameraStream(aprilTag.visionPortal, 30);
        shooter = new ShooterLib();
        mecanum = new MecanumLib();
        intake = new Intake();
        shooter.init(hardwareMap);
//        ramp = new Ramp();
        mecanum.init(hardwareMap);

        intake.init(hardwareMap);







    }

    @Override
    public void loop() {

        UpdateControllers();
        UpdateTelemetry();

        if(RB1){
            mecanum.drive(x,y,turn,0,true,true);
        } else {
            mecanum.drive(x,y,turn,0,true,false);
        }


        intake.setPower(in);

        if(shoot){
            shooter.shooting(range);
        }

        shooter.setPower(shooting);


       if(b){
           shooter.shoot();
       }

//        shooter.setPower(shoot);

        if(a){
            ramp.states("down");
        }


//        aprilTag.calibrarCamara();
//        aprilTag.cameraDetection();

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



//        if (gamepad1.y) {
//            ramp.setAngle(165);
//        } else ramp.setAngle(0);
    }

    public void UpdateControllers(){
        x = gamepad1.left_stick_x;
        y = -gamepad1.left_stick_y;
        turn = gamepad1.right_stick_x;

        in = gamepad2.left_stick_y;
        shoot = gamepad2.right_trigger_pressed;
        y2 = gamepad2.y;
        RB1 = gamepad2.right_bumper;
        a = gamepad2.a;
        b = gamepad2.b;

        shooting = gamepad2.right_stick_y;

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

        telemetry.addData("Range :", range);
        telemetry.addData("Bearing :", AprilTag.bearing);
        telemetry.addData("Elevation :", AprilTag.elevation);

    }

}