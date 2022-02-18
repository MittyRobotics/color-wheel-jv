package com.github.mittyrobotics.Subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.github.mittyrobotics.Constants;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorWheel extends SubsystemBase {
    private static ColorWheel instance;

    private WPI_TalonSRX talon = new WPI_TalonSRX(Constants.talonId);

    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
    private final ColorMatch matcher = new ColorMatch();

    public static ColorWheel getInstance(){
        return instance == null ? instance = new ColorWheel() : instance;
    }
    public void initHardware(){
        matcher.addColorMatch(Constants.blue);
        matcher.addColorMatch(Constants.green);
        matcher.addColorMatch(Constants.red);
        matcher.addColorMatch(Constants.yellow);
    }
    public void setSpeed(double speed){
        talon.set(speed);
    }
    public int getColor(){
        ColorMatchResult match = matcher.matchClosestColor(colorSensor.getColor());
        if (match.color == Constants.blue) return 1;
        if (match.color == Constants.green) return 2;
        if (match.color == Constants.red) return 3;
        if (match.color == Constants.yellow) return 4;
        return -1;
    }
}
