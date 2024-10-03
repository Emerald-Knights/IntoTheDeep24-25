package org.firstinspires.ftc.teamcode.EK10582.teleop;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Claw;

import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.Robot;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants;

@TeleOp(name="New Drive")
public class Drive extends EKLinear {

    @Override
    public void runOpMode(){
        waitForStart();

        while(opModeIsActive()) {

            //drive
            robot.mecanumDrive.lx = driverStation.getLeftStickX();
            robot.mecanumDrive.ly = driverStation.getLeftStickY();
            robot.mecanumDrive.rx = driverStation.getRightStickX();




            if(robot.claw.currentState == SubsystemConstants.clawStates.CLOSED)
                robot.claw.currentState = SubsystemConstants.clawStates.OPEN;
            else
                robot.claw.currentState = SubsystemConstants.clawStates.CLOSED;

            robot.update();
            }

        }
    }