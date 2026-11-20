package org.firstinspires.ftc.teamcode.subsistemas;
import com.qualcomm.robotcore.hardware.HardwareMap;
import dev.easyfamily.EasyStack.hardware.Servo.EasyServo;
import dev.easyfamily.EasyStack.hardware.Servo.EasyServoGroup;
import dev.easyfamily.EasyStack.subsytem.EasySubsystemBase;

public class Ramp extends EasySubsystemBase {
    EasyServo left;
    EasyServo right;
    EasyServoGroup dualRamp;
    @Override
    public void init(HardwareMap hardwareMap) {

        left = new EasyServo(hardwareMap,"left");
        right = new EasyServo(hardwareMap,"right").reverse();

        dualRamp = new EasyServoGroup(right,left)
                .setLimits(0,165);
    }

    public void states(String name){
        dualRamp.addState("down",165)
                .addState("up",0);

        if(name == "down"){
            dualRamp.setState("down");
        } else{
            dualRamp.setState("up");
        }
    }

    public void setAngle(double angle){
        dualRamp.setAngle(angle);
    }




}
