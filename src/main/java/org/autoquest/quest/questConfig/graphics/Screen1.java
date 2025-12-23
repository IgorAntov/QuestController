package org.autoquest.quest.questConfig.graphics;

import org.autoquest.quest.view.*;
import org.autoquest.quest.questConfig.steps.Step1;
import org.autoquest.quest.questConfig.steps.Step2;

public class Screen1 {

    private static final Screen screen = new Screen()
            .setName("Screen1")
            .setDesc("Комната 1")
            .setScreenSize(800, 1200);
    public static Screen getScreen() {
        StatisImage statisImage = new StatisImage("QuestScreenWB.jpg");
        statisImage.setImageSize(800, 1000);
        screen.addElement(statisImage);

        Step1.getInstance().getTransitionByName("Transition1S1").setBypassButtonXY(95, 495);
        Step1.getInstance().getTransitionByName("Transition2S1").setBypassButtonXY(715, 280);
        screen.addCollection(new StepBypassButtons(Step1.getInstance()).getButtons());

        Step2.getInstance().getTransitionByName("Transition1S2").setBypassButtonXY(690, 555);
        Step2.getInstance().getTransitionByName("Transition2S2").setBypassButtonXY(490, 475);
        screen.addCollection(new StepBypassButtons(Step2.getInstance()).getButtons());

        screen.addCollection(new ControlBar("Name", "qt_icon.svg").getElements(1020, 5));
        screen.addCollection(new TimerLabel().getTimerLabel(1060, 110));

        screen.addImage("qt_icon.svg");
        screen.addImage("key.svg");
        screen.addImage("QuestScreenWB.jpg");
        return screen;
    }
}
