package org.autoquest.quest.view.graphics;

import org.autoquest.quest.StepFrame;
import org.autoquest.quest.steps.Step1;
import org.autoquest.quest.steps.Step2;
import org.autoquest.quest.view.Screen;

public class Screen2 {

    private static final Screen screen = new Screen()
            .setName("Screen2")
            .setDesc("Шаги квеста")
            .setScreenSize(800, 1200);
    public static Screen getScreen() {
        screen.addCollection(new StepFrame(Step1.getInstance()).getStepFrame(100,100));
        screen.addCollection(new StepFrame(Step2.getInstance()).getStepFrame(450,100));
//        screen.addCollection(new StepFrame(Step3.getInstance()).getStepFrame(800,100));

        screen.addImage("StepActive.svg");
        screen.addImage("StepDone.svg");

        return screen;
    }
}
