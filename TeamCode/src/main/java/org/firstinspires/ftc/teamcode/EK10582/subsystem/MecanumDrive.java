package org.firstinspires.ftc.teamcode.EK10582.subsystem;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;

public class MecanumDrive extends Subsystem{
    public double ly,lx,rx;
    public double speed = SubsystemConstants.SPEED;
    public double ratio;
    public double slowMode;

    @Override
    public void init(boolean auton){
        slowMode = 0.5;
    }

    @Override

    public void update(boolean auton){
        double lf = ly + lx + rx;
        double rf = ly - lx - rx;
        double lb = ly - lx + rx;
        double rb = ly + lx - rx;


        double max = Math.max(Math.max(Math.abs(lb), Math.abs(lf)), Math.max(Math.abs(rb), Math.abs(rf)));
        double magnitude = Math.sqrt((lx * lx) + (ly * ly) + (rx * rx));
        if (max == 0) {
            ratio = 0;
        } else {
            ratio = magnitude / max * speed;
        }

        Robot.getInstance().leftFront.setPower(lf*ratio*speed);
        Robot.getInstance().rightFront.setPower(rf*ratio*speed);
        Robot.getInstance().leftBack.setPower(lb*ratio*speed);
        Robot.getInstance().rightBack.setPower(rb*ratio*speed);

    }
    @Override
    public void stop(){}

    @Override
    public void printToTelemetry(Telemetry telemetry){
        telemetry.addData("ratio:", ratio);
        telemetry.addData("ly",ly);
        telemetry.addData("lx",lx);
        telemetry.addData("rx", rx);
    }

}