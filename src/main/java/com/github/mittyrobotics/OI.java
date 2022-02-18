package com.github.mittyrobotics;

import edu.wpi.first.wpilibj.*;

public class OI{
    private static OI instance;
    private XboxController controller = new XboxController(Constants.controllerId);

    public static OI getInstance(){
        return instance == null ? instance = new OI() : instance;
    }
    public void initOI(){}
    public XboxController getController(){
        return controller;
    }
}