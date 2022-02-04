package com.github.mittyrobotics.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;

public class ColorWheel extends SubsystemBase {
    private static ColorWheel instance;

    private WPI_TalonSRX talon;

    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
    private final ColorMatch matcher = new ColorMatch();

    private final Color blue = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color green = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color red = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color yellow = ColorMatch.makeColor(0.361, 0.524, 0.113);

    public static ColorWheel getInstance(){
        return instance == null ? instance = new ColorWheel() : instance;
    }
    public void initHardware(){
        talon = new WPI_TalonSRX(20);
        matcher.addColorMatch(blue);
        matcher.addColorMatch(green);
        matcher.addColorMatch(red);
        matcher.addColorMatch(yellow);
    }
    public void setSpeed(double speed){
        talon.set(speed);
    }
    public int getColor(){
        ColorMatchResult match = matcher.matchClosestColor(colorSensor.getColor());
        if (match.color == blue) return 1;
        if (match.color == green) return 2;
        if (match.color == red) return 3;
        if (match.color == yellow) return 4;
        return -1;
    }
}
