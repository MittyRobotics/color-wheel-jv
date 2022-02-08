package com.github.mittyrobotics.commands;

import com.github.mittyrobotics.subs.colorWheelSubs;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class colorWheelCommand extends CommandBase {
    public colorWheelCommand(){
        addRequirements(colorWheelSubs.getInstance());
    }
    @Override
    public void initialize() {
        colorWheelSubs.getInstance().setTalon(0);
        colorWheelSubs.getInstance().setSolenoid("off");

    }
    @Override
    public void execute() {
        colorWheelSubs.getInstance().setSolenoid("forward");
        while (true){
            if (colorWheelSubs.getInstance().isAPressed()){
                break;
            }
        }
        colorWheelSubs.getInstance().setTalon(0.5);






//        DrivetrainSubsystem.getInstance().setMotors
//                (DrivetrainSubsystem.getInstance().getControllerLeft(),
//                        DrivetrainSubsystem.getInstance().getControllerRight());
    }
    @Override
    public void end(boolean interrupted) {

    }
    @Override
    public boolean isFinished() {
        return false;
    }
}