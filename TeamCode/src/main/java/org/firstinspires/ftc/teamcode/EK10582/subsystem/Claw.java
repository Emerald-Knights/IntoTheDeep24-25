package org.firstinspires.ftc.teamcode.EK10582.subsystem;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants.clawStates;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants.wristStates;

public class Claw extends Subsystem{
    clawStates currentState;
    wristStates currentWristState;
    public boolean moveClaw;
    public boolean moveWrist;

    public void init(boolean isAuton){
        currentState = clawStates.CLOSED;
        currentWristState = wristStates.DOWN;
        Robot.getInstance().clawServo.setPosition(clawStates.CLOSED.position);
        Robot.getInstance().clawServo.setPosition(wristStates.DOWN.position);
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