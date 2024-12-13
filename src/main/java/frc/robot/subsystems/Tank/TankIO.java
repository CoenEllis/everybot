package frc.robot.subsystems.Tank;

import org.littletonrobotics.junction.AutoLog;

public interface TankIO {
    @AutoLog
    public class TankIOInputs {
        public double tankRightLeadVelocity;
        public double tankLeftLeadVelocity;

        public static float yaw;
        public static float pitch;
        public static float roll;
    }

    public default void updateInputs(TankIOInputs inputs) {}

    public default void setVoltage(double leftV, double rightV) {}
}
