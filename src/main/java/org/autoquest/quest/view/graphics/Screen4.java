package org.autoquest.quest.view.graphics;

import org.autoquest.quest.steps.Step3;
import org.autoquest.quest.steps.Step4;
import org.autoquest.quest.timealarm.secX1.Timer1;
import org.autoquest.quest.timealarm.secX1.TimerAction1;
import org.autoquest.quest.view.Screen;
import org.autoquest.quest.view.StepFrame;

public class Screen4 {

    private static final Screen screen = new Screen()
            .setName("Screen4")
            .setDesc("Подсказки")
            .setScreenSize(800, 1200);
    public static Screen getScreen() {

        screen.addCollection(new StepFrame(Timer1.getInstance()).getStepFrame(100,100));
        screen.addCollection(new StepFrame(TimerAction1.getInstance()).getStepFrame(450,100));

        screen.addImage("StepActive.svg");
        screen.addImage("StepDone.svg");

        return screen;
    }
}
