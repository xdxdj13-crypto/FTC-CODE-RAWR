package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.controller.PIDController;
import com.seattlesolvers.solverslib.controller.wpilibcontroller.ElevatorFeedforward;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.geometry.Translation2d;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.kinematics.wpilibkinematics.ChassisSpeeds;
import com.seattlesolvers.solverslib.kinematics.wpilibkinematics.MecanumDriveKinematics;
import com.seattlesolvers.solverslib.kinematics.wpilibkinematics.MecanumDriveWheelSpeeds;

public class Chassis {
    Motor rearLeft;
    Motor rearRight;
    Motor frontLeft;
    Motor frontRight;
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


        ChassisSpeeds speeds = new ChassisSpeeds(1.0, 0.0, 0.0);
        MecanumDriveWheelSpeeds wheelSpeeds = MDKinematiks.toWheelSpeeds(speeds);

    }
    //MecanumDriveKinematics(frontLeftUbication, frontRightUbication,rearLeftUbication, rearRightUbication);
    MecanumDriveKinematics MDKinematiks = new MecanumDriveKinematics(
            new Translation2d( -1, 1),
            new Translation2d( .5,1),
            new Translation2d( -1, -1),
            new Translation2d(.5,  -1)
    );

    public void drive(double x, double y, double z){
        ChassisSpeeds chassis = new ChassisSpeeds(x,y,z);
        MecanumDriveWheelSpeeds Speeds = MDKinematiks.toWheelSpeeds(chassis);
        double frontLeft = Speeds.frontLeftMetersPerSecond;
        double frontRight = Speeds.frontRightMetersPerSecond;
        double rearLeft = Speeds.rearLeftMetersPerSecond;
        double rearRight = Speeds.rearRightMetersPerSecond;

        MecanumDriveWheelSpeeds wheelSpeeds =
                new MecanumDriveWheelSpeeds(-15, 20, -13, 15);

        ChassisSpeeds chassisSpeeds =
                MDKinematiks.toChassisSpeeds(wheelSpeeds);

        double forward = chassisSpeeds.vxMetersPerSecond;
        double sideways = chassisSpeeds.vyMetersPerSecond;
        double angular = chassisSpeeds.omegaRadiansPerSecond;

    }


}
