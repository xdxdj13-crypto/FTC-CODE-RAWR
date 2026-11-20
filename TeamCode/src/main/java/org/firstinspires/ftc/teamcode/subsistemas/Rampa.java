package org.firstinspires.ftc.teamcode.subsistemas;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoExGroup;

import dev.easyfamily.EasyStack.subsytem.EasySubsystemBase;

public class Rampa extends EasySubsystemBase {
    ServoEx derecha;
    ServoEx izquierda;
    private ServoEx leader;
    private ServoEx follower1;
    ServoExGroup myServos;

    @Override
    public void init(HardwareMap hardwareMap) {
        izquierda = new ServoEx(hardwareMap, "izquierda", 0, 165);
        derecha = new ServoEx(hardwareMap, "derecha", 0, 165);
        derecha.setInverted(true);
        leader = derecha;
        follower1 = izquierda;
        izquierda = myServos;

        myServos = new ServoExGroup(leader, follower1);
    }

    public void setAngle(double angle) {
        myServos.set(angle);
    }
}