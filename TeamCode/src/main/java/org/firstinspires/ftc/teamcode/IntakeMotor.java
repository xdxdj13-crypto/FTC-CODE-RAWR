package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

public class IntakeMotor {
    public Motor intakeMotor;

    public IntakeMotor(HardwareMap hardwareMap){

        intakeMotor = new Motor(hardwareMap, "IntakeMotor");
        intakeMotor.setInverted(true);
        intakeMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

    }
    public void Stop(){
        intakeMotor.stopMotor();
    }
    public void MoveIntake(){
        intakeMotor.set(1);
    }



}

