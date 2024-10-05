package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants.HangingStates;

public class Hanging extends Subsystem{
    public double hangingPower = 0;
    public HangingStates currentState = HangingStates.GROUND;
    public double triggerInput;
    private double motorSpeed = 0;
    private double ff = 0;



    @Override
    public void init(boolean auton) {
        currentState = HangingStates.GROUND;
    }

    @Override
    public void update(boolean auton) {
        if(currentState == SubsystemConstants.HangingStates.GROUND && triggerInput > 0){
            currentState = SubsystemConstants.HangingStates.LOW_HANG;
            sethSlideLength(SubsystemConstants.lowHang); //set it to the length of the low hang
        }
        if(currentState == SubsystemConstants.HangingStates.LOW_HANG && triggerInput > 0){
            currentState = SubsystemConstants.HangingStates.HIGH_HANG;
            sethSlideLength(SubsystemConstants.highHang); //set it to the length of the high hang, maybe use an array?
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public void printToTelemetry(Telemetry telemetry) {
        telemetry.addData("Hanging Power", hangingPower);
    }
    public void sethSlideLength(double targetPosition) {
        double direction = (targetPosition - getHangSlidePosition()) / Math.abs(targetPosition - getHangSlidePosition());
        double error = targetPosition - getHangSlidePosition();
        if (Math.abs(error) >= SubsystemConstants.slidesTolerance) {
//            double speed = slidesPID.update(error);
            double speed = error;
            speed = (Math.abs(speed) > 0.8) ? 0.8 * (speed / Math.abs(speed)) : speed;
            sethSlidesPower(speed);
        }
    }
    public void sethSlidesPower(double input) {
        double ff = Math.pow(getHangSlidePosition() / SubsystemConstants.MAX_SLIDE_HEIGHT, 6) * SubsystemConstants.MAX_FEEDFORWARD;
        double total = input + ff;
        double power = (Math.abs(total) > 0.8) ? 0.8 * (total / Math.abs(total)) : total;
        Robot.getInstance().clawSlide.setPower(power);
        motorSpeed = power;
        this.ff = ff;
    }

    public double getHangSlidePosition() {
        return Robot.getInstance().hangSlide.getCurrentPosition();
    }

}