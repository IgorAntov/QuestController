package org.autoquest.quest.view.graphics;

import org.autoquest.connections.Params;
import org.autoquest.quest.view.*;
import org.autoquest.quest.steps.Step1;
import org.autoquest.quest.steps.Step2;

public class Screen1 {

    private static final Screen screen = new Screen()
            .setName("Screen1")
            .setDesc("Комната 1")
            .setScreenSize(800, 1200);
    public static Screen getScreen() {
        StatisImage statisImage = new StatisImage("QuestScreenWB.jpg");
        statisImage.setImageSize(800, 1000);
        screen.addElement(statisImage);

        screen.addCollection(new StepBypassButtons(Step1.getInstance()).getButtons());
        screen.addCollection(new StepBypassButtons(Step2.getInstance()).getButtons());

        screen.addCollection(new ControlBar("Name", "qt_icon.svg").getElements(1020, 5));
        screen.addCollection(new TimerLabel().getTimerLabel(1060, 110));

        screen.addImage("qt_icon.svg");
        screen.addImage("key.svg");
        screen.addImage("QuestScreenWB.jpg");
        return screen;
    }
}
