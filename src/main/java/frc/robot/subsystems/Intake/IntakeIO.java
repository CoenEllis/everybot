package frc.robot.subsystems.Intake;

import org.littletonrobotics.junction.AutoLog;

public interface IntakeIO {
    @AutoLog
    public class IntakeIOInputs {
        public boolean intake;
        public boolean outtake;
    }

    // updates if motors are moving
    public default void updateInputs(IntakeIO inputs) {}

    public default void setPower(double Power) {}
}
