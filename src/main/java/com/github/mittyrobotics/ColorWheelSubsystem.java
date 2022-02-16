package com.github.mittyrobotics;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.github.mittyrobotics.motion.controllers.PID;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ColorWheelSubsystem extends SubsystemBase {

    private static XboxController controller;

    static WPI_TalonSRX colorWheel = new WPI_TalonSRX(0);

    static DoubleSolenoid doubleSolenoid = new DoubleSolenoid(0, 0, 0);

    public static void execute () {

        boolean pressedY = controller.getYButtonPressed();

        if (pressedY) {
        doubleSolenoid.set(DoubleSolenoid.Value.kForward);

        velocityPID();
        }




    }

    public static void velocityPID() {


        double feedForward = 0; //temporary value
        double desiredVelocity = 0; //temporary constant until I know what value makes 50 rpm

        PIDController colorWheelMotor = new PIDController(1, 0, 0);

        double output = colorWheelMotor.calculate(colorWheel.getSelectedSensorVelocity());

        colorWheel.set((feedForward * desiredVelocity) + output);
    }

}
