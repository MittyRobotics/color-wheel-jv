package com.github.mittyrobotics.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorWheel extends SubsystemBase {

    private static ColorWheel instance;
    private WPI_TalonSRX motor;
    private DoubleSolenoid solenoid;

    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
    private final ColorMatch m_colorMatcher = new ColorMatch();

    //should I put in Constants class?
    final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);


    public static ColorWheel getInstance() {
        if(instance==null) {
            instance = new ColorWheel();
        }
        return instance;
    }

    public void initHardware() {
        motor = new WPI_TalonSRX(0);
        motor.configFactoryDefault();

        solenoid = new DoubleSolenoid(0, 0); // don't know id yet

        m_colorMatcher.addColorMatch(kBlueTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);
    }

    public void resetEncoders() {
        motor.setSelectedSensorPosition(0);
    }

    public ColorSensorV3 getColorSensor() {
        return m_colorSensor;
    }

    public ColorMatch getColorMatcher() {
        return m_colorMatcher;
    }

    public Color getkBlueTarget() {
        return kBlueTarget;
    }

    public Color getkGreenTarget() {
        return kGreenTarget;
    }

    public Color getkRedTarget() {
        return kRedTarget;
    }

    public Color getkYellowTarget() {
        return kYellowTarget;
    }

    public double getPosition() {
        return motor.getSelectedSensorPosition();
    }

    public void setMotors(double speed) {
        motor.set(ControlMode.PercentOutput, speed);
    }

    public void setSolenoid(String solenoidStatus) {
        if(solenoidStatus.equalsIgnoreCase("forward")) {
            solenoid.set(DoubleSolenoid.Value.kForward);
        }
        else if(solenoidStatus.equalsIgnoreCase("reverse")) {
            solenoid.set(DoubleSolenoid.Value.kReverse);
        }
        else if(solenoidStatus.equalsIgnoreCase("off")) {
            solenoid.set(DoubleSolenoid.Value.kOff);
        }
    }

}
