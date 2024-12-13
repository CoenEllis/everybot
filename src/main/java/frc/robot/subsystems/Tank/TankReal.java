package frc.robot.subsystems.Tank;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.controls.StrictFollower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.kauailabs.navx.frc.AHRS;
import frc.robot.Constants;

public class TankReal implements TankIO {

    private final TalonFX tankFrontRightLead = new TalonFX(Constants.Tank.FRONTRIGHT, "CANivore");
    private final TalonFX tankFrontLeftLead = new TalonFX(Constants.Tank.FRONTLEFT, "CANivore");
    private final TalonFX tankBackRightFollow = new TalonFX(Constants.Tank.BACKRIGHT, "CANivore");
    private final TalonFX tankBackLeftFollow = new TalonFX(Constants.Tank.BACKLEFT, "CANivore");

    private final AHRS gyro = new AHRS(Constants.Tank.navXID);
    private final StatusSignal<Double> tankRightLeadVelocity;
    private final StatusSignal<Double> tankLeftLeadVelocity;

    public TankReal() {

        tankBackLeftFollow.setControl(new StrictFollower(tankFrontLeftLead.getDeviceID()));
        tankBackRightFollow.setControl(new StrictFollower(tankFrontRightLead.getDeviceID()));

        tankRightLeadVelocity = tankFrontRightLead.getVelocity();
        tankLeftLeadVelocity = tankFrontLeftLead.getVelocity();

        tankFrontLeftLead.setNeutralMode(NeutralModeValue.Brake);
        tankFrontRightLead.setNeutralMode(NeutralModeValue.Brake);
        tankBackLeftFollow.setNeutralMode(NeutralModeValue.Brake);
        tankBackRightFollow.setNeutralMode(NeutralModeValue.Brake);
    }

    @Override
    public void setVolatge(double leftV, double rightV) {
        tankBackLeftFollow.set(leftV);
        tankBackRightFollow.set(rightV);
    }

    public void periodic() {}

    public void updateInputs(TankIOInputs inputs) {
        inputs.yaw = gyro.getYaw();
        inputs.roll = gyro.getRoll();
        inputs.pitch = gyro.getPitch();

        inputs.tankRightLeadVelocity = tankRightLeadVelocity.getValueAsDouble();
        inputs.tankLeftLeadVelocity = tankLeftLeadVelocity.getValueAsDouble();

        BaseStatusSignal.refreshAll(tankRightLeadVelocity, tankLeftLeadVelocity);
    }
}
