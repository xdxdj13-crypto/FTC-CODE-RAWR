package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleOp Chassis", group = "TeleOp")
public class TeleOpChassis extends OpMode {//...
    double x;
    double y;
    double turn;
    Chassis chassis;
    IntakeMotor intakeMotor;

    @Override
    public void init() {
        chassis = new Chassis(hardwareMap);
        intakeMotor = new IntakeMotor(hardwareMap);

    }

    @Override
    public void loop() {
        UpdateControllers();
        UpdateTelemetry();

        chassis.drive(x,y,turn,true);


        if (gamepad1.a){
            intakeMotor.MoveIntake();
        } else {
            intakeMotor.Stop();
        }

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

    }
}