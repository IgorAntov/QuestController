package org.autoquest.quest.view.graphics;

import org.autoquest.connections.Params;
import org.autoquest.connections.adapters.SimulatorParams;
import org.autoquest.quest.view.Led;
import org.autoquest.quest.view.Screen;
import org.autoquest.quest.view.Switch;

public class Screen2 {

    private static final Screen screen1 = new Screen()
            .setName("Screen2")
            .setDesc("Комната 2")
            .setScreenSize(600, 600);
    public static Screen getScreen() {

        return screen1;
    }
}
