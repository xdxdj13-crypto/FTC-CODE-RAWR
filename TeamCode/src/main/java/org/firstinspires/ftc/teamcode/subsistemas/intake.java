package org.firstinspires.ftc.teamcode.subsistemas;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class intake {

    private DcMotor motor;

    public intake(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotor.class, "intake");
        motor.setDirection(DcMotor.Direction.FORWARD);
    }

    public void setPower(boolean on) {
        if (on) {
            motor.setPower(1.0);
        } else {
            motor.setPower(0);
        }
    }
}