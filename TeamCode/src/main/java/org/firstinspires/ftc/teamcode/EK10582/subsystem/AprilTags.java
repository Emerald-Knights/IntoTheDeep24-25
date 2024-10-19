package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

public class AprilTags extends Subsystem {

    boolean aprilTagsEnabled;

    private AprilTagProcessor aprilTag;
    public float decimation;

    private VisionPortal visionPortal;
    List<AprilTagDetection> currentDetections;



    /**
     * Initialize the AprilTag processor.
     */
    @Override
    public void init (boolean auton) {

        aprilTagsEnabled = true;

        // Create the AprilTag processor.
        aprilTag = new AprilTagProcessor.Builder()

                // The following default settings are available to un-comment and edit as needed.
                //.setDrawAxes(false)
                //.setDrawCubeProjection(false)
                //.setDrawTagOutline(true)
                //.setTagFamily(AprilTagProcessor.TagFamily.TAG_36h11)
                //.setTagLibrary(AprilTagGameDatabase.getCenterStageTagLibrary())
                //.setOutputUnits(DistanceUnit.INCH, AngleUnit.DEGREES)

                .build();

        aprilTag.setDecimation(3);

        // Create the vision portal by using a builder.
        VisionPortal.Builder builder = new VisionPortal.Builder();

        // Set the camera (webcam vs. built-in RC phone camera).
        builder.setCamera(Robot.getInstance().camera);

        // Set and enable the processor.
        builder.addProcessor(aprilTag);

        // Build the Vision Portal, using the above settings.
        visionPortal = builder.build();

        // Disable or re-enable the aprilTag processor at any time.
        visionPortal.setProcessorEnabled(aprilTag, aprilTagsEnabled);

    }   // end method initAprilTag()

    public void update (boolean auton){
        if(decimation < 1){
            decimation = 1;
        }
        else if (decimation > 5){
            decimation = 5;
        }
        aprilTag.setDecimation(decimation);
        currentDetections = aprilTag.getDetections();
    }

    @Override
    public void stop(){

    }
    @Override
    public void printToTelemetry(Telemetry telemetry) {


        telemetry.addData("# AprilTags Detected", currentDetections.size());

        // Step through the list of detections and display info for each one.
        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null) {
                telemetry.addLine(String.format("\n==== (ID %d) %s", detection.id, detection.metadata.name));
                telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", filterXDist(detection.ftcPose.x), filterYDist(detection.ftcPose.y), detection.ftcPose.z));
                telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw));
                telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", detection.ftcPose.range, detection.ftcPose.bearing, detection.ftcPose.elevation));
                telemetry.addData("apriltags size: ", detection.metadata.tagsize);
            } else {
                telemetry.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
                telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
            }
        }   // end for() loop

        // Add "key" information to telemetry
        telemetry.addLine("\nkey:\nXYZ = X (Right), Y (Forward), Z (Up) dist.");
        telemetry.addLine("PRY = Pitch, Roll & Yaw (XYZ Rotation)");
        telemetry.addLine("RBE = Range, Bearing & Elevation");
        telemetry.addData("Decimation: ", decimation);

    }   // end method telemetryAprilTag()

    public double filterYDist(double dist){
        return 0.90689 * dist + 0.751176;
    }

    public double filterXDist(double dist){
        return 1.00298575 * dist + 1.074626865;
    }

}   // end class