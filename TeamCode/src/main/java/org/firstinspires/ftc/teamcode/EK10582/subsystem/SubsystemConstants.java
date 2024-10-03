package org.firstinspires.ftc.teamcode.EK10582.subsystem;

public class SubsystemConstants {
    public static double SPEED = 0.8;

    public enum clawStates {
        CLOSED(0.0), OPEN(0.2);

        public final double position;
        clawStates(double position){
            this.position = position;
        }

    }
}
