/*
 * MIT License
 *
 * Copyright (c) 2020 Mitty Robotics (Team 1351)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.mittyrobotics;


import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

/**
 * Robot Class to run the robot code (uses timed robot)
 */
public class Robot extends TimedRobot {
    /**
     * Sets the Robot to loop at 20 ms cycle
     */

    private final I2C.Port i2cPort = I2C.Port.kOnboard;

    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

    private final ColorMatch m_colorMatcher = new ColorMatch();

    public Robot() {
        super(0.02);
    }

    private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
    /**
     * Initializes all the hardware
     */
    @Override
    public void robotInit() {
        m_colorMatcher.addColorMatch(kBlueTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);
    }

    /**
     * Runs Scheduler for commands and updates the dashboard and OI
     */
    @Override
    public void robotPeriodic() {

    }

    /**
     * Brakes the drivetrain when disabling
     */
    @Override
    public void disabledInit() { }

    /**
     * Initializes and starts autonomous command
     */
    @Override
    public void autonomousInit() { }

    /**
     * Stops autonomous command and initializes controls
     */
    @Override
    public void teleopInit() { }

    @Override
    public void teleopPeriodic() {
        Color detectedColor = m_colorSensor.getColor();

        String colorString;
        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

        if(match.color == kBlueTarget) {
            colorString = "Blue";
        }
        else if(match.color == kRedTarget) {
            colorString = "Red";
        }
        else if(match.color == kGreenTarget) {
            colorString = "Green";
        }
        else if(match.color == kYellowTarget) {
            colorString = "Yello";
        }
        else {
            colorString = "Unknown";
        }


        SmartDashboard.putNumber("Red", detectedColor.red);
        SmartDashboard.putNumber("Green", detectedColor.green);
        SmartDashboard.putNumber("Blue", detectedColor.blue);
        SmartDashboard.putNumber("Confidence", match.confidence);
        SmartDashboard.putString("Detected Color", colorString);
    }

    /**
     * Function for initializing test code
     */
    @Override
    public void testInit() {
        //IntakeRaiseSubsystem.getInstance().testReset();
    }

    /**
     * Function for testing code
     */
    @Override
    public void testPeriodic() { }

}