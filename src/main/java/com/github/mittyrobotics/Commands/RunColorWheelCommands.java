package com.github.mittyrobotics.Commands;

import com.github.mittyrobotics.Subsystems.Pistons;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class RunColorWheelCommands extends SequentialCommandGroup {
    public RunColorWheelCommands(int color){ //-1 if not spinning to some color
        addCommands(
                new InstantCommand(()-> Pistons.getInstance().push()),
                new WaitCommand(1),
                color != -1 ? new SpinToColor(color) : new SpinThreeTimes(),
                new WaitCommand(1),
                new InstantCommand(()-> Pistons.getInstance().pull())
        );
    }
}
