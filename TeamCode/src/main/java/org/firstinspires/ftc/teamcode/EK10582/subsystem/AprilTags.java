package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagGameDatabase;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

public class AprilTags {

    private AprilTagProcessor aprilTag;
    private VisionPortal visionPortal;

    public int targetAprilTag = SubsystemConstants.targetAprilTag;
    //TODO: make this changable by buttons or sm so you can change what april tag you're looking for
    // TODO: set up roadrunner etc for relocalize method

    public boolean seeTag = false;
    public double tagX;
    public double tagDistance;

    public double yaw;

    private boolean runTelemetry;

    // Decimation: 1 means low rate high range, 3 means low range high rate
    // Note: Decimation can be changed on-the-fly to adapt during a match.
    private int decimation;
    public boolean aprilTagsEnabled = true;

    //makes a list called currentDetections
    private List<AprilTagDetection> currentDetections;


    //boolean auton will be set to true or false depending if in auton
    //if in auton, then certain things will happen due to certain if statements specific to auton
    public void init(boolean auton) {
        if(!auton){
            return;
            //upon initialization, don't run init to save viewport for opencv
        }

        decimation = SubsystemConstants.decimation;
        // Create the AprilTag processor.
        aprilTag = new AprilTagProcessor.Builder()
//                .setLensIntrinsics( 1312.9, 1312.9, 810.624, 453.727)

                // The following default settings are available to un-comment and edit as needed.
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setDrawTagOutline(true)
                .setTagFamily(AprilTagProcessor.TagFamily.TAG_36h11)
                .setTagLibrary(AprilTagGameDatabase.getCenterStageTagLibrary())
                .setOutputUnits(DistanceUnit.INCH, AngleUnit.DEGREES)

                .build();


        aprilTag.setDecimation(decimation);

        // Create the vision portal by using a builder.
        VisionPortal.Builder builder = new VisionPortal.Builder();

        // Set the camera
        builder.setCamera(Robot.getInstance().camera);

        // Enable the RC preview (LiveView).  Set "false" to omit camera monitoring.
        builder.enableLiveView(true);

        // Set and enable the processor.
        builder.addProcessor(aprilTag);

        // Build the Vision Portal, using the above settings.
        visionPortal = builder.build();

        // Disable or re-enable the aprilTag processor at any time.
        visionPortal.setProcessorEnabled(aprilTag, aprilTagsEnabled);

    }   // end method initAprilTag()


    public void update(boolean auton){

        if(!auton){
            runTelemetry = false;
            return;
        }
        runTelemetry = true;
        currentDetections = aprilTag.getDetections();
        // Step through the list of detections and display info for each one.
        for (AprilTagDetection detection : currentDetections) {
            if(detection.id == targetAprilTag){
                seeTag = true;
                tagX= (double)Math.round(detection.ftcPose.x * 100)/100;
                tagDistance = ((double)Math.round(detection.ftcPose.y * 100)/100 ) / 1.102 - 0.4223;
                yaw = (double)Math.round(detection.ftcPose.yaw * 100)/100;

            }
            //amount of detections in detections array
            if(currentDetections.isEmpty()){
                seeTag = false;
            }
        }
        if(!seeTag){
            tagX = -1;
            tagDistance = -1;
            //if you don't see the stack return a negative number
        }
    }
    public void stop() {
    }


    public void printToTelemetry(Telemetry telemetry) {
        if(!runTelemetry){
            return;
        }
        telemetry.addData("# AprilTags Detected", currentDetections.size());
        telemetry.addLine("Apriltag " + targetAprilTag + " detected: " + seeTag);
        telemetry.addData("Apriltag " + targetAprilTag + "'s x value:", tagX);
        telemetry.addData("Apriltag " + targetAprilTag + "'s distance: ", tagDistance);
        telemetry.addData("Apriltag " + targetAprilTag + "'s yaw angle: ", yaw);
    }


//    public Pose2d relocalize() {
//        int targetX = 61;
//
//
//        double targetY = 42;
//        switch (targetAprilTag){
//            case 2: targetY = 36; break;
//            case 3: targetY = 30; break;
//            case 4: targetY = -30; break;
//            case 5: targetY = -36; break;
//            case 6: targetY = -42; break;
//        }
//
//        Pose2d pose = new Pose2d(targetX - tagDistance - 8, targetY + tagX, Math.toRadians(180));
//        if(!seeTag) pose = Robot.getInstance().roadRunner.getPoseEstimate();
//        return pose;
////        Robot.getInstance().roadRunner.setPoseEstimate(pose);
//    }
}
