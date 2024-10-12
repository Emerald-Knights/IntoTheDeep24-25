package org.firstinspires.ftc.teamcode.EK10582.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;

@TeleOp(name="Servo Tester (intake arm port)")
//@Disabled
public class ServoTester extends EKLinear {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        double targetPos1 = 0.5;
        double targetPos2 = 0.5;

        while(opModeIsActive()) {


            targetPos1 += gamepad1.right_stick_y * 0.001;
            targetPos2 += gamepad1.left_stick_y * 0.001;



            if(targetPos1 >= 1){
                targetPos1 = 1;
            }
            if(targetPos1 <= 0){
                targetPos1 = 0;
            }

            if(targetPos2 >= 1){
                targetPos2= 1;
            }
            if(targetPos2 <= 0){
                targetPos2 = 0;
            }

            Robot.getInstance().tServo1.setPosition(targetPos1);
            Robot.getInstance().tServo2.setPosition(targetPos2);

            telemetry.addData("Servo Position 1: ", targetPos1);
            telemetry.addData("Servo Position 2: ", targetPos2);
            telemetry.update();
            //robot.update();
        }
    }
}
