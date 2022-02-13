package frc.robot.lib.controls;

import java.util.function.BooleanSupplier;

public class Button extends edu.wpi.first.wpilibj2.command.button.Button implements ButtonInput {
    public Button(final BooleanSupplier supplier) {
        super(supplier);
    }
}
