package com.github.mittyrobotics;

import edu.wpi.first.wpilibj.RobotBase;

/**
 * Main Class to run robot code, do not touch
 */
public final class Main {
    private Main() {

    }
    /**
     * Main function to run robot code, do not touch
     */
    public static void main(String... args) {
        RobotBase.startRobot(Robot::new);
    }
}