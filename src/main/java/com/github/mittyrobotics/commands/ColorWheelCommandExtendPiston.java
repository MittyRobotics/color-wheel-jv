package com.github.mittyrobotics.commands;

import com.github.mittyrobotics.OI;
import com.github.mittyrobotics.subsystems.ColorWheel;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ColorWheelCommandExtendPiston extends CommandBase {
    @Override
    public void initialize() {
        ColorWheel.getInstance().setSolenoid("forward");
    }

    @Override
    public void execute() {

    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
