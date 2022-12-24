package org.autoquest.quest.view;

import org.autoquest.QuestName;
import org.autoquest.quest.view.graphics.Screen1;

public class GraphicConfig {

    private static final Graphic graphic = Graphic.getInstance();

    public static void build() {
        Group group1 = new Group("Group1", "DescGroup");
        graphic.addGroup(group1);
        group1.addScreen(Screen1.getScreen());

    }

  //  public static StringBuilder getXMLContent(StringBuilder sb, int index) {
  //      StringBuilder sbx = new StringBuilder();
  //      sbx.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
  //              "<SchemeView title=\"\" xmlns:basic=\"urn:rapidscada:scheme:basic\">\n");
  //      build();
  //      graphic.getContent(sbx, index);
  //      sbx.append("</SchemeView>");
  //      return sbx;
  //  }

}
