package org.autoquest.quest.view.graphics;

import org.autoquest.quest.StepBypassButtons;
import org.autoquest.quest.steps.Step1;
import org.autoquest.quest.steps.Step2;
import org.autoquest.quest.steps.Step3;
import org.autoquest.quest.view.Screen;
import org.autoquest.quest.view.StatisImage;

public class Screen2 {

    private static final Screen screen2 = new Screen()
            .setName("Screen2")
            .setDesc("Комната 2")
            .setScreenSize(800, 1200);
    public static Screen getScreen() {
        StatisImage statisImage = new StatisImage("QuestScreenWB.jpg");
        statisImage.setImageSize(800, 1000);
        screen2.addElement(statisImage);
        screen2.addCollection(new StepBypassButtons(Step1.getInstance()).getButtons());
        screen2.addCollection(new StepBypassButtons(Step2.getInstance()).getButtons());
        screen2.addCollection(new StepBypassButtons(Step3.getInstance()).getButtons());

        screen2.addImage("key.svg");
        screen2.addImage("QuestScreenWB.jpg");
        return screen2;
    }
}
