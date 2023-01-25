package org.autoquest.quest.view.graphics;

import org.autoquest.connections.Params;
import org.autoquest.quest.StepBypassButtons;
import org.autoquest.quest.steps.Step1;
import org.autoquest.quest.steps.Step2;
import org.autoquest.quest.steps.Step3;
import org.autoquest.quest.view.ButtonTest;
import org.autoquest.quest.view.Screen;
import org.autoquest.quest.view.StatisImage;

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
//        screen.addCollection(new StepBypassButtons(Step3.getInstance()).getButtons());


        ButtonTest startButton = new ButtonTest();
        startButton.setName("Старт");
        startButton.setDesc("SB_Desc");
        startButton.setHint("Start");
        startButton.setSize(25, 70);
        startButton.setParameterControl(Params.START);
        startButton.setParameterStatus(Params.START_FB);
        startButton.setPosition(1042, 30);
        screen.addElement(startButton);
        
        ButtonTest stopButton = new ButtonTest();
        stopButton.setName("Стоп");
        stopButton.setDesc("SB_Desc");
        stopButton.setHint("Stop");
        stopButton.setSize(25, 70);
        stopButton.setParameterControl(Params.ABORT);
        stopButton.setParameterStatus(Params.ABORT_FB);
        stopButton.setPosition(1042, 30);
        screen.addElement(stopButton);

        screen.addImage("key.svg");
        screen.addImage("QuestScreenWB.jpg");
        return screen;
    }
}
