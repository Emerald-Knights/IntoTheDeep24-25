package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.ImuOrientationOnRobot;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.EK10582.EKLinear;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//robot class to create a robot object that will be used
//to do auton and teleop stuff
public class Robot {

    //creates a Robot variable to be called later
    static Robot robot = null;

    HardwareMap hardwareMap;

    //declares a custom EK LinearOpMode named linearOpMode
    EKLinear linearOpMode;

    //declare hardware here
    public DcMotorEx leftFront, leftBack, rightFront, rightBack;

    public DcMotorEx slide1;
    public DcMotorEx slide2;
    public DcMotorEx intakeSpin;
    public DcMotorEx hangingMotor;

    public BHI260IMU imu;

    public Servo pixelHolder;
    public Servo dumper;
    public Servo drone;
    public Servo hangingServo;

    public WebcamName camera;

    public OpenCvWebcam webcam;


    //Declare subsystems here: Ex. mecanumDrive, collection, slides, sorting, etc.
    public MecanumDrive mecanumDrive = new MecanumDrive();


    //Add all subsystems to a list to be initiated and updated through
    //private List<Subsystem> subsystems = Arrays.asList(mecanumDrive, intake, aprilTags, slides, housing, openCV);
    public List<Subsystem> subsystems = Arrays.asList(mecanumDrive);

    //add all subsystems that need to go through telemetry
//    private List<Subsystem> telemetrySubsystems = Arrays.asList();
    public List<Subsystem> telemetrySubsystems = Arrays.asList(mecanumDrive);


    //Creates an arraylist called actions that stores all the actions that are currently being done
//    private ArrayList<Action> actions = new ArrayList<Action>();

    public ElapsedTime cycleTimer = new ElapsedTime();

    //sets values to declared but not instantiated values
    public void init(HardwareMap hardwareMap, LinearOpMode linearOpMode) {
        this.hardwareMap = hardwareMap;
        this.linearOpMode = (EKLinear)linearOpMode;

        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftBack = hardwareMap.get(DcMotorEx.class, "leftBack");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightBack = hardwareMap.get(DcMotorEx.class, "rightBack");

        leftFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        imu = hardwareMap.get(BHI260IMU.class, "imu");

        //.Parameters refers to a nested class within BNO055IMU
        //.Parameters class has more methods and uses specific to our use of imu
        BHI260IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.RIGHT, RevHubOrientationOnRobot.UsbFacingDirection.UP));

        //sets parameter obj to another nested class called AngleUnit to increase capabilities
        imu.initialize(parameters);
        imu.resetYaw();


        for(Subsystem subsystem : subsystems) {
            //initialize the subsystems
            subsystem.init(false); //change this
        }


        cycleTimer.reset();
    }

    //checks if a robot object is created
    //it is unnecessary and can get complicated if there multiple robot objects
    public static Robot getInstance() {
        if(robot == null) robot = new Robot();
        return robot;
    }
    //if you see Robot.getInstance() the code will check if there
    //is a robot instantiated anywhere. if there is, it will use that object
    // if not, it will create a new robot obj

    //Add an action to the list of things the robot is currently doing.

//    public void addAction(Action action) {
//        action.start();
//        actions.add(action);
//    }

    public void update() {
        //Update every single subsystem in the subsystems array initialized earlier
        for(Subsystem subsystem : subsystems) {
            subsystem.update(linearOpMode.isAuton);
            if(linearOpMode.isStopRequested()){
                return;
            }
        }

//        //Updates every single action in the list of actions that are currently being done
//        for(int i = 0; i < actions.size(); i++) {
//            actions.get(i).update();
//
//            //if an action is finished, end said action and remove it from the list of things to do
//            if(actions.get(i).isComplete) {
//                actions.get(i).end();
//                actions.remove(actions.get(i));
//                i--;
//            }
//        }

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