package com.github.mittyrobotics.Commands;
import com.github.mittyrobotics.Subsystems.ColorWheel;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class SpinThreeTimes extends InstantCommand {

    public SpinThreeTimes() {
        addRequirements(ColorWheel.getInstance());
        int startColor = ColorWheel.getInstance().getColor();
        int counter = 0, prvCol = startColor;

        ColorWheel.getInstance().setSpeed(0.5);

        while (counter < 4){
            int newColor = ColorWheel.getInstance().getColor();

            if (newColor == startColor && prvCol != startColor) counter++;
            prvCol = newColor;
        }
        ColorWheel.getInstance().setSpeed(0);
    }
}
