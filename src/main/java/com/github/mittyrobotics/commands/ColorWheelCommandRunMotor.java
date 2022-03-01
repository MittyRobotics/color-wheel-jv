package com.github.mittyrobotics.commands;

import com.github.mittyrobotics.subsystems.ColorWheel;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ColorWheelCommandRunMotor extends CommandBase {

    Boolean foundDesiredColor;

    @Override
    public void initialize() {
        PIDController pid = new PIDController(0, 0, 0);;
        pid.setSetpoint(50);

        //spin at 50rpm?
    }

    @Override
    public void execute() {
        //run motor?
        //how do you spin two full rotations
        //use controller?

        Color detectedColor = ColorWheel.getInstance().getColorSensor().getColor();
        ColorMatchResult match = ColorWheel.getInstance().getColorMatcher().matchClosestColor(detectedColor);

        Color desiredColor = ColorWheel.getInstance().getkBlueTarget();

        if(match.color == ColorWheel.getInstance().getkBlueTarget()) {
            foundDesiredColor = true;
        }
        else {
            foundDesiredColor = false;
        }


        /*SmartDashboard.putNumber("Red", detectedColor.red);
        SmartDashboard.putNumber("Green", detectedColor.green);
        SmartDashboard.putNumber("Blue", detectedColor.blue);
        SmartDashboard.putNumber("Confidence", match.confidence);
        SmartDashboard.putString("Detected Color", colorString);*/
    }

    @Override
    public void end(boolean interrupted) {
        ColorWheel.getInstance().setMotors(0);
    }

    @Override
    public boolean isFinished() {
        if(foundDesiredColor) {
            return true;
        }
        return false;
    }
}
