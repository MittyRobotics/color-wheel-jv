package com.github.mittyrobotics;

import com.github.mittyrobotics.Subsystems.ColorWheel;
import com.github.mittyrobotics.Subsystems.Pistons;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {

    public Robot() { super(0.02); }


    /**
     * Initializes all the hardware
     */
    @Override
    public void robotInit() {
        ColorWheel.getInstance().initHardware();
        Pistons.getInstance().initHardware();
    }


    /**
     * Runs Scheduler for commands and updates the dashboard and OI
     */
    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }


    /**
     * Brakes the drivetrain when disabling
     */
    @Override
    public void disabledInit() {

    }


    /**
     * Initializes and starts autonomous command
     */
    @Override
    public void autonomousInit() {

    }


    /**
     * Stops autonomous command and initializes controls
     */
    @Override
    public void teleopInit() {

    }


    /**
     * Function for initializing test code
     */
    @Override
    public void testInit() {

    }


    /**
     * Function for testing code
     */
    @Override
    public void testPeriodic() {

    }
}