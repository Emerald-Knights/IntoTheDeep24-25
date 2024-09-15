package org.firstinspires.ftc.teamcode.EK10582.subsystem;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;

public class MecanumDrive extends Subsystem{
    public double ly,lx,rx;
    public double speed = SubsystemConstants.SPEED;
    @Override
    public void init(boolean auton){
    }

    @Override

    public void update(boolean auton){
        double lf = ly + lx + rx;
        double rf = ly - lx - rx;
        double lb = ly - lx + rx;
        double rb = ly + lx - rx;

        Robot.getInstance().leftFront.setPower(lf*speed);
        Robot.getInstance().rightFront.setPower(rf*speed);
        Robot.getInstance().leftBack.setPower(lb*speed);
        Robot.getInstance().rightBack.setPower(rb*speed);

    }
    @Override
    public void stop(){}
    @Override
    public void printToTelemetry(Telemetry telemetry){
        telemetry.addData("ly",ly);
        telemetry.addData("lx",lx);
        telemetry.addData("rx", rx);

    }

}
