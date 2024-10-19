package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class OpenCV extends Subsystem{

    @Override
    public void init(boolean auton){
        Robot.getInstance().webcam.setPipeline(new CameraPipeline());
        Robot.getInstance().webcam.setMillisecondsPermissionTimeout(2500);

        Robot.getInstance().webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                Robot.getInstance().webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }
            @Override
            public void onError(int errorCode) {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });
    }


    @Override
    public void update(boolean auton){

    }

    @Override
    public void stop(){
        Robot.getInstance().webcam.stopStreaming();
        Robot.getInstance().webcam.closeCameraDevice();
    }

    @Override
    public void printToTelemetry(Telemetry telemetry){

    }
}