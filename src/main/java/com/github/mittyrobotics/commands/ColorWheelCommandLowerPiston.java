package com.github.mittyrobotics.commands;

import com.github.mittyrobotics.subsystems.ColorWheel;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ColorWheelCommandLowerPiston extends CommandBase {

    @Override
    public void initialize() {
        ColorWheel.getInstance().setSolenoid("reverse");
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
