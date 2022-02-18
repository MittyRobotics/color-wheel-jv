package com.github.mittyrobotics.Commands;
import com.github.mittyrobotics.Subsystems.ColorWheel;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class SpinToColor extends InstantCommand {

    public SpinToColor(int color) {
        super(()->{
            ColorWheel.getInstance().setSpeed(0.5);
            while (ColorWheel.getInstance().getColor() != color);
            ColorWheel.getInstance().setSpeed(0);
        });
    }
}
