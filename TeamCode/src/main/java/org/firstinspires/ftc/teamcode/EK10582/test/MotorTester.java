package org.firstinspires.ftc.teamcode.EK10582.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;

@TeleOp(name="Motor Tester")
public class MotorTester extends EKLinear {
    @Override
    public void runOpMode() {
        waitForStart();
        DcMotor testMotor = hardwareMap.get(DcMotor.class, "Test Motor");
        while(opModeIsActive()) {
            testMotor.setPower(gamepad1.right_trigger*.5);
            testMotor.setPower(-gamepad1.left_trigger*.5);
            telemetry.addData("motor speed: ", gamepad1.right_trigger);
            telemetry.update();
        }
    }
}
