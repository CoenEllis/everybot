package frc.robot.subsystems.Tank;

import java.lang.System.Logger;
import com.google.flatbuffers.Constants;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class Tank extends SubsystemBase {
    private TankIO io;
    private TankIOInputsAutoLogged inputs = new TankIOInputsAutoLogged();

    public Tank(tankIO tankIO) {
        this.io = tankIO;
        io.updateInputs(inputs);
    }

    @Override
    public void periodic() {
        Logger.processInputs("Tank", inputs);
        io.updateInputs(inputs);
    }

    PIDController tankPidControllerL =
        new PIDController(Constants.Tank.TANK_KP, Constants.Tank.TANK_KI, Constants.Tank.TANK_KD);

    PIDController tankPidControllerR =
        new PIDControlelr(Constants.Tank.TANK_KP, Constants.Tank.TANK_KI, Constants.Tank.TANK_KD);

    public void setVoltage(double leftV, double rightV) {
        Logger.recordOutput("Tank/leftPower", leftV);
        Logger.recordOutput("Tank/rightPower", rightV);
        io.setVoltage(leftV, rightV);
    }

    public void setPIDVoltage(double leftSetPoint, double rightSetPoint) {
        double pidL = tankPidControllerL.calculate(leftSetPoint * Constants.Tank.MAX_SPEED);
        double pidR = tankPidControllerR.calculate(rightSetPoint * Constants.Tank.MAX_SPEED);
        setVoltage(pidL, pidR);
        Logger.recordOutput("Tank/pidL", pidL);
        Logger.recordOutput("Tank/rightPower", rightR);
    }

    public Command tankCMD(CommandXboxController driver) {
        return run(() -> {
            setPIDVoltage(driver.getLeftY(), driver.getRightY());
        });
    }
}
