package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsistemas.MecanumLib;

@Autonomous(name = "Auto", group = "red")
public class Auto extends LinearOpMode {
    MecanumLib chassis;
    ElapsedTime timer;
    public void runOpMode() throws InterruptedException{
        chassis = new MecanumLib();
        timer=new ElapsedTime();


    }

}
