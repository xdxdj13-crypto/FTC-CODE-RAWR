package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

public class Shooter {
    public Motor shooterMotor;

    public Shooter(HardwareMap hardwareMap) {
        shooterMotor = new Motor(hardwareMap, "ShooterMotor");
        shooterMotor.setInverted(false);
        shooterMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }

    public void setPower(double power) {
        shooterMotor.set(power);
    }

    public void shoot() {
        shooterMotor.set(1.0);
    }

    public void stop() {
        shooterMotor.stopMotor();
    }
}
