package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsistemas.Chassis;

@Autonomous(name = "Auto", group = "red")
public class Auto extends LinearOpMode {
    Chassis chassis;
    ElapsedTime timer;
    public void runOpMode() throws InterruptedException{
        chassis = new Chassis(hardwareMap);
        timer=new ElapsedTime();


    }

}
