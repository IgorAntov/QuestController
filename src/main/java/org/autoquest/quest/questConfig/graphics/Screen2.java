package org.autoquest.quest.questConfig.graphics;

import org.autoquest.quest.view.StepFrame;
import org.autoquest.quest.questConfig.steps.Step1;
import org.autoquest.quest.questConfig.steps.Step2;
import org.autoquest.quest.view.Screen;

public class Screen2 {

    private static final Screen screen = new Screen()
            .setName("Screen2")
            .setDesc("Шаги квеста")
            .setScreenSize(800, 1200);
    public static Screen getScreen() {
        screen.addCollection(new StepFrame(Step1.getInstance()).getStepFrame(100,100));
        screen.addCollection(new StepFrame(Step2.getInstance()).getStepFrame(550,100)); //450,100

        screen.addImage("StepActive.svg");
        screen.addImage("StepDone.svg");

        return screen;
    }
}
