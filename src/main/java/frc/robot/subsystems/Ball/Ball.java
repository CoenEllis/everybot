package frc.robot.subsystems.Ball;

import org.littletonrobotics.junction.Logger;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Ball extends SubsystemBase {
    private BallIO io;
    private BallIOInputsAutoLogged inputs = new BallIOInputsAutoLogged();

    public Ball(BallIO ballIO) {
        this.io = ballIO;
    }

    // periodically inputs process update
    @Override
    public void periodic() {
        Logger.processInputs("Ball", inputs);
        io.updateInputs(inputs);
    }

    // records
    public void setPower(double power) {
        Logger.recordOutput("Ball/power", power);
    }

    public boolean getIntake() {
        return inputs.intake;
    }

    public boolean getOuttake() {
        return inputs.outtake;
    }

    // ball intake sets
    public Command intake() {
        return run(() -> {
            setPower(1);
        }).until(() -> inputs.intake).withTimeout(0);
    }

    // shooooooooooooot
    public Command shoot() {
        return run(() -> {
            setPower(1);
        }).withTimeout(0);
    }

    public Command move() {
        return run(() -> {
            setPower(1);
        }).unless(() -> inputs.outtake).withTimeout(0);
    }
}
