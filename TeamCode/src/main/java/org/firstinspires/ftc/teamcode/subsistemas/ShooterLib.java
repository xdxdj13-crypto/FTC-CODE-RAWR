package org.firstinspires.ftc.teamcode.subsistemas;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static org.firstinspires.ftc.teamcode.Constants.Constants.ShooterConstants.KP;
import static org.firstinspires.ftc.teamcode.Constants.Constants.ShooterConstants.KS;
import static org.firstinspires.ftc.teamcode.Constants.Constants.ShooterConstants.KV;
import static org.firstinspires.ftc.teamcode.Constants.Constants.ShooterConstants.targetRPM;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import dev.easyfamily.EasyStack.control.EasyPIDFCoefficients;
import dev.easyfamily.EasyStack.control.EasyPIDFController;
import dev.easyfamily.EasyStack.hardware.Motors.EasyMotor;
import dev.easyfamily.EasyStack.subsytem.EasySubsystemBase;
import dev.easyfamily.EasyStack.util.EasyInterpLUT;

public class ShooterLib extends EasySubsystemBase {
   // private EasyMotor leftShooter;
    private EasyMotor rightShooter;
    EasyPIDFCoefficients coefficients = new EasyPIDFCoefficients(0,0,0,0);
    double proportional = coefficients.kP;
    double integral = coefficients.kI;
    double derivative = coefficients.kD;
    double feedforward = coefficients.kF;
    EasyInterpLUT interpLUT;


    @Override
    public void init(HardwareMap hardwareMap) {
       // leftShooter = new EasyMotor(hardwareMap, "leftShooter").setInverted(true);
        rightShooter = new EasyMotor(hardwareMap,"rightShooter");
        EasyPIDFController controller = new EasyPIDFController(coefficients);


    }

    public void setRPM(double rpm, HardwareMap hwMap) {
        VoltageSensor battery = hwMap.voltageSensor.iterator().next();
        targetRPM = rpm;
        //double power = PID(targetRPM, battery.getVoltage(), rightShooter.getVelocity());
       // leftShooter.setPower(power);
        //rightShooter.setPower(power);
    }
    private static double PID(double targetRpm, double battery, double currentEncoderTicks) {
        double currentRpm = (currentEncoderTicks * 60.0) / 28;
        double kF = targetRpm / (KV  * battery);
        double feedforwardStatic = KS / battery;
        double error = targetRpm - currentRpm;
        double proportional = KP * error;
        double output = kF + proportional + feedforwardStatic;
        return Math.max(0, Math.min(1, output));
    }
    public double getRPM(){
        double currentRpm = (rightShooter.getVelocity() * 60.0) / 28;
        return currentRpm;
    }

    public void shooting(double distance){
        if(distance < 0.5 ||distance>1){
           // leftShooter.setVelocityTicks(5000);
            rightShooter.setVelocityTicks(5000);
        } else{
           // leftShooter.setVelocityTicks(interpLUT.get(distance));
            rightShooter.setVelocityTicks(interpLUT.get(distance));

        }

    }

    public void setPower(double power){
//        leftShooter.setPower(power);
        rightShooter.setPower(power);
        rightShooter.setPowerLimit(0.9);
    }

    public void shoot(){
       // leftShooter.setVelocityTicks(5000);
//        leftShooter.setPower(1);
        rightShooter.setPower(-0.8);
    }

    public void stop(){
        //leftShooter.setPower(0);
        rightShooter.setPower(0);
    }

//    public void Interplut(){
//        interpLUT.add(0,0);
//        interpLUT.add(0,0);
//        interpLUT.add(0,0);
//        interpLUT.add(0,0);
//        interpLUT.createLUT();


    public void telemetry(){
        telemetry.addData("kP",coefficients.kP);
        telemetry.addData("kI",coefficients.kI);
        telemetry.addData("kD",coefficients.kD);
        telemetry.addData("kF",coefficients.kF);
    }
}
