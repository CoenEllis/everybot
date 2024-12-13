package frc.robot.subsystems.Intake;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DigitalInput;


public class IntakeReal implements IntakeIO { // records information to IntakeIO
    private final CANSparkMax intakeMotor = new CANSparkMax(12, MotorType.kBrushless);
    private final DigitalInput intakeBeamBrake = new DigitalInput(0);
    private final DigitalInput outtakeBeamBrake = new DigitalInput(1);

    public IntakeReal() {}

    @Override

    public void setPower(double power) {
        intakeMotor.set(power);
    }

    public void periodic() {}

    public void updateInputs(IntakeIOInputs inputs) {
        inputs.intake = intakeBeamBrake.get();
        inputs.outtake = outtakeBeamBrake.get();
    }

}
