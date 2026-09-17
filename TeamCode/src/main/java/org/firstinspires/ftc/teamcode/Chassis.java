package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

public class Chassis {
    Motor rearLeft;
    Motor rearRight;
    Motor frontLeft;
    Motor frontRight;
    MecanumDrive drive;

    public Chassis(HardwareMap hardwareMap){

        frontLeft = new Motor(hardwareMap, "frontLeft");
        frontRight= new Motor(hardwareMap, "frontRight");
        rearLeft = new Motor(hardwareMap, "rearLeft");
        rearRight = new Motor (hardwareMap, "rearRight");

        frontLeft.setInverted(false);
        frontRight.setInverted(true);
        rearLeft.setInverted(false);
        rearRight.setInverted(true);

        frontLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rearRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rearLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);


        drive = new MecanumDrive(frontLeft, frontRight, rearLeft, rearRight);

    }

    public void driveRobotCentric(double x, double y, double turn){
        drive.driveRobotCentric(x, y, turn);
    }
}
