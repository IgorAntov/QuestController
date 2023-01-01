package org.autoquest.quest.view.graphics;

import org.autoquest.connections.Params;
import org.autoquest.connections.adapters.SimulatorParams;
import org.autoquest.quest.StepTemplate;
import org.autoquest.quest.steps.Step1;
import org.autoquest.quest.view.ButtonBypass;
import org.autoquest.quest.view.Led;
import org.autoquest.quest.view.Screen;
import org.autoquest.quest.view.Switch;

public class Screen1 {

    private static final Screen screen1 = new Screen()
            .setName("Screen1")
            .setDesc("Комната 1")
            .setScreenSize(800, 1000);
    public static Screen getScreen() {

//        Switch switch1 = new Switch();
//        switch1.setPosition(150, 50);
//        switch1.setParameterControl(SimulatorParams.KEY1);
//        switch1.setParameterStatus(Params.START);
//        switch1.setHint("Popup Message For Switch");
//        screen1.addElement(switch1);

//        ButtonBypass buttonBypass1 = new ButtonBypass();
//        buttonBypass1.setPosition(150, 150);
//        buttonBypass1.setParameterControl(SimulatorParams.KEY1);
//        buttonBypass1.setParameterStatus(Params.START);
//        buttonBypass1.setHint("Popup Message For Button");
//        screen1.addElement(buttonBypass1);

        screen1.addFrame(Step1.getStepFrame(100,100));
        screen1.addFrame(Step1.getStepFrame(450,100));

        screen1.addImage("key.svg");
        screen1.addImage("StepActive.svg");
        screen1.addImage("StepDone.svg");

        return screen1;
    }
}
