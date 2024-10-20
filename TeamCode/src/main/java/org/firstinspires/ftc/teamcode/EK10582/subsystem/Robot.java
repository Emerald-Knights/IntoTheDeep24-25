package org.firstinspires.ftc.teamcode.EK10582.subsystem;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
import org.firstinspires.ftc.teamcode.EK10582.auton.action.Action;
import org.firstinspires.ftc.teamcode.EK10582.teleop.Drive;
import org.firstinspires.ftc.teamcode.EK10582.teleop.DriverStation;
import org.openftc.easyopencv.OpenCvWebcam;
import org.openftc.easyopencv.OpenCvCameraFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Robot {

    static Robot robot = null;

    HardwareMap hardwareMap;
    EKLinear linearOpMode;

    //declare hardware here

    public DcMotorEx leftFront, leftBack, rightFront, rightBack, hangSlide, clawSlide, arm1, arm2;
    public Servo wristServo;
    public Servo clawServo;

    public DistanceSensor autoGrabSensor;

    //public Servo Wrist, Hand;
    public BHI260IMU imu;

    public WebcamName camera;
    public OpenCvWebcam webcam;

    //Declare subsystems here: Ex. mecanumDrive, collection, slides, sorting, etc.
    public MecanumDrive mecanumDrive = new MecanumDrive();
    public Slides slides = new Slides();
    public Hanging hanging = new Hanging();
    public Elbow elbow = new Elbow();
    public Claw claw = new Claw();


    public List<Subsystem> subsystems = Arrays.asList(mecanumDrive, slides, elbow, claw);
    public List<Subsystem> telemetrySubsystems = Arrays.asList(mecanumDrive, slides, elbow, claw);


    //Creates an arraylist called actions that stores all the actions that are currently being done
    private ArrayList<Action> actions = new ArrayList<Action>();

    public ElapsedTime cycleTimer = new ElapsedTime();


    //sets values to declared but not instantiated values
    public void init(HardwareMap hardwareMap, LinearOpMode linearOpMode) {
        this.hardwareMap = hardwareMap;
        this.linearOpMode = (EKLinear)linearOpMode;

        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftBack = hardwareMap.get(DcMotorEx.class, "leftBack");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightBack = hardwareMap.get(DcMotorEx.class, "rightBack");

        arm1 = hardwareMap.get(DcMotorEx.class, "arm1");
        arm2 = hardwareMap.get(DcMotorEx.class, "arm2");

        arm1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        arm1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        arm2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        arm1.setDirection(DcMotorSimple.Direction.REVERSE);

        clawServo = hardwareMap.get(Servo.class, "clawServo");

        wristServo = hardwareMap.get(Servo.class, "wristServo");
        //tServo2 = hardwareMap.get(Servo.class, "testServo2");

        leftFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        arm1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);


        clawSlide = hardwareMap.get(DcMotorEx.class, "clawSlide");
        clawSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        clawSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //TODO: Autograb
//        autoGrabSensor = hardwareMap.get(DistanceSensor.class, "distanceSensor");

        //hangSlide = hardwareMap.get(DcMotorEx.class, "hangSlide");


        imu = hardwareMap.get(BHI260IMU.class, "imu");

        BHI260IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.RIGHT, RevHubOrientationOnRobot.UsbFacingDirection.UP));
        imu.initialize(parameters);
        imu.resetYaw();

        //TODO: Apriltags Camera
        camera = hardwareMap.get(WebcamName.class, "Webcam 1");

        //OpenCV Camera
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

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

    public void addAction(Action action) {
        action.start();
        actions.add(action);
    }

    public void update() {
        //Update every single subsystem in the subsystems array initialized earlier
        for(Subsystem subsystem : subsystems) {
            subsystem.update(linearOpMode.isAuton);
            if(linearOpMode.isStopRequested()){
                return;
            }
        }

        for(int i = 0; i < actions.size(); i++) {
            actions.get(i).update();

            //if an action is finished, end said action and remove it from the list of things to do
            if(actions.get(i).isComplete) {
                actions.get(i).end();
                actions.remove(i);
                i--;
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