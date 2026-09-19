package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleOp Chassis", group = "TeleOp")
public class TeleOpChassis extends LinearOpMode {//...
    double x;
    double y;
    double turn;

    @Override
    public void runOpMode() {

        Chassis chassis = new Chassis(hardwareMap);
        IntakeMotor intakeMotor = new IntakeMotor(hardwareMap);

        telemetry.addData("Estado", "Esperando xd");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {




            chassis.drive(x,y,turn);


            if (gamepad1.a){
                intakeMotor.MoveIntake();
            } else {
                intakeMotor.Stop();
            }


            telemetry.addData("Eje Y", y);
            telemetry.addData("Eje X", x);
            telemetry.addData("Giro", turn);
            telemetry.update();
        }
    }

    public void UpdateControllers(){
        x = gamepad1.left_stick_x;
        y = -gamepad1.left_stick_y;
        turn = gamepad1.right_stick_x;
    }
}