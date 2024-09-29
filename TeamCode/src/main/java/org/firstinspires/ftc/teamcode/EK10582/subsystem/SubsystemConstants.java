package org.firstinspires.ftc.teamcode.EK10582.subsystem;

public class SubsystemConstants {
    public static double SPEED = 0.8;

    // april tags (ripped from centerstage)
    public static int targetAprilTag = 1;
    public static boolean aprilTagsEnabled = true;
    public static int decimation = 3;

    // slides (ripped from centerstage)
    public static double SLIDES_TICKS_TO_INCHES = 0.010722;
    public static double MAX_SLIDE_HEIGHT = 1632;
    public static double MAX_FEEDFORWARD = 0.2;
    public static double slidesTolerance = 50;
    public enum SlideStates {
        FREE(0), BOTTOM(0), LOW(1115), MEDIUM(1300), ADJUSTABLE(-5);

        public final double position;

        SlideStates(double position) {
            this.position = position;
        }
    }
}
