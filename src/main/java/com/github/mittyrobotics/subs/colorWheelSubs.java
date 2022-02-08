package com.github.mittyrobotics.subs;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class colorWheelSubs extends SubsystemBase {
    private static colorWheelSubs instance;
    private DoubleSolenoid solenoid;
    private WPI_TalonSRX talon;
    private XboxController controller;

    public static colorWheelSubs getInstance(){
        if (instance == null){
            instance = new colorWheelSubs();
        }
        return instance;
    }
    public void initHardware (){
        solenoid = new DoubleSolenoid(0,1);
        talon = new WPI_TalonSRX(20);
    }
    public void initController() {
        controller = new XboxController(0);

    }
    public boolean isAPressed(){
        return controller.getAButton();
    }
    public boolean isBPressed(){
        return controller.getBButton();
    }
    public void setSolenoid(String direction){
        if (direction == "off"){
            solenoid.set(DoubleSolenoid.Value.kOff);
        }
        else if (direction == "forward"){
            solenoid.set(DoubleSolenoid.Value.kForward);
        }
        else if (direction == "reverse"){
            solenoid.set(DoubleSolenoid.Value.kReverse);

        }
    }
    public void setTalon(double speed){
        talon.set(speed);
    }

}