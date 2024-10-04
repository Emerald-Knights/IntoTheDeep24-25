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
    }

    public double toggleHang(){
        return filterJoystick(gamepad2.right_trigger);
    }

}
