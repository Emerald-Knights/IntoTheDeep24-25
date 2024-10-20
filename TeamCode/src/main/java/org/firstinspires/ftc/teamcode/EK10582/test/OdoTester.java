package org.firstinspires.ftc.teamcode.EK10582.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
import org.firstinspires.ftc.teamcode.RoadRunner.util.Encoder;

@TeleOp(name="Odo Test")
public class OdoTester extends EKLinear {

    Encoder leftEncoder;
    Encoder rightEncoder;
    Encoder frontEncoder;
    @Override
    public void runOpMode() {

        leftEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "slide2"));
        rightEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "hanging"));
        frontEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "intakeSpin"));
        frontEncoder.setDirection(Encoder.Direction.REVERSE);
        waitForStart();

        while(opModeIsActive()) {
            //back is intake spin
            //left is slide 2
            //right is hanging
            telemetry.addData("backOdo", frontEncoder.getCurrentPosition());
            telemetry.addData("rightOdo", rightEncoder.getCurrentPosition());
            telemetry.addData("leftOdo", leftEncoder.getCurrentPosition());
            telemetry.update();
        }
    }
}