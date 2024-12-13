package frc.robot.subsystems.Ball;

public interface BallIO {
    @Override
    public class BallIOInputs {
        public boolean intake;
        public boolean outtake;
    }

    public default void updateInputs(BallIOInputs inputs) {}

    public default void setPower(double Power) {}
}
