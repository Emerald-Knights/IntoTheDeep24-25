package org.firstinspires.ftc.teamcode.EK10582.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;

@TeleOp(name="Slides Tester")
public class SlidesTester extends EKLinear {
    @Override
    public void runOpMode() {
        waitForStart();
        DcMotor sm1 = hardwareMap.get(DcMotor.class, "sm1");
        DcMotor sm2 = hardwareMap.get(DcMotor.class, "sm2");
        while(opModeIsActive()) {
            sm1.setPower(gamepad1.right_trigger*.5);
            sm2.setPower(gamepad1.right_trigger*.5);
            sm1.setPower(-gamepad1.left_trigger*.5);
            sm2.setPower(-gamepad1.left_trigger*.5);
            telemetry.addData("motor speed: ", gamepad1.right_trigger);
            telemetry.update();
        }
    }

}
