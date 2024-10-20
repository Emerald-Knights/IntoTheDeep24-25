package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import org.rowlandhall.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class AutonSim {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity redRight = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setColorScheme(new ColorSchemeBlueDark())
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(24, 66, Math.toRadians(-90)))
                        .lineToLinearHeading(new Pose2d(60,60, Math.toRadians(-135)))
                        .splineToSplineHeading(new Pose2d(36,24,Math.toRadians(0)),Math.toRadians(-90))
                        .splineToSplineHeading(new Pose2d(56,60, Math.toRadians(0)), Math.toRadians(-90))
                        .splineToSplineHeading(new Pose2d(46,24,Math.toRadians(0)),Math.toRadians(-90))
                        .splineToSplineHeading(new Pose2d(56,60, Math.toRadians(25)), Math.toRadians(60))
                        .splineToSplineHeading(new Pose2d(52,24,Math.toRadians(0)),Math.toRadians(-90))
                        .splineToSplineHeading(new Pose2d(56,60, Math.toRadians(25)), Math.toRadians(60))
                        .build());

        RoadRunnerBotEntity blueLeft = new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(-24, -66, Math.toRadians(-90)))
                        .lineToLinearHeading(new Pose2d(-60,-60, Math.toRadians(210)))
                        .splineToSplineHeading(new Pose2d(-36,-24,Math.toRadians(180)),Math.toRadians(90))
                        .splineToSplineHeading(new Pose2d(-56,-60, Math.toRadians(180)), Math.toRadians(90))
                        .splineToSplineHeading(new Pose2d(-46,-24,Math.toRadians(180)),Math.toRadians(90))
                        .splineToSplineHeading(new Pose2d(-56,-60, Math.toRadians(210)), Math.toRadians(240))
                        .splineToSplineHeading(new Pose2d(-52,-24,Math.toRadians(180)),Math.toRadians(90))
                        .splineToSplineHeading(new Pose2d(-56,-60, Math.toRadians(210)), Math.toRadians(240))
                        .build());

        RoadRunnerBotEntity redPlayerZone = new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .setColorScheme(new ColorSchemeBlueDark())
                .followTrajectorySequence(drive -> drive.trajectorySequenceBuilder(new Pose2d(24, -66, Math.toRadians(-90)))
                        .lineToLinearHeading(new Pose2d(-60,-60, Math.toRadians(210)))
                        .splineToSplineHeading(new Pose2d(-36,-24,Math.toRadians(180)),Math.toRadians(90))
                        .splineToSplineHeading(new Pose2d(-56,-60, Math.toRadians(180)), Math.toRadians(90))
                        .splineToSplineHeading(new Pose2d(-46,-24,Math.toRadians(180)),Math.toRadians(90))
                        .splineToSplineHeading(new Pose2d(-56,-60, Math.toRadians(210)), Math.toRadians(240))
                        .splineToSplineHeading(new Pose2d(-52,-24,Math.toRadians(180)),Math.toRadians(90))
                        .splineToSplineHeading(new Pose2d(-56,-60, Math.toRadians(210)), Math.toRadians(240))
                        .build());
        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(redRight)
                .addEntity(blueLeft)
                .start();
    }
}