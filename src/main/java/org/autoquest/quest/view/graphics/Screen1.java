package org.autoquest.quest.view.graphics;

import org.autoquest.connections.Params;
import org.autoquest.connections.adapters.SimulatorParams;
import org.autoquest.quest.StepTemplate;
import org.autoquest.quest.steps.Step1;
import org.autoquest.quest.view.ButtonBypass;
import org.autoquest.quest.view.Led;
import org.autoquest.quest.view.Screen;
import org.autoquest.quest.view.Switch;

public class Screen1 {

    private static final Screen screen1 = new Screen()
            .setName("Screen1")
            .setDesc("Комната 1")
            .setScreenSize(800, 1000);
    public static Screen getScreen() {

        screen1.addFrame(Step1.getStepFrame(100,100));
        screen1.addFrame(Step1.getStepFrame(450,100));

        screen1.addImage("key.svg");
        screen1.addImage("StepActive.svg");
        screen1.addImage("StepDone.svg");

        return screen1;
    }
}
