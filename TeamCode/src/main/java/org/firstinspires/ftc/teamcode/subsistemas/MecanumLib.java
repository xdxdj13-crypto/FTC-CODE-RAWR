package org.firstinspires.ftc.teamcode.subsistemas;


import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import dev.easyfamily.EasyStack.drivebase.EasyMecanumDrive;
import dev.easyfamily.EasyStack.hardware.Motors.EasyMotor;
import dev.easyfamily.EasyStack.subsytem.EasySubsystemBase;

public class MecanumLib extends EasySubsystemBase {
    double x;
    double y;
    double turn;
    EasyMotor frontLeft;
    EasyMotor frontRight;
    EasyMotor rearLeft;
    EasyMotor rearRight;
    EasyMecanumDrive mechanum;

    @Override
    public void init(HardwareMap hardwareMap) {
        frontLeft = new EasyMotor(hardwareMap,"frontLeft");
        frontRight = new EasyMotor(hardwareMap, "frontRight");
        rearLeft = new EasyMotor(hardwareMap, "rearLeft");
        rearRight = new EasyMotor(hardwareMap,"rearRight");
        mechanum = new EasyMecanumDrive(false,frontLeft,frontRight,rearLeft,rearRight);

        frontLeft.setInverted(true);
        rearLeft.setInverted(true);

        frontRight.setInverted(false);
        rearRight.setInverted(false);


    }

    public void drive(double x,double y,double turn,double headingRadians, boolean fieldCentric, boolean RB1){
        mechanum.setMaxOutput(1);

        if(fieldCentric){
            mechanum.driveFieldCentric(x,y,turn,headingRadians,true);
        }else {
            mechanum.driveRobotCentric(RB1 ? x * .3 : x,RB1 ? y * .3 : turn ,RB1 ? turn * .3 : turn,true);
        }
    }

    public void stop(){
        mechanum.stop();
    }


}
