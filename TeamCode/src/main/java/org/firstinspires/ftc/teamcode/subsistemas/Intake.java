package org.firstinspires.ftc.teamcode.subsistemas;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import dev.easyfamily.EasyStack.hardware.Motors.EasyMotor;
import dev.easyfamily.EasyStack.subsytem.EasySubsystemBase;

public class Intake extends EasySubsystemBase {

    private EasyMotor intake;

    @Override
    public void init(HardwareMap hardwareMap) {
        intake = new EasyMotor(hardwareMap, "intake");
        intake.setInverted(false);

    }

    public void setPower(double in) {
        intake.setPower(in);

    }

    public void in(){
        intake.setPower(1);
    }

    public void stop(){
        intake.setPower(0);
    }


}