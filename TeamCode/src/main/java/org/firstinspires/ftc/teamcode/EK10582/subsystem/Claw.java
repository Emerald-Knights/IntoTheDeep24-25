package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.EK10582.subsystem.SubsystemConstants.clawStates;
import org.firstinspires.ftc.teamcode.EK10582.teleop.DriverStation;

import java.sql.Driver;

public class Claw extends Subsystem{
    public clawStates currentState;
    private DriverStation driverstation;

    public Claw(DriverStation driverstation){
        this.driverstation = driverstation;
    }

    //public DriverStation driverstation = new DriverStation(gamepad1,gamepad2);

    public void init(boolean isAuton){
        currentState = clawStates.CLOSED;
        Robot.getInstance().clawServo.setPosition(clawStates.CLOSED.position);
    }

    public void update(boolean isAuton){
        if(Robot.getInstance().clawServo.getPosition() == clawStates.CLOSED.position) {
            currentState = clawStates.OPEN;
            switch (openClaw()) {
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
             switch (openClaw()){
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
        telemetry.addData("Servo Position", Robot.getInstance().clawServo.getPosition());

    }
    public int openClaw() {
        if (driverstation.aButton) {
            if (driverstation.bButton) {
                return 0;
            }
            else {
                return 1;
            }
        } else {
            if (driverstation.bButton) {
                return -1;
            }
            else {
                return 0;
            }
        }
    }
}
