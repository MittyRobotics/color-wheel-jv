package com.github.mittyrobotics;

import edu.wpi.first.wpilibj.*;

public class OI{
    private static OI instance;
    private XboxController controller;

    public static OI getInstance(){
        return instance == null ? instance = new OI() : instance;
    }
    public void initOI(){
        controller = new XboxController(0);
    }
    public XboxController getController(){
        return controller;
    }
}