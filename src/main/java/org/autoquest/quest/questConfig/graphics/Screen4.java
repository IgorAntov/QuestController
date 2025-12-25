package org.autoquest.quest.questConfig.graphics;

import org.autoquest.quest.questConfig.timealarm.secX1.Timer1;
import org.autoquest.quest.questConfig.timealarm.secX1.TimerAction1;
import org.autoquest.quest.questConfig.timealarm.secX2.Timer2;
import org.autoquest.quest.questConfig.timealarm.secX2.TimerAction2;
import org.autoquest.quest.view.Screen;
import org.autoquest.quest.view.StepFrame;
import org.autoquest.quest.view.TimerLabel;

public class Screen4 {

    private static final Screen screen = new Screen()
            .setName("Screen4")
            .setDesc("Подсказки")
            .setScreenSize(800, 1200);
    public static Screen getScreen() {

        screen.addCollection(new StepFrame(Timer1.getInstance()).getStepFrame(100,100));
        screen.addCollection(new StepFrame(TimerAction1.getInstance()).getStepFrame(550,100));

        screen.addCollection(new StepFrame(Timer2.getInstance()).getStepFrame(100,300));
        screen.addCollection(new StepFrame(TimerAction2.getInstance()).getStepFrame(550,300));

        screen.addCollection(new TimerLabel().getTimerLabel(10, 50));

        screen.addImage("StepActive.svg");
        screen.addImage("StepDone.svg");

        return screen;
    }
}
