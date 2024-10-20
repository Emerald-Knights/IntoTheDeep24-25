package org.firstinspires.ftc.teamcode.EK10582.teleop;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
//import org.firstinspires.ftc.teamcode.EK10582.subsystem.Claw;

import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants;

@TeleOp(name="New Drive")
public class Drive extends EKLinear {

    @Override
    public void runOpMode() {
        waitForStart();

        while (opModeIsActive()) {
            //drive
            robot.mecanumDrive.lx = -driverStation.getLeftStickX();
            robot.mecanumDrive.ly = -driverStation.getLeftStickY();
            robot.mecanumDrive.rx = driverStation.getRightStickX();

            //slowmode
            if (driverStation.getA1()) {
                if (robot.mecanumDrive.slowMode == 1)
                    robot.mecanumDrive.slowMode = 0.5;
                else {
                    robot.mecanumDrive.slowMode = 1;
                }
            }


            //arm motors: gamepad2 right stick y

//            if (armJoystickInput > 0.02 || armJoystickInput < -0.02) {
//                //multiplier to make it slower
//                robot.arm1.setPower(armJoystickInput * .2);
//                robot.arm2.setPower(-armJoystickInput * .2);
//            } else {
//                robot.arm1.setPower(0);
//                robot.arm2.setPower(0);
//            }

            //elbow
            robot.elbow.joystickInput = driverStation.getRY2();
            //slides
            robot.slides.joystickInput = driverStation.getLY2();

            //claw
            robot.claw.moveClaw = driverStation.getA2();
            robot.claw.moveWrist = driverStation.getB2();

            robot.update();
        }
    }
}


