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
        DcMotor sm1 = hardwareMap.get(DcMotor.class, "clawSlide");

//        DcMotor arm1 = hardwareMap.get(DcMotor.class, "arm1");
//        DcMotor arm2 = hardwareMap.get(DcMotor.class, "arm2");

        sm1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sm1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sm1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        arm1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        arm2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        while(opModeIsActive()) {

            sm1.setPower(gamepad2.left_stick_y * .8);

//            arm1.setPower((gamepad2.right_trigger - gamepad2.left_trigger) * .3);
//            arm2.setPower(-1*(gamepad2.left_trigger - gamepad2.right_trigger) * .3);

            telemetry.addData("motor speed: ", gamepad2.left_stick_y);
            telemetry.addData("motor position:", sm1.getCurrentPosition());
//            telemetry.addData("arm speed: ", gamepad2.right_trigger - gamepad2.left_trigger);
            telemetry.update();
        }
    }

}
