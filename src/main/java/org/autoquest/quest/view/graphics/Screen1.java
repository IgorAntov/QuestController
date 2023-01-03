package org.autoquest.quest.view.graphics;

import org.autoquest.quest.StepFrame;
import org.autoquest.quest.steps.Step1;
import org.autoquest.quest.steps.Step2;
import org.autoquest.quest.steps.Step3;
import org.autoquest.quest.view.Screen;

public class Screen1 {

    private static final Screen screen1 = new Screen()
            .setName("Screen1")
            .setDesc("Комната 1")
            .setScreenSize(800, 1200);
    public static Screen getScreen() {
        screen1.addCollection(new StepFrame(Step1.getInstance()).getStepFrame(100,100));
        screen1.addCollection(new StepFrame(Step2.getInstance()).getStepFrame(450,100));
        screen1.addCollection(new StepFrame(Step3.getInstance()).getStepFrame(800,100));

        screen1.addImage("key.svg");
        screen1.addImage("StepActive.svg");
        screen1.addImage("StepDone.svg");

        return screen1;
    }
}
