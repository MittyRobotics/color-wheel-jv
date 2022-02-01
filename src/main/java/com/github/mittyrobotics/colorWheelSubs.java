public class ColorWheelSubs extends SubsystemBase {
    private static colorWheelSubs instance;
    private DoubleSolenoid solenoid;
    private WPI_TalonSRX talon;
    private XboxController controller;

    public static ColorWheelSubs getInstance(){
        if (instance == null){
            instance = new ColorWheelSubs();
        }
        return instance;
    }
    public void initHardware (){
        solenoid = new DoubleSolenoid(0,1);
        talon = new WPI_TalonSRX(20);
    }
    public void initController() {
        controller = new XboxController(0);

    }
    public boolean isAPressed(){
        return controller.getAButton();
    }
    public boolean isBPressed(){
        return controller.getBButton();
    }
    public void moveSolenoidForward(){
        solenoid.set(DoubleSolenoid.Value.kForward);
    }
    public void moveTalon(){
        talon.set(0.5) //change to slower or faster?
    }

    }