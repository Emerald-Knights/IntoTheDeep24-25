package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;

// TODO: set up PID, change subsystem constants for slide states
public class Slides extends Subsystem {

    public static double p = 0.0022, i = 0, d = 0.0001, f = 0.08;
    public static double adjustableTarget = 0;

    public double joystickInput; // joystick input
//    PIDController slidesPID = new PIDController(0.002, 0, 0.0001);

    public SubsystemConstants.SlideStates currentState = SubsystemConstants.SlideStates.FREE;
    private double motorSpeed = 0;
    private double ff = 0;

    @Override
    public void init(boolean auton) {
        currentState = SubsystemConstants.SlideStates.FREE;

    }

    @Override
    public void update(boolean auton) {
//        slidesPID.setPID(p, i, d);
        SubsystemConstants.MAX_FEEDFORWARD = f;

        if(currentState == SubsystemConstants.SlideStates.FREE || joystickInput > 0.05) {
            currentState = SubsystemConstants.SlideStates.FREE;
            setSlidesPower(joystickInput);
        } else if(currentState == SubsystemConstants.SlideStates.ADJUSTABLE) {
            setSlidesLength(adjustableTarget);
        } else {
            setSlidesLength(currentState.position);
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public void printToTelemetry(Telemetry telemetry) {
        telemetry.addData("slidePower", motorSpeed);
        telemetry.addData("currentState", currentState);
        telemetry.addData("finalPosition", adjustableTarget);
        telemetry.addData("ff", ff);
        telemetry.addData("currentPosition", getSlidesPosition());
    }

    public void setSlidesLength(double targetPosition) {
        double direction = (targetPosition - getSlidesPosition()) / Math.abs(targetPosition - getSlidesPosition());
        double error = targetPosition - getSlidesPosition();
        if (Math.abs(error) >= SubsystemConstants.slidesTolerance) {
//            double speed = slidesPID.update(error);
            double speed = error;
            speed = (Math.abs(speed) > 0.8) ? 0.8 * (speed / Math.abs(speed)) : speed;
            setSlidesPower(speed);
        }
    }

    //ADD IT TO SPEED AFTER TUNING
    public void setSlidesPower(double input) {
        double ff = Math.pow(getSlidesPosition() / SubsystemConstants.MAX_SLIDE_HEIGHT, 6) * SubsystemConstants.MAX_FEEDFORWARD;
        double total = input + ff;
        double power = (Math.abs(total) > 0.8) ? 0.8 * (total / Math.abs(total)) : total;
        Robot.getInstance().leftSlide.setPower(power);
        Robot.getInstance().rightSlide.setPower(power);
        motorSpeed = power;
        this.ff = ff;
    }

    public double getSlidesPosition() {
        return Robot.getInstance().leftSlide.getCurrentPosition();
    }
}
