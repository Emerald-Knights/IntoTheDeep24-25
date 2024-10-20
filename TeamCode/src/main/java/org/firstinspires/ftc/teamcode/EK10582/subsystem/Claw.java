package org.firstinspires.ftc.teamcode.EK10582.subsystem;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants.clawStates;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants.wristStates;

public class Claw extends Subsystem{
    clawStates currentState;
    wristStates currentWristState;
    public boolean moveClaw;
    public boolean moveWrist;

    boolean autoGrab;

    public void init(boolean isAuton){
        currentState = clawStates.CLOSED;
        currentWristState = wristStates.FOLDED;
    }

    public void update(boolean isAuton){
        if(moveClaw){
            if(currentState == clawStates.CLOSED){
                currentState = clawStates.OPEN;
            } else{
                currentState = clawStates.CLOSED;
            }
        }
        if(moveWrist){
            if(currentWristState == wristStates.DOWN){
                currentWristState = wristStates.UP;
            } else{
                currentWristState = wristStates.DOWN;
            }
        }

        //TODO: autograb
//        if (autoGrab){
//            if (Robot.getInstance().autoGrabSensor.getDistance(DistanceUnit.INCH) < 2){
//                currentState = clawStates.CLOSED;
//            }
//        }
        Robot.getInstance().wristServo.setPosition(currentWristState.position);
        Robot.getInstance().clawServo.setPosition(currentState.position);
    }

    public void stop(){
    }

    public void printToTelemetry(Telemetry telemetry){
        telemetry.addData("Claw State", currentState);
        telemetry.addData("Wrist State", currentWristState);
        telemetry.addData("Servo Position", Robot.getInstance().clawServo.getPosition());
        telemetry.addData("Wrist Position", Robot.getInstance().wristServo.getPosition());

    }
}