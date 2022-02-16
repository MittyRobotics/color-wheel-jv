package com.github.mittyrobotics.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pistons extends SubsystemBase {
    private static Pistons instance;

    DoubleSolenoid ds = new DoubleSolenoid(0, 1);

    public static Pistons getInstance(){
        return instance == null ? instance = new Pistons() : instance;
    }
    public void initHardware(){ }
    public void push(){ ds.set(DoubleSolenoid.Value.kForward); }
    public void pull(){ ds.set(DoubleSolenoid.Value.kReverse); }
}
