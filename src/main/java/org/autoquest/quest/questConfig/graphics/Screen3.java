package org.autoquest.quest.questConfig.graphics;

import org.autoquest.quest.view.StepFrame;
import org.autoquest.quest.questConfig.steps.Step3;
import org.autoquest.quest.questConfig.steps.Step4;
import org.autoquest.quest.view.Screen;

public class Screen3 {

    private static final Screen screen = new Screen()
            .setName("Screen3")
            .setDesc("Непрерывные действия")
            .setScreenSize(800, 1200);
    public static Screen getScreen() {

        screen.addCollection(new StepFrame(Step3.getInstance()).getStepFrame(100,100));
        screen.addCollection(new StepFrame(Step4.getInstance()).getStepFrame(450,100));


        screen.addImage("StepActive.svg");
        screen.addImage("StepDone.svg");

        return screen;
    }
}
