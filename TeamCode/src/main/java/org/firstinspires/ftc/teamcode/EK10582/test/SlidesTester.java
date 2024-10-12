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

        DcMotor arm1 = hardwareMap.get(DcMotor.class, "arm1");
        DcMotor arm2 = hardwareMap.get(DcMotor.class, "arm2");

        arm1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        arm1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm1.setTargetPosition(0);
        arm2.setTargetPosition(0);
        arm1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        sm1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        double increment = 5;
        float targetPos = 0;

        while(opModeIsActive()) {

            sm1.setPower(gamepad2.left_stick_y * .8);
//
//            arm1.setPower((gamepad2.right_trigger - gamepad2.left_trigger) * .8);
//            arm2.setPower((-gamepad2.left_trigger + gamepad2.right_trigger) * .8);


            if(gamepad2.left_trigger > 0.1 || gamepad2.right_trigger > 0.1){
                arm1.setPower(0.8);
                arm2.setPower(0.8);
                targetPos += ((gamepad2.right_trigger-gamepad2.left_trigger)*increment);
                arm1.setTargetPosition(Math.round(targetPos));
                arm2.setTargetPosition(Math.round(targetPos));
            }
            else{
                arm1.setPower(0);
                arm2.setPower(0);
            }

//            telemetry.addData("motor speed: ", gamepad2.left_stick_y);
            telemetry.addData("arm speed: ", gamepad2.right_trigger - gamepad2.left_trigger);
            telemetry.addData("Target Position: ", targetPos);
            telemetry.addData("Arm1 pos: ", arm1.getCurrentPosition());
            telemetry.addData("Arm2 pos: ", arm2.getCurrentPosition());
            telemetry.update();
        }
    }

}
