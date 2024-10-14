package org.firstinspires.ftc.teamcode.EK10582.subsystem;

public class SubsystemConstants {
    public static double SPEED = 0.8;

    public enum clawStates {
        CLOSED(0.0), OPEN(0.28);

        public final double position;

        clawStates(double position) {
            this.position = position;
        }
    }

    public enum wristStates {
        DOWN(1.0), UP(0.85);

        public final double position;

        wristStates(double position) {
            this.position = position;
        }
    }

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

    public enum ArmStates {
        FREE(0), GROUND(-1000), UP(2000);

        public final int position;

        ArmStates(int position) { this.position = position; }
    }

    //Hanging
    public enum HangingStates {
        GROUND, LOW_HANG, HIGH_HANG;

    }

    public static double lowHang;
    public static double highHang;
}
