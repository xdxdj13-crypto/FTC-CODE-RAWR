package org.firstinspires.ftc.teamcode.subsistemas;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoExGroup;

public class Ramp {
    private ServoEx leader;
    ServoEx ramp;

    private ServoEx follower1;
    ServoEx ramp2;
    ServoExGroup myServos;

    public Ramp(HardwareMap hardwareMap) {
        ramp = new ServoEx(hardwareMap, "izquierda", 0, 165);
        ramp2 = new ServoEx(hardwareMap, "derecha", 0, 165);
        ramp2.setInverted(true);
        leader = ramp;
        follower1 = ramp2;
        ramp2 =
        myServos = new ServoExGroup(leader, follower1);
    }

    public void setAngle(double angle) {
        myServos.set(angle);
    }

}