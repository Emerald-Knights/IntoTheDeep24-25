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
            robot.mecanumDrive.lx = -1 * driverStation.getLeftStickX();
            robot.mecanumDrive.ly = -1 * driverStation.getLeftStickY();
            robot.mecanumDrive.rx = driverStation.getRightStickX();

            //arm motors
            double armJoystickInput = driverStation.getArmSpeed();

            if (armJoystickInput > 0.02 || armJoystickInput < -0.02){
                robot.arm1.setPower(armJoystickInput * .8);
                robot.arm2.setPower(-1 * armJoystickInput * .8);
            }

            // slide motor

            double slideJoystickInput = driverStation.getSlidePower();

            if (slideJoystickInput > 0.02 || slideJoystickInput < -0.02){
                robot.clawSlide.setPower(slideJoystickInput * .8);
            }

//
//            //claw
//            if(robot.clawSlide.currentState == SubsystemConstants.clawStates.CLOSED)
//                robot.clawSlide.currentState = SubsystemConstants.clawStates.OPEN;
//            else
//                robot.clawSlide.currentState = SubsystemConstants.clawStates.CLOSED;
/*
            //hanging
            robot.hanging.triggerInput = driverStation.toggleHang();

            //apriltags
            if (driverStation.getDPADUP1()) {
                Robot.getInstance().aprilTags.decimation++;
            }
            if (driverStation.getDPADDOWN1()){
                Robot.getInstance().aprilTags.decimation--;
            }*/


                robot.update();
        }

    }
}


