package org.firstinspires.ftc.teamcode;

import android.renderscript.FieldPacker;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.controller.PIDController;
import com.seattlesolvers.solverslib.controller.wpilibcontroller.ElevatorFeedforward;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.geometry.Rotation2d;
import com.seattlesolvers.solverslib.geometry.Translation2d;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.kinematics.wpilibkinematics.ChassisSpeeds;
import com.seattlesolvers.solverslib.kinematics.wpilibkinematics.MecanumDriveKinematics;
import com.seattlesolvers.solverslib.kinematics.wpilibkinematics.MecanumDriveWheelSpeeds;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class Chassis {
    Motor rearLeft;
    Motor rearRight;
    Motor frontLeft;
    Motor frontRight;
    IMU imu;
    boolean fieldOriented;
    public Chassis(HardwareMap hardwareMap) {

        frontLeft = new Motor(hardwareMap, "frontLeft");
        frontRight = new Motor(hardwareMap, "frontRight");
        rearLeft = new Motor(hardwareMap, "rearLeft");
        rearRight = new Motor(hardwareMap, "rearRight");


        frontLeft.setInverted(false);
        frontRight.setInverted(true);
        rearLeft.setInverted(false);
        rearRight.setInverted(true);

        frontLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rearRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        rearLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        imu = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters imuparameters = new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.DOWN, RevHubOrientationOnRobot.UsbFacingDirection.LEFT));

        imu.initialize(imuparameters);
        imu.resetYaw();

    }
    MecanumDriveKinematics MDKinematics = new MecanumDriveKinematics(
            Constants.MechanumConstants.frontLeftPose,
            Constants.MechanumConstants.frontRightPose,
            Constants.MechanumConstants.rearLeftPose,
            Constants.MechanumConstants.rearRightPose

    );

    public void drive(double x, double y, double z, boolean fieldOriented){

        ChassisSpeeds chassis_speeds = fieldOriented ? ChassisSpeeds.toFieldRelativeSpeeds(
                new ChassisSpeeds(x,y,z),
                new Rotation2d(imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS))

        ) : new ChassisSpeeds(x,y,z);
        MecanumDriveWheelSpeeds Speeds = MDKinematics.toWheelSpeeds(chassis_speeds);



        frontLeft.set(Speeds.frontLeftMetersPerSecond);
        frontRight.set(Speeds.frontRightMetersPerSecond);
        rearLeft.set(Speeds.rearLeftMetersPerSecond);
        rearRight.set(Speeds.rearRightMetersPerSecond);


    }


}