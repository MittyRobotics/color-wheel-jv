package com.github.mittyrobotics.commands;

import com.github.mittyrobotics.subs.colorWheelSubs;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class colorWheelCommand extends CommandBase {
    public colorWheelCommand(){
        addRequirements(colorWheelSubs.getInstance());
    }
    double feedForward = 0;
    double desiredVelo = 0;
    final I2C.Port i2cPort = I2C.Port.kOnboard;
    final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
    final ColorMatch m_colorMatcher = new ColorMatch();
    final Color kBlueTarget = new Color(0.143, 0.427, 0.429);
    final Color kGreenTarget = new Color(0.197, 0.561, 0.240);
    final Color kRedTarget = new Color(0.561, 0.232, 0.114);
    final Color kYellowTarget = new Color(0.361, 0.524, 0.113);
    ColorMatchResult match;
    Color color;
    @Override
    public void initialize() {
        colorWheelSubs.getInstance().setTalon(0);
        colorWheelSubs.getInstance().setSolenoid("forward");
        m_colorMatcher.addColorMatch(kBlueTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);
        color = m_colorSensor.getColor();
        match = m_colorMatcher.matchClosestColor(color);

    }
    @Override
    public void execute() {
        //colorWheelSubs.getInstance().setSolenoid("forward");
        while (true){
            if (colorWheelSubs.getInstance().isAPressed()){
                break;
            }
        }


        Color startColor;
        if (match.color == kBlueTarget) {
            startColor = match.color;
        } else if (match.color == kRedTarget) {
            startColor = match.color;
        } else if (match.color == kGreenTarget) {
            startColor = match.color;
        } else{
            startColor = match.color;
        }
        PIDController colorWheelMotor = new PIDController(0.1, 0, 0);
        double output = colorWheelMotor.calculate(colorWheelSubs.getInstance().getSelectedSensorVelo());
        colorWheelSubs.getInstance().setTalon((feedForward * desiredVelo) + output);
        int count = 0;
        while (count < 3){
            Color color1 = m_colorSensor.getColor();
            ColorMatchResult match1 = m_colorMatcher.matchClosestColor(color1);
            if (startColor == match1.color ){
                count += 1;
            }

        }
        colorWheelSubs.getInstance().setTalon(0);
        colorWheelSubs.getInstance().setSolenoid("reverse");












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