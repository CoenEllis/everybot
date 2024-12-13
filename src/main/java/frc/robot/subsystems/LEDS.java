package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// stuff stuff stuff stuff stuff stuff
public class LEDS extends SubsystemBase {
    AddressableLED leds;
    AddressableLEDBuffer buffer;

    // gives the information to the LEDs
    public LEDs(int portNum, int length) {
        this.leds = new AddressableLED(portNum);
        this.buffer = new AddressableLEDBuffer(length);
        leds.setLength(buffer.getLength());
        leds.setData(buffer);
        leds.start();
    }

    // colors are being set super cool
    public void setColor(Color colors) {
        for (var i = 0; i < buffer.getLength(); i++) {
            buffer.setLED(i, colors);
        }
        leds.setData(buffer);
    }

    // led color is based off of alliance color
    public Command setAllianceColor() {
        return Commands.run(() -> {
            Color color = Color.kYellow;
            if (DriverStation.getAlliance().isPresent()) {
                if (DriverStation.getAlliance().get() == Alliance.Red) {
                    color = Color.kRed;
                } else {
                    color = color.kBlue;
                }
            }
        }, this);
    }

    public Command call() {
        Color callClr = Color.kBlue;
        return Commands.run(() -> setColor(callClr));
    }

    // hatch led connection
    public Command hatchTrue() {
        Color clr = Color.kLavendar;
        return Commands.run(() -> setColor(clr));
    }
}
