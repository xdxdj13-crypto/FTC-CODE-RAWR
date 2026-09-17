package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleOp Chassis", group = "TeleOp")
public class TeleOpChassis extends LinearOpMode {

    @Override
    public void runOpMode() {

        Chassis chassis = new Chassis(hardwareMap);
        IntakeMotor intakeMotor = new IntakeMotor(hardwareMap);

        telemetry.addData("Estado", "Esperando xd");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double turn = gamepad1.right_stick_x;


            chassis.driveRobotCentric(x, y, turn);


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
}