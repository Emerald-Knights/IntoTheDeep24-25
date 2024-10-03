package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants.clawStates;

public class Claw extends Subsystem{
    public clawStates currentState = clawStates.CLOSED;

    public void init(boolean isAuton){
        currentState = clawStates.CLOSED;
        Robot.getInstance().clawServo.setPosition(clawStates.CLOSED.position);
    }

    public void update(boolean isAuton){
        Robot.getInstance().clawServo.setPosition(currentState.position);

    }

    public void stop(){

    }
    public void printToTelemetry(Telemetry telemetry){
        telemetry.addData("State", currentState);

    }
}
