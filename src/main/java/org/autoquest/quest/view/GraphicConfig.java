package org.autoquest.quest.view;

import org.autoquest.QuestName;
import org.autoquest.quest.view.graphics.Screen1;
import org.autoquest.quest.view.graphics.Screen2;

public class GraphicConfig {

    private static final Graphic graphic = Graphic.getInstance();

    public static void build() {
        Group group1 = new Group("Group1", "Квест");
        graphic.addGroup(group1);
        group1.addScreen(Screen1.getScreen());
        group1.addScreen(Screen2.getScreen());
    }
}
