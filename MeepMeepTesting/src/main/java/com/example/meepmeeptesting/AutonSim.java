package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class AutonSim {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(24, 66, Math.toRadians(-90)))
                        .lineToLinearHeading(new Pose2d(60,60, Math.toRadians(-135)))
                        .splineToSplineHeading(new Pose2d(36,26,Math.toRadians(0)),Math.toRadians(-90))
                        .forward(5)
                        .back(1)
                        .splineToSplineHeading(new Pose2d(60,60,Math.toRadians(-135)),Math.toRadians(90))
//                        .turn(Math.toRadians(90))
//                        .lineToLinearHeading(new Pose2d(60,60, Math.toRadians(-135)))
//                        .lineToLinearHeading(new Pose2d(58 ,38, Math.toRadians(-90)))
//                        .lineToLinearHeading(new Pose2d(60,60, Math.toRadians(-135)))

                        .build());



        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}