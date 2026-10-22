package org.firstinspires.ftc.teamcode.OpModes;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Chassis;

@Autonomous(name = "Auto", group = "red")
public class Auto extends LinearOpMode {
    Chassis chassis;
    ElapsedTime timer;
    public void runOpMode() throws InterruptedException{
        chassis = new Chassis(hardwareMap);
        timer=new ElapsedTime();

        while (timer.seconds() < 5){
            chassis.auto();
        }
        timer.reset();
        while (timer.seconds()< 5){
            chassis.moveLeft();
        }
        timer.reset();
        while (timer.seconds()< 5){
            chassis.moveRight();
        }
        timer.reset();
        while (timer.seconds()< 5){
            chassis.turnleft();
        }
        timer.reset();
        while (timer.seconds()< 5){
            chassis.turnRight();
        }
        timer.reset();
        while (timer.seconds()< 5){
            chassis.auto();
        }


    }

}
