package frc.robot.subsystems.Drive;

import org.littletonrobotics.junction.AutoLog;

public interface DriveIO {
    @AutoLog
    public class DriveIOInputs {
        public double driveRightLeadPosisitionRad;
        public double driveRightLeadVelocityRadPerSec;
        public double driveRightLeadAppliedVolts;

        public double driveLeftPositionRad;
        public double driveLeftLeadVelocityRadPerSec;
        public double driveLeftLeadAppliedVolts;

        public static float yaw;
        public static float pitch;
        public static float roll;
    }

    public default void updateInputs(DriveIO inputs) {}

    public default void setPower(double powerLeft, double powerRight) {}
}
