package frc.robot.input;

import frc.robot.lib.input.Layout;
import frc.robot.lib.input.LayoutToggler;
import frc.robot.lib.input.ShuffleLayoutChooser;
import frc.robot.lib.input.models.LogitechGamepadF310;

public class OperatorInputs extends ShuffleLayoutChooser {

    public OperatorInputs() {
        final var defaultLayout = add(new Layout("Default"));
        final var logitech = new LogitechGamepadF310(0);

        defaultLayout.assign(Keys.Button.Hello, logitech.a);

        final var printFooLayout = new Layout("Print Foo");
        printFooLayout.assign(Keys.DebugButton.PrintFoo, logitech.a);
        final var printBarLayout = new Layout("Print Bar");
        printBarLayout.assign(Keys.DebugButton.PrintBar, logitech.b);

        final var printLayouts = new LayoutToggler(printFooLayout, printBarLayout);
        defaultLayout.assign(Keys.DebugButton.PrintFoo, printLayouts.button(Keys.DebugButton.PrintFoo));
        defaultLayout.assign(Keys.DebugButton.PrintBar, printLayouts.button(Keys.DebugButton.PrintBar));

        logitech.start.whenPressed(printLayouts::toggle);
    }
}
