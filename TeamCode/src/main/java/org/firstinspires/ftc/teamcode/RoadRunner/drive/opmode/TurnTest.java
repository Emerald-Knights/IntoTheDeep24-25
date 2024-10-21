package org.firstinspires.ftc.teamcode.RoadRunner.drive.opmode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
import org.firstinspires.ftc.teamcode.RoadRunner.drive.SampleMecanumDrive;

/*
 * This is a simple routine to test turning capabilities.
 */
@Config
@Autonomous(group = "drive")
public class TurnTest extends EKLinear {
    public static double ANGLE = 90; // deg

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);



        if (isStopRequested()) return;

        drive.turn(Math.toRadians(ANGLE));
    }
}
