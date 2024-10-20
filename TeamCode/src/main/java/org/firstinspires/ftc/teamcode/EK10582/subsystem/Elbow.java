package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants.ArmStates;

public class Elbow extends Subsystem {
    // this is to control the elbow/arm motor part of the robot
    // TODO: implement arm states (maybe?)
    // currently under free-control

    public ArmStates currentState;
    public double motorSpeed;
    public double joystickInput;
    double SPEEDMULTIPLIER;
    double EXTENSIONLIMIT;

    private double ff = 0;

    @Override
    public void init(boolean isAuton) {
        motorSpeed = 0;
        SPEEDMULTIPLIER = 0.35;
        EXTENSIONLIMIT = -550;
        currentState = ArmStates.FREE;
    }

    @Override
    public void update(boolean isAuton) {
//        if (currentState == ArmStates.GROUND && triggerInput > 0) {
//            currentState = ArmStates.UP;
//            setSlideLength(); //set it to the length of the low hang
//        }
        if(currentState == SubsystemConstants.ArmStates.FREE || joystickInput > 0.05) {
            currentState = SubsystemConstants.ArmStates.FREE;
            setArmSpeed(joystickInput);
        } else {
            setArmPosition(currentState.position);
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public void printToTelemetry(Telemetry telemetry) {
        telemetry.addData("Arm Power", motorSpeed);
        telemetry.addData("arm1 position", Robot.getInstance().arm1.getCurrentPosition());
        telemetry.addData("arm2 position", Robot.getInstance().arm2.getCurrentPosition());
    }

    public void setArmPosition(double targetPosition) {
        double direction = (targetPosition - getArmSlidePosition()) / Math.abs(targetPosition - getArmSlidePosition());
        double error = targetPosition - getArmSlidePosition();
        if (Math.abs(error) >= SubsystemConstants.slidesTolerance) {
            double speed = error;
            speed = (Math.abs(speed) > 0.8) ? 0.8 * (speed / Math.abs(speed)) : speed;
            setArmSpeed(speed);
        }
    }

    public void setArmSpeed(double input) {
        //if attempting to go back past the extension limit, just don't :tm:
        if(getArmSlidePosition() < EXTENSIONLIMIT && input < 0) {
            Robot.getInstance().arm1.setPower(0);
            Robot.getInstance().arm2.setPower(0);
            return;
        }
        double ff = Math.pow(getArmSlidePosition() / SubsystemConstants.MAX_ELBOW_LIMIT, 6) * SubsystemConstants.MAX_FEEDFORWARD;
        double total = input + ff;
        double power = (Math.abs(total) > 0.8) ? 0.8 * (total / Math.abs(total)) : total;
        Robot.getInstance().arm1.setPower(SPEEDMULTIPLIER * power);
        Robot.getInstance().arm2.setPower(SPEEDMULTIPLIER * power);
        motorSpeed = power;
        this.ff = ff;
    }

    public double getArmSlidePosition() {
        return Robot.getInstance().arm2.getCurrentPosition();
    }
}
