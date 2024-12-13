package frc.robot.subsystems.Intake;

import org.littletonrobotics.junction.Logger;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    private Intake.io;
    private IntakeIOInputsAutologged inputs = new IntakeIOInputsAutoLogged();

    public Intake(IntakeIO intakeIO) {
        this.io = intakeIO;
    }
    
    @Override
    public void periodic() {
        Logger.processInputs("Intake", inputs); 
        io.updateInputs(inputs);
    }

    public void setPower(double power) {
        Logger.recordOutput("Intake/power", power);
        io.setPower(power);
    }

    public boolean getIntake() { //returns the true or false value of intake motor
        return inputs.intake;
    }

    public boolean getOuttake() { //returns the true or false value of outtake motor
        return inputs.outtake;
    }
    //no trigger = 0
    public Command intake() {
        return run(() -> {
            setPower(1);
        }).unless(() -> inputs.intake).withTimeout(0);
    }
    //shoots
    public Command shoot() {
        return run(() -> {
            setPower(1);
        }).withTimeout (0);
    }

    public Command move() {
        return run(() -> {
            setPower(1);
        }).unless(() -> inputs.outtake).withTimeout(0);
    }
}