package org.firstinspires.ftc.teamcode;

import com.seattlesolvers.solverslib.geometry.Translation2d;
import com.seattlesolvers.solverslib.kinematics.wpilibkinematics.MecanumDriveKinematics;

public class  Constants {
    public static class MechanumConstants{
        public static final Translation2d frontLeftPose  = new Translation2d( 0.2,  0.2); // Adelante a la Izquierda (+X, +Y)
        public static final Translation2d frontRightPose = new Translation2d( 0.2, -0.2); // Adelante a la Derecha   (+X, -Y)
        public static final Translation2d rearLeftPose   = new Translation2d(-0.2,  0.2); // Atrás a la Izquierda   (-X, +Y)
        public static final Translation2d rearRightPose  = new Translation2d(-0.2, -0.2); // Atrás a la Derecha     (-X, -Y)
    }


}


