package frc.robot.subsystems.Drive;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.kauailabs.navx.frc.AHRS;
import frc.robot.Constants;

/**
 * DrivetrainReal
 */
public class DriveReal implements DriveIO {
    private final TalonFX driveFrontRightLead = new TalonFX(4, "CANivore");
    private final TalonFX driveFrontLeftLead = new TalonFX(2, "CANivore");
    private final TalonFX driveBackRightFollow = new TalonFX(5, "CANivore");
    private final TalonFX driveBackLeftFollow = new TalonFX(3, "CANivore");

    private final AHRS gyro = new AHRS(Constants.Drive.nacXID);
    private final StatusSignal<Double> driveRightLeadPosition;
    private final StatusSignal<Double> driveRightLeadVelocity;
    private final StatusSignal<Double> driveRightLeadVoltage;

    private final StatusSignal<Double> driveLeftLeadPosition;
    private final StatusSignal<Double> driveLeftLeadVelocity;
    private final StatusSignal<Double> driveLeftLeadVoltage;

    public DriveReal() {
        //sets each drive device
        driveBackLeftFollow.set(driveFrontLeftLead.getDeviceID());
        driveBackRightFollow.set(driveFrontRightLead.getDeviceID());
        //corresponds right
        driveRightLeadPosition = driveFrontRightLead.getPosition();
        driveRightLeadPosition = driveFrontRightLead.getVelocity();
        driveRightLeadPosition = driveFrontRightLead.getMotorVoltage();
        //corresponds left
        driveLeftLeadPosition = driveFrontLeftLead.getPosition(); 
        driveLeftLeadPosition = driveFrontLeftLead.getVelocity();
        driveLeftLeadPosition = driveFrontLeftLead.getMotorVoltage();

        driveFrontLeftLead.getConfigurator();
        //end wheel movement as you don't touch the controller
        driveFrontLeftLead.setNeutralMode(NeutralModeValue.Brake); 
        driveFrontRightLead.setNeutralMode(NeutralModeValue.Brake);
        driveBackLeftFollow.setNeutralMode(NeutralModeValue.Brake);
        driveBackRightFollow.setNeutralMode(NeutralModeValue.Brake);
        //sets power to motors and the motors correspond to be equal
        public void setPower(double powerLeft, double powerRight) {
            driveBackLeftFollow.set(powerLeft);
            driveBackRightFollow.set(powerRight);
        }   

        public void periodic() {}

        public void updateInputs(DriveIoInputs inputs) {
            inputs.yaw = gyro.getYaw();
            inputs.roll = gyro.getRoll();
            inputs.pitch = gyro.getPitch();

            BaseStatusSignal.refreshAll(driveRightLeadPosition, driveRightLeadVelocity, driveRightLeadVoltage, driveLeftLeadPosition, driveLeftLeadVelocity, driveLeftLeadVoltage);
        }
    }
}
