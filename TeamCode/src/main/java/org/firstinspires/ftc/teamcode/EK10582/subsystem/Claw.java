package org.firstinspires.ftc.teamcode.EK10582.subsystem;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants.clawStates;


public class Claw extends Subsystem{
    clawStates currentState;
    public boolean moveClaw;

    public void init(boolean isAuton){
        currentState = clawStates.CLOSED;
        Robot.getInstance().clawServo.setPosition(clawStates.CLOSED.position);
    }

    public void update(boolean isAuton){
        if(moveClaw){
            if(currentState == clawStates.CLOSED){
                currentState = clawStates.OPEN;
            } else{
                currentState = clawStates.CLOSED;
            }
        }
        Robot.getInstance().clawServo.setPosition(currentState.position);
    }

    public void stop(){
    }
    public void printToTelemetry(Telemetry telemetry){
        telemetry.addData("State", currentState);
        telemetry.addData("Servo Position", Robot.getInstance().clawServo.getPosition());

    }
}
