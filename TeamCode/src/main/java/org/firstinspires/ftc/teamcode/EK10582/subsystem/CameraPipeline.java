package org.firstinspires.ftc.teamcode.EK10582.subsystem;

import org.opencv.core.Mat;

import org.opencv.core.Size;
import org.openftc.easyopencv.OpenCvPipeline;

public class CameraPipeline extends OpenCvPipeline {


    double percentError = 0.6;
    double[] replacementColor = {0, 255, 0, 1};
    double[] targetColor = {185, 35, 30};

    @Override
    public Mat processFrame(Mat input) {
        Size dimensions = input.size();
        double height = dimensions.height;
        double width = dimensions.width;

        Mat output = input.clone();
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                double[] currentColor = output.get(j,i);
                if (compareColor(targetColor, currentColor)){
                    output.put(j,i,replacementColor);
                }
            }
        }
        return output;
    }



    public boolean compareColor(double[] target, double[] current){
        for (int i = 0; i <= 2; i++){
            if (!(target[i] * (1-percentError)  <= current[i] && target[i] * (1+percentError)>= current[i])){
                return false;
            }
        }
        return true;
    }
}