package frc.robot.subsystems.Hatch;

import com.ctre.phoenix6.hardware.CANcoder;
import com.google.flatbuffers.Constants;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.drive.RobotDriveBase.MotorType;

public class HatchReal implements HatchIO {
    private CANSparkMAx hatchMotor =
        new CANSparkMax(Constants.Hatch.HATCHMOTOR, MotorType.kBrushless);
    private DigitalInput touchSensor = new DigitalInput(Constants.Hatch.TOUCHSENSOR);
    private CANcoder hatchCancoder = new CANcoder(Constants.Hatch.CANCODER);
    private AbsoluteEncoder hatchWristEnc = hatchMotor.getAbsoluteEncoder();

    public HatchReal() {
        hatchWristEnc.setPositionConversionFactor(1);
        hatchMotor.setIdleMode(IdleMode.kBrake);
    }

    @Override
    public void setVoltage(double v) {
        hatchMotor.setVoltage(v);
    }

    public void updateInputs(HatchIOInputs inputs) {
        inputs.hatchAbsoluteENCRawValue = hatchCancoder.getPosition().getValueAsDouble();
        inputs.touchSensor = touchSensor.get();
    }
}
