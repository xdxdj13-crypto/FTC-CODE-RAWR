package org.firstinspires.ftc.teamcode.Camera;
/** Metros, radianes. x delante, y izquierda, z arriba; yaw CCW. Inmutable. */
public final class Pose2 {
    public final double x, y, heading;
    public Pose2(double x, double y, double heading) {
        if (!Double.isFinite(x+y+heading)) throw new IllegalArgumentException("Pose no finita");
        this.x=x; this.y=y; this.heading=wrap(heading);
    }
    public static double wrap(double a) { return Math.atan2(Math.sin(a),Math.cos(a)); }
    public Pose2 compose(Pose2 b) {
        double c=Math.cos(heading),s=Math.sin(heading);
        return new Pose2(x+c*b.x-s*b.y,y+s*b.x+c*b.y,heading+b.heading);
    }
    public Pose2 inverse() {
        double c=Math.cos(heading),s=Math.sin(heading);
        return new Pose2(-c*x-s*y,s*x-c*y,-heading);
    }
    public static Pose2 interpolate(Pose2 a,Pose2 b,double u) {
        return new Pose2(a.x+u*(b.x-a.x),a.y+u*(b.y-a.y),a.heading+u*wrap(b.heading-a.heading));
    }
}