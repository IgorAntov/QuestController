package org.autoquest.quest.view.graphics;

import org.autoquest.quest.view.Graphic;
import org.autoquest.quest.view.Group;

public class GraphicConfig {

    private static final Graphic graphic = Graphic.getInstance();

    public static void build() {
        Group group1 = new Group("Group1", "Квест");
        graphic.addGroup(group1);
        group1.addScreen(Screen1.getScreen());
        group1.addScreen(Screen2.getScreen());
        group1.addScreen(Screen3.getScreen());
    }
}
