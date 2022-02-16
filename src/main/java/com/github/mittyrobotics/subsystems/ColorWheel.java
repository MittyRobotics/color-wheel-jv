package com.github.mittyrobotics.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorWheel extends SubsystemBase {
    private static ColorWheel instance;
    private WPI_TalonSRX motor;
    private DoubleSolenoid solenoid;
    private PIDController pid;


    public static ColorWheel getInstance() {
        if(instance==null) {
            instance = new ColorWheel();
        }
        return instance;
    }

    public void initHardware() {
        motor = new WPI_TalonSRX(0);
        motor.configFactoryDefault();
        //motor.setInverted(true);

        solenoid = new DoubleSolenoid(0, 0); // don't know id yet

        pid = new PIDController(0, 0, 0);
        pid.setSetpoint(50);
    }

    public void resetEncoders() {
        motor.setSelectedSensorPosition(0);
    }

    public double getPosition() {
        return motor.getSelectedSensorPosition();
    }

    public void setMotors(double speed) {
        motor.set(ControlMode.PercentOutput, speed);
    }

}
