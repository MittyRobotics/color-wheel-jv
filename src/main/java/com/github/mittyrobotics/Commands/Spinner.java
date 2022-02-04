package com.github.mittyrobotics.Commands;
import com.github.mittyrobotics.Subsystems.ColorWheel;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Spinner extends CommandBase {

    public Spinner() {
        addRequirements(ColorWheel.getInstance());
    }
    public void initialize(){
        ColorWheel.getInstance().setSpeed(0);
    }
    //Stage 2; rotate more than 3 and less than 5 times
    public void SpinThreeTimes(){
        int startColor = ColorWheel.getInstance().getColor();
        int counter = 0, curColor = startColor;

        ColorWheel.getInstance().setSpeed(0.5);

        while (counter < 4){
            int newColor = ColorWheel.getInstance().getColor();

            if (newColor == startColor && curColor != startColor) counter++;
            curColor = newColor;
        }
        ColorWheel.getInstance().setSpeed(0);
    }
    //Stage 3; rotate to color
    public void SpinToColor(int color){
        ColorWheel.getInstance().setSpeed(0.5);
        while (ColorWheel.getInstance().getColor() != color);
        ColorWheel.getInstance().setSpeed(0);
    }
}
