//package org.firstinspires.ftc.teamcode.EK10582.subsystem;
//
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants.ArmStates;
//
//public class Elbow extends Subsystem {
//    // this is to control the elbow/arm motor part of the robot
//    // TODO: implement arm states (maybe?)
//    // currently under free-control
//
//    public ArmStates currentState;
//    public double motorSpeed = 0;
//    public double triggerInput;
//
//    @Override
//    public void init(boolean isAuton) {
//        currentState = ArmStates.GROUND;
////        Robot.getInstance().armSlide1.setTargetPosition(ArmStates.GROUND.position);
//    }
//
//    @Override
//    public void update(boolean isAuton) {
//        if (currentState == ArmStates.GROUND && triggerInput > 0) {
//            currentState = ArmStates.UP;
//            setSlideLength(); //set it to the length of the low hang
//        }
//    }
//
//    @Override
//    public void stop() {
//
//    }
//
//    @Override
//    public void printToTelemetry(Telemetry telemetry) {
//        telemetry.addData("Hanging Power", hangingPower);
//    }
//
//    public void setSlideLength(double targetPosition) {
//        double direction = (targetPosition - getHangSlidePosition()) / Math.abs(targetPosition - getHangSlidePosition());
//        double error = targetPosition - getHangSlidePosition();
//        if (Math.abs(error) >= SubsystemConstants.slidesTolerance) {
////            double speed = slidesPID.update(error);
//            double speed = error;
//            speed = (Math.abs(speed) > 0.8) ? 0.8 * (speed / Math.abs(speed)) : speed;
//            setMotorSpeed(speed);
//        }
//    }
//
//    public void setMotorSpeed(double input) {
//        double ff = Math.pow(getHangSlidePosition() / SubsystemConstants.MAX_SLIDE_HEIGHT, 6) * SubsystemConstants.MAX_FEEDFORWARD;
//        double total = input + ff;
//        double power = (Math.abs(total) > 0.8) ? 0.8 * (total / Math.abs(total)) : total;
//        Robot.getInstance().clawSlide.setPower(power);
//        motorSpeed = power;
//        this.ff = ff;
//    }
//
//    public double getHangSlidePosition() {
//        return Robot.getInstance().hangSlide.getCurrentPosition();
//    }
//}
