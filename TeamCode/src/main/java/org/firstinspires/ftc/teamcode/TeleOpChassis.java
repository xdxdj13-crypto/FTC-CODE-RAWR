package org.firstinspires.ftc.teamcode;
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


        if(gamepad1.right_bumper){double speedMultiplier= gamepad1.right_bumper ? 0.35 : 1.0; //esto sirve para poder reducir la velocidad
            chassis.drive(x * speedMultiplier, y* speedMultiplier, turn * speedMultiplier, true);}
        else{
            chassis.drive(x,y,turn,true);

        }


        if (gamepad1.a){ //cambio para poder escupir polen con b
            intakeMotor.setPower(-1);
        } else if(gamepad1.b) {
            intakeMotor.setPower(1);
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
        telemetry.addData("Eje Y", y);
        telemetry.addData("Eje X", x);
        telemetry.addData("Giro", turn);
        telemetry.update();


    }
}