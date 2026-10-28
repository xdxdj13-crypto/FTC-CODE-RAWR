package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

public class Chassis {
    Motor rearLeft;
    Motor rearRight;
    Motor frontLeft;
    Motor frontRight;
    MecanumDrive mecanum;
    double x, y, turn;
    public Chassis(HardwareMap hardwareMap) {

        frontLeft = new Motor(hardwareMap, "frontLeft");
        frontRight = new Motor(hardwareMap, "frontRight");
        rearLeft = new Motor(hardwareMap, "rearLeft");
        rearRight = new Motor(hardwareMap, "rearRight");
        frontLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rearRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rearLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        mecanum = new MecanumDrive(false, frontRight,frontLeft,rearRight,rearLeft);
        mecanum.driveRobotCentric(x, y, turn);

        frontRight.setInverted(false);
        rearRight.setInverted(false);

        frontLeft.setInverted(true);
        rearLeft.setInverted(true);

//        imu = hardwareMap.get(IMU.class, "imu");
//
//        IMU.Parameters imuparameters = new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.LEFT));
//
//        imu.initialize(imuparameters);
//        imu.resetYaw();


    }
    public void mover(double x, double y, double turn){
        mecanum.driveRobotCentric(x,y,turn);

    }

    public void auto(){
        frontLeft.set(.5);
        frontRight.set(.5);
        rearLeft.set(.5);
        rearRight.set(.5);
    }

    public void turnleft(){
        frontLeft.set(-.5);
        frontRight.set(.5);
        rearLeft.set(-.5);
        rearRight.set(.5);
    }

    public void turnRight(){
        frontLeft.set(.5);
        frontRight.set(-.5);
        rearLeft.set(.5);
        rearRight.set(-.5);
    }

    public void moveRight(){
        frontLeft.set(.5);
        frontRight.set(-.5);
        rearLeft.set(-.5);
        rearRight.set(.5);
    }

    public void moveLeft(){
        frontLeft.set(-.5);
        frontRight.set(.5);
        rearLeft.set(.5);
        rearRight.set(-.5);
    }

    public void back(){
        frontLeft.set(-.5);
        frontRight.set(-.5);
        rearLeft.set(-.5);
        rearRight.set(-.5);
    }


}