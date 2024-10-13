package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants.clawStates;
import org.firstinspires.ftc.teamcode.EK10582.teleop.DriverStation;

public class Claw extends Subsystem{
    public clawStates currentState;

    public DriverStation driverstation = new DriverStation(gamepad1,gamepad2);

    public void init(boolean isAuton){
        currentState = clawStates.CLOSED;
        Robot.getInstance().clawServo.setPosition(clawStates.CLOSED.position);
    }

    public void update(boolean isAuton){
        if(Robot.getInstance().clawServo.getPosition() == clawStates.CLOSED.position) {
            currentState = clawStates.OPEN;
            switch (driverstation.openClaw()) {
                case 0:
                    break;
                case 1:
                    Robot.getInstance().clawServo.setPosition(clawStates.OPEN.position);
                    break;
                case -1:
                    Robot.getInstance().clawServo.setPosition(clawStates.CLOSED.position);
                    break;
            }
        }
         else{
             currentState = clawStates.CLOSED;
             switch (driverstation.openClaw()){
                 case 0:
                        break;
                 case 1:
                        Robot.getInstance().clawServo.setPosition(clawStates.OPEN.position);
                        break;
                 case -1:
                        Robot.getInstance().clawServo.setPosition(clawStates.CLOSED.position);
                        break;
                }
            }
    }

    public void stop(){

    }
    public void printToTelemetry(Telemetry telemetry){
        telemetry.addData("State", currentState);

    }
}
