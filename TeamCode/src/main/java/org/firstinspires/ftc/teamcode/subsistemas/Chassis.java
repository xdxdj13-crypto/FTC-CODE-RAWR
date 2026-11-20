package org.firstinspires.ftc.teamcode.subsistemas;

import com.qualcomm.robotcore.hardware.HardwareMap;
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

        frontRight.setInverted(true);
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
    public void mover(double x, double y, double turn, boolean slow) {
        mecanum.driveRobotCentric(x, y, turn);

    }

}