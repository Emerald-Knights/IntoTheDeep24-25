package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Slides extends Subsystem{
    public static double p = 0.0022, i = 0, d = 0.0001, f = 0.08;
    public double joystickInput; // joystick input

    double UPPERLIMIT, LOWERLIMIT;
    double MAXSLIDESPEED;


    public SubsystemConstants.SlideStates currentState = SubsystemConstants.SlideStates.FREE;
    private double motorSpeed = 0;
    private double ff = 0;

    @Override
    public void init(boolean auton) {
        currentState = SubsystemConstants.SlideStates.FREE;
        UPPERLIMIT = 6000;
        LOWERLIMIT = 0;
        MAXSLIDESPEED = 0.8;
    }

    @Override
    public void update(boolean auton) {

        SubsystemConstants.MAX_FEEDFORWARD = f;

        if(currentState == SubsystemConstants.SlideStates.FREE || joystickInput > 0.05) {
            currentState = SubsystemConstants.SlideStates.FREE;
            setSlidesPower(joystickInput);
        }
//        else {
//            setSlidesLength(currentState.position);
//        }
    }

    @Override
    public void stop() {

    }

    @Override
    public void printToTelemetry(Telemetry telemetry) {
        telemetry.addData("slidePower", motorSpeed);
        telemetry.addData("currentState", currentState);
//        telemetry.addData("ff", ff);
        telemetry.addData("currentPosition", getSlidesPosition());
    }

    public void setSlidesLength(double targetPosition) {
        double error = targetPosition - getSlidesPosition();
        if (Math.abs(error) >= SubsystemConstants.slidesTolerance) {
            double speed = error;
            speed = (Math.abs(speed) > 0.8) ? 0.8 * (speed / Math.abs(speed)) : speed;
            setSlidesPower(speed);
        }
    }

    //ADD IT TO SPEED AFTER TUNING
    public void setSlidesPower(double input) {


        double ff = Math.pow(getSlidesPosition() / SubsystemConstants.MAX_CLAWSLIDE_HEIGHT, 6) * SubsystemConstants.MAX_FEEDFORWARD;
        double total = input + ff;
        double power = (Math.abs(total) > 0.8) ? 0.8 * (total / Math.abs(total)) : total;

        if((getSlidesPosition() < LOWERLIMIT && input < 0) || (getSlidesPosition() > UPPERLIMIT && input > 0)) {
            Robot.getInstance().arm1.setPower(0);
            Robot.getInstance().arm2.setPower(0);
            return;
        }

        Robot.getInstance().clawSlide.setPower(power * MAXSLIDESPEED);
        motorSpeed = power;
        this.ff = ff;
    }

    public double getSlidesPosition() {
        return Robot.getInstance().clawSlide.getCurrentPosition();
    }
}