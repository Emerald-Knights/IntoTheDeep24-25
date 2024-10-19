package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.opencv.core.Mat;

import org.opencv.core.Size;
import org.openftc.easyopencv.OpenCvPipeline;

public class CameraPipeline extends OpenCvPipeline {

    double[] targetColor = {161,39.5,55.5};
    double[] replacementColor = {0, 255, 0, 1};

    double percentError = 0.5;


    Mat output = new Mat();

    @Override
    public Mat processFrame(Mat input) {

        Size dimensions = input.size();
        double height = dimensions.height;
        double width = dimensions.width;

        output = input.clone();


        for(int j = 0; j < height; j++){ //height
            for(int k = 0; k < width; k++){ //width
                double[] currentColor = output.get(j,k); //color of each pixel
                if(compareColor(targetColor, currentColor, percentError)){
                    output.put(j,k, replacementColor); //if color is target color, change color
                }
            }
        }
        
        return output;

    }

    public boolean compareColor(double[] targ, double[] cur, double percentError){
        if (Math.abs(targ[0] - cur[0]) < percentError * targ[0] && Math.abs(targ[1] - cur[1]) < percentError*targ[1] && Math.abs(targ[2] - cur[2]) < percentError*targ[2]){
            return true;
        }
        return false;
    }

}