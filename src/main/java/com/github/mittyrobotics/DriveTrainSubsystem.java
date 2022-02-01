package com.github.mittyrobotics;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.ArrayList;

public class DriveTrainSubsystem extends SubsystemBase {

    private static DriveTrainSubsystem instance;

    private WPI_TalonSRX left1;
    private WPI_TalonSRX left2;
    private WPI_TalonSRX right1;
    private WPI_TalonSRX right2;

    private PIDController pidController;

    public static DriveTrainSubsystem getInstance() {
        if (instance == null) {
            instance = new DriveTrainSubsystem();
        }
        return instance;
    }

    public void resetPosition() {
        left1.setSelectedSensorPosition(0);
        left2.setSelectedSensorPosition(0);
        right1.setSelectedSensorPosition(0);
        right2.setSelectedSensorPosition(0);
    }

    public void initHardware() {

        left1 = new WPI_TalonSRX(20);
        left2 = new WPI_TalonSRX(21);
        right1 = new WPI_TalonSRX(22);
        right2 = new WPI_TalonSRX(23);

        left1.configFactoryDefault();
        left2.configFactoryDefault();
        right1.configFactoryDefault();
        right2.configFactoryDefault();

        left1.setInverted(true);
        left2.setInverted(true);
        right1.setInverted(false);
        right2.setInverted(false);

        left1.setNeutralMode(NeutralMode.Brake);
        left2.setNeutralMode(NeutralMode.Brake);
        right1.setNeutralMode(NeutralMode.Brake);
        right2.setNeutralMode(NeutralMode.Brake);

        pidController = new PIDController(0.1, 0, 0);

        left1.getSelectedSensorPosition();
        right1.getSelectedSensorPosition();
        right2.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        left2.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    }


    public void setPosition(double setpoint) {
        double TICKSPERINCH = 500;
        setpoint = setpoint * TICKSPERINCH;
    }

    public void calculate() {
        pidController.calculate(left1.getSelectedSensorPosition());
        pidController.calculate(left2.getSelectedSensorPosition());
        pidController.calculate(right1.getSelectedSensorPosition());
        pidController.calculate(right2.getSelectedSensorPosition());
    }

    public double[] getPositionLeft() {

        double[] leftResult = new double[2];
        leftResult[0] = left1.getSelectedSensorPosition();
        leftResult[1] = left2.getSelectedSensorPosition();

        return leftResult;
    }

    public double[] getPositionRight() {

        double[] rightResult = new double[2];
        rightResult[0] = right1.getSelectedSensorPosition();
        rightResult[1] = right2.getSelectedSensorPosition();

        return rightResult;
    }


    public void setSpeed(double left, double right) {
        left1.set(left);
        left2.set(left);
        right1.set(right);
        right2.set(right);
    }

    public void updateDashboard() {
    }

    public void brake() {
    }

    public void updateOI() {
    }

    public void setupControls() {
    }

    public void addSubsystems(DriveTrainSubsystem instance) {
    }
}
