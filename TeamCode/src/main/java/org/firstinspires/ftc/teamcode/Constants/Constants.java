package org.firstinspires.ftc.teamcode.Constants;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.Camera.Pose2;

import dev.easyfamily.EasyStack.dashboard.EasyTunable;

public class Constants{
    Pose2 pose2;
    public static class ShooterConstants {
        @EasyTunable public static double KS = 0;
        @EasyTunable public static double KV = 0;
        @EasyTunable public static double KP = 0;
        @EasyTunable public static double targetRPM = 0;

        @EasyTunable public static double speed = 0;

//        public static double kP = 0;
//        public static double kI = 0;
//        public static double kD = 0;
//        public static double kF = 0;

    }

    public static class Pose2d{

        //Q1
        public static double upRedX = 14.3;
        public static double upRedY = 12.6;

        //Q2
        public static double upBlueX= -14.3 ;
        public static double upBlueY = 12.6;

        //Q3
        public static double downBlueX = -14.3 ;
        public static double downBlueY = -12.6 ;

        //Q4
        public static double downRedX = 14.3 ;
        public static double downRedY = -12.6 ;


    }
}
