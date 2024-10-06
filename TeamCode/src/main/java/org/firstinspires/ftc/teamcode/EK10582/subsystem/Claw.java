package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants.clawStates;

public class Claw extends Subsystem{
    public clawStates currentState;
    boolean autoGrab;

    public void init(boolean isAuton){
        currentState = clawStates.CLOSED;
        Robot.getInstance().clawServo.setPosition(clawStates.CLOSED.position);
        autoGrab = false;
    }

    public void update(boolean isAuton){
        Robot.getInstance().clawServo.setPosition(currentState.position);
        if (autoGrab){
            if (Robot.getInstance().autoGrabSensor.getDistance(DistanceUnit.INCH) < 2){
                currentState = clawStates.CLOSED;
            }
        }
    }

    public void stop(){

    }
    public void printToTelemetry(Telemetry telemetry){
        telemetry.addData("State", currentState);
        telemetry.addData("Distance: ", Robot.getInstance().autoGrabSensor.getDistance(DistanceUnit.INCH));

    }
}
