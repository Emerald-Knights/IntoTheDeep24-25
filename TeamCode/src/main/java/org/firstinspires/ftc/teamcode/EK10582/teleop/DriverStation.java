package org.firstinspires.ftc.teamcode.EK10582.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
class JoystickConstants {
    public static double DEADZONE = 0.01;
    public static double minJoystick = 0.1;
    public static double maxJoystick = 1;
}

public class DriverStation {
    Gamepad gamepad1, gamepad2;

    public DriverStation(Gamepad gamepad1, Gamepad gamepad2) {
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
    }

    //-----------------------first controller------------------------------

    public double getLeftStickY() {
        return -filterJoystick(gamepad1.left_stick_y);
    }

    public double getLeftStickX() {
        return filterJoystick(gamepad1.left_stick_x);
    }

    public double getRightStickX() {
        return filterJoystick(gamepad1.right_stick_x);
    }


    public double getRightTrigger(){
        return filterJoystick(gamepad1.right_trigger);
    }
    public double getLeftTrigger(){
        return filterJoystick(gamepad1.left_trigger);
    }

    boolean lateA1 = false;
    public boolean getA1(){
        boolean out;
        out = gamepad1.a && !lateA1;
        lateA1 = gamepad1.a;
        return out;
    }

    boolean lateDPADUP1 = false;
    public boolean getDPADUP1(){
        boolean out;
        out = gamepad1.dpad_up && !lateDPADUP1;
        lateDPADUP1 = gamepad1.dpad_up;
        return out;
    }
    boolean lateDPADDOWN1 = false;
    public boolean getDPADDOWN1() {
        boolean out;
        out = gamepad1.dpad_down && !lateDPADDOWN1;
        lateDPADDOWN1 = gamepad1.dpad_down;
        return out;
    }



    public double filterJoystick(double input) {
        //implements both deadzone and scaled drive
        if(Math.abs(input) < JoystickConstants.DEADZONE) return 0;
        if(input > 0) {
            return JoystickConstants.minJoystick * Math.pow((JoystickConstants.maxJoystick / JoystickConstants.minJoystick), input);
        } else {
            input *= -1;
            return -JoystickConstants.minJoystick * Math.pow((JoystickConstants.maxJoystick / JoystickConstants.minJoystick), input);
        }
    }

    //----------------------second controller---------------------------------
    public double getSlidePower(){
        return filterJoystick(gamepad2.left_stick_y);
    } // motor

    public double getArmSpeed() { return filterJoystick(gamepad2.right_stick_y); } // motor, limit max speed

    public double getWristPosition() { return filterJoystick(gamepad2.right_trigger - gamepad2.left_trigger); } // servo

    public int openClaw() {
        boolean aButton = gamepad2.a;
        boolean bButton = gamepad2.b;
        if (aButton) {
            if (bButton) {
                return 0;
            }
            else {
                return 1;
            }
        } else {
            if (bButton) {
                return -1;
            }
            else {
                return 0;
            }
        }
    } // servo

//    public double toggleHang(){
//        return filterJoystick(gamepad2.right_trigger);
//    }

}
