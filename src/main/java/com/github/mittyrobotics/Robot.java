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

/*import com.github.mittyrobotics.colorwheel.ColorPistonSubsystem;
import com.github.mittyrobotics.colorwheel.SpinnerSubsystem;
import com.github.mittyrobotics.conveyor.ConveyorSubsystem;
import com.github.mittyrobotics.conveyor.IntakeRaiseSubsystem;
import com.github.mittyrobotics.conveyor.IntakeSubsystem;
import com.github.mittyrobotics.drivetrain.DrivetrainSubsystem;
import com.github.mittyrobotics.shooter.ShooterSubsystem;
import com.github.mittyrobotics.shooter.TurretSubsystem;
import com.github.mittyrobotics.util.Compressor;
import com.github.mittyrobotics.util.Gyro;
import com.github.mittyrobotics.util.OI;
import com.github.mittyrobotics.util.SubsystemManager;*/
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import java.awt.*;

/**
 * Robot Class to run the robot code (uses timed robot)
 */
public class Robot extends TimedRobot {

    private final I2C.Port i2cPort = I2C.Port.kOnboard;

    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
    private final ColorMatch m_colorMatcher = new ColorMatch();

    private final Color targetBlue = ColorMatch.makeColor(0, 0, 0);
    private final Color targetGreen = ColorMatch.makeColor(0, 0, 0);
    private final Color targetRed = ColorMatch.makeColor(0, 0, 0);
    private final Color targetYellow = ColorMatch.makeColor(0, 0, 0);


   public Robot() {
        super(0.02);
    }

    @Override
    public void robotInit() {

       m_colorMatcher.addColorMatch(targetBlue);
       m_colorMatcher.addColorMatch(targetGreen);
       m_colorMatcher.addColorMatch(targetRed);
       m_colorMatcher.addColorMatch(targetYellow);


       ColorMatchResult match = m_colorMatcher.matchColor()
       SubsystemManager.getInstance().addSubsystems(
//                ConveyorSubsystem.getInstance(),
       DriveTrainSubsystem.getInstance()
//                IntakeRaiseSubsystem.getInstance(),
//                IntakeSubsystem.getInstance(),
//                ShooterSubsystem.getInstance(),
//                TurretSubsystem.getInstance()
        );
        SubsystemManager.getInstance().initHardware();
//        Gyro.getInstance().initHardware();
        //Compressor.getInstance().initHardware();
    }

    /**
     * Runs Scheduler for commands and updates the dashboard and OI
     */
    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        SubsystemManager.getInstance().updateDashboard();
        OI.getInstance().updateOI();
    }

    /**
     * Brakes the drivetrain when disabling
     */
    @Override
    public void disabledInit() {
        DriveTrainSubsystem.getInstance().brake();
    }

    /**
     * Initializes and starts autonomous command
     */
    @Override
    public void autonomousInit() {
        OI.getInstance().initHardware();
    }

    /**
     * Stops autonomous command and initializes controls
     */
    @Override
    public void teleopInit() {
        OI.getInstance().setupControls();
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
    public void testPeriodic() {

    }

}