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

        rightEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "rightBack"));
        leftEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "leftBack"));
        frontEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "rightFront"));
        leftEncoder.setDirection(Encoder.Direction.REVERSE);
        rightEncoder.setDirection(Encoder.Direction.REVERSE);
        waitForStart();

        while(opModeIsActive()) {
            telemetry.addData("backOdo", frontEncoder.getCurrentPosition());
            telemetry.addData("rightOdo", rightEncoder.getCurrentPosition());
            telemetry.addData("leftOdo", leftEncoder.getCurrentPosition());
            telemetry.update();
        }
    }
}