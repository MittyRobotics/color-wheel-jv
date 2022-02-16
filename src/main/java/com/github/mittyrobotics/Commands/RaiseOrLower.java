package com.github.mittyrobotics.Commands;
import com.github.mittyrobotics.Subsystems.Pistons;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class RaiseOrLower extends InstantCommand {

    public RaiseOrLower(boolean raise) {
        addRequirements(Pistons.getInstance());
        if (raise) Pistons.getInstance().push();
        else Pistons.getInstance().pull();
    }
}
