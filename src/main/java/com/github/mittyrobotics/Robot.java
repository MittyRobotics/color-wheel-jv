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

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import java.awt.*;

/**
 * Robot Class to run the robot code (uses timed robot)
 */
public class Robot extends TimedRobot {

    private static XboxController controller;

    static WPI_TalonSRX colorWheel = new WPI_TalonSRX(0);
    static DoubleSolenoid doubleSolenoid = new DoubleSolenoid(0, 0, 0);

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

        execute();
    }

    public void execute() {

        boolean pressedY = controller.getYButtonPressed();

        if (pressedY) {
            doubleSolenoid.set(DoubleSolenoid.Value.kForward);

            velocityPID();
        }
        robotPeriodic();

    }

    public static void velocityPID() {

        double feedForward = 0; //temporary value
        double desiredVelocity = 0; //temporary constant until I know what value makes 50 rpm

        PIDController colorWheelMotor = new PIDController(1, 0, 0);

        double output = colorWheelMotor.calculate(colorWheel.getSelectedSensorVelocity());

        colorWheel.set((feedForward * desiredVelocity) + output);
    }


    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        SubsystemManager.getInstance().updateDashboard();
        OI.getInstance().updateOI();

        Color colorDetected = m_colorSensor.getColor();

        String colorString;

        ColorMatchResult match = m_colorMatcher.matchClosestColor(colorDetected);

        if (colorDetected == targetBlue){
            colorString = "Blue";
        }
        else if (colorDetected == targetGreen) {
            colorString = "Green";
        }
        else if (colorDetected == targetRed) {
            colorString = "Red";
        }
        else if (colorDetected == targetYellow) {
            colorString = "Yellow";
        }
        else {
            colorString = "Unknown";
        }

        SmartDashboard.putNumber("Blue", colorDetected.blue);
        SmartDashboard.putNumber("Green", colorDetected.green);
        SmartDashboard.putNumber("Red", colorDetected.red);
        SmartDashboard.putNumber("Confidence", match.confidence);
        SmartDashboard.putString("Detected Color", colorString);
    }




    @Override
    public void disabledInit() {
        DriveTrainSubsystem.getInstance().brake();
    }


    @Override
    public void autonomousInit() {
        OI.getInstance().initHardware();
    }


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