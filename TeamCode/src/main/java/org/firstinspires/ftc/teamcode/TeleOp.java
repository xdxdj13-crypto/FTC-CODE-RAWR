package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Camera.AprilTag;
@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp", group = "TeleOp")
public class TeleOp extends OpMode {
    double x;
    double y;
    double turn;
//    Chassis chassis;
//    IntakeMotor intakeMotor;

    AprilTag aprilTag;

    @Override
    public void init() {

        aprilTag = new AprilTag(hardwareMap);




//        chassis = new Chassis(hardwareMap);
//        intakeMotor = new IntakeMotor(hardwareMap);
        FtcDashboard.getInstance().startCameraStream(aprilTag.visionPortal, 0);

    }

    @Override
    public void loop() {
        UpdateControllers();
        aprilTag.cameraDetection();
        TelemetryWebCam();
        UpdateTelemetry();


//        if(gamepad1.right_bumper){double speedMultiplier= gamepad1.right_bumper ? 0.35 : 1.0; //esto sirve para poder reducir la velocidad
//            chassis.drive(x * speedMultiplier, y* speedMultiplier, turn * speedMultiplier, true);}
//        else{
//            chassis.drive(x,y,turn,true);
//
//        }


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
        telemetry.addData("Eje Y", y);
        telemetry.addData("Eje X", x);
        telemetry.addData("Giro", turn);
        telemetry.update();
    }


    public void TelemetryWebCam(){
        telemetry.addData("FPS : ", aprilTag.visionPortal.getFps());
        telemetry.addData("Distance X : ", AprilTag.x);
        telemetry.addData("Distance Y : ", AprilTag.y);
        telemetry.addData("Distance z: ", AprilTag.z);
    }

}