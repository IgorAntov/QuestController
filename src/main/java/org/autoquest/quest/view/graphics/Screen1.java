package org.autoquest.quest.view.graphics;

import org.autoquest.connections.Params;
import org.autoquest.connections.adapters.SimulatorParams;
import org.autoquest.quest.view.Button;
import org.autoquest.quest.view.Led;
import org.autoquest.quest.view.Screen;
import org.autoquest.quest.view.Switch;

public class Screen1 {

    private static final Screen screen1 = new Screen()
            .setName("Screen1")
            .setDesc("ScreenDesc")
            .setScreenSize(600, 600);
    public static Screen getScreen() {
        Led led1 = new Led();
        led1.setPosition(50, 50);
        led1.setParameter(Params.START);
        led1.setHint("Всплывающая подсказка");
        screen1.addElement(led1);

        Switch switch1 = new Switch();
        switch1.setPosition(150, 50);
        switch1.setParameterControl(SimulatorParams.KEY1);
        switch1.setParameterStatus(Params.START);
        switch1.setHint("Popup Message For Switch");
        screen1.addElement(switch1);

        Button button1 = new Button();
        button1.setPosition(150, 150);
        button1.setParameterControl(SimulatorParams.KEY1);
        button1.setParameterStatus(Params.START);
        button1.setHint("Popup Message For Button");
        screen1.addElement(button1);

        screen1.addImage("key.svg");

        return screen1;
    }
}
