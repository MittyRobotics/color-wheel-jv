package com.github.mittyrobotics;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Button;

public class OI {

    XboxController controller;

    private static OI instance;

    private OI() {

    }

    public static OI getInstance() {
        if (instance == null) {
            instance = new OI();
        }
        return instance;
    }

    public XboxController getController() {
        return controller;
    }

    public void initOI() {
        controller = new XboxController(0);
    }

    public void updateOI() {

    }

    public void setupControls() {

    }

    public void initHardware() {
    }
}
