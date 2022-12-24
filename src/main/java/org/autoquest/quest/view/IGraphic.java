package org.autoquest.quest.view;

import java.util.ArrayList;

public interface IGraphic {

    void getContent(StringBuilder sb, int index);
    ArrayList<IGraphic> getElements();
    String getName();
    String getDesc();
}
