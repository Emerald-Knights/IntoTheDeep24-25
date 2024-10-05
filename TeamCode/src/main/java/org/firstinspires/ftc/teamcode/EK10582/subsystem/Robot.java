package org.firstinspires.ftc.teamcode.EK10582.subsystem;
import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.Arrays;
import java.util.List;


public class Robot {

    static Robot robot = null;

    HardwareMap hardwareMap;
    EKLinear linearOpMode;

    //declare hardware here
    public DcMotorEx leftFront, leftBack, rightFront, rightBack, leftSlide, rightSlide;

    public Servo tServo1, tServo2;
    public Servo clawServo;

    //public Servo Wrist, Hand;
    public BHI260IMU imu;

    public WebcamName camera;
    public OpenCvWebcam webcam;

    //Declare subsystems here: Ex. mecanumDrive, collection, slides, sorting, etc.
    public MecanumDrive mecanumDrive = new MecanumDrive();
    public Slides slides = new Slides();
    public Claw claw = new Claw();

    public AprilTags aprilTags = new AprilTags();

    public List<Subsystem> subsystems = Arrays.asList(aprilTags);
    public List<Subsystem> telemetrySubsystems = Arrays.asList(aprilTags);



    //Creates an arraylist called actions that stores all the actions that are currently being done
//    private ArrayList<Action> actions = new ArrayList<Action>();

    public ElapsedTime cycleTimer = new ElapsedTime();

    //sets values to declared but not instantiated values
    public void init(HardwareMap hardwareMap, LinearOpMode linearOpMode) {
        this.hardwareMap = hardwareMap;
        this.linearOpMode = (EKLinear)linearOpMode;



        camera = hardwareMap.get(WebcamName.class, "Webcam 1");




        for(Subsystem subsystem : subsystems) {
            //initialize the subsystems
            subsystem.init(false);
        }


        cycleTimer.reset();
    }

    //checks if a robot object is created
    //it is unnecessary and can get complicated if there multiple robot objects
    public static Robot getInstance() {
        if(robot == null) robot = new Robot();
        return robot;
    }

    public void update() {
        //Update every single subsystem in the subsystems array initialized earlier
        for(Subsystem subsystem : subsystems) {
            subsystem.update(linearOpMode.isAuton);
            if(linearOpMode.isStopRequested()){
                return;
            }
        }

        //telemetry
        linearOpMode.allTelemetry.addData("Match Time", linearOpMode.matchTimer.milliseconds());
        linearOpMode.allTelemetry.addData("Cycle Time", cycleTimer.milliseconds());
        cycleTimer.reset();
        for(Subsystem subsystem : telemetrySubsystems) {
            linearOpMode.allTelemetry.addData("  --  ", (subsystem.getClass().getSimpleName() + "  --  "));
            subsystem.printToTelemetry(linearOpMode.allTelemetry);
        }
        linearOpMode.allTelemetry.update();
    }
}