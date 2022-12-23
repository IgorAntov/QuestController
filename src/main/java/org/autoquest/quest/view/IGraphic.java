package org.autoquest.quest.view;

import java.util.ArrayList;

public interface IGraphic {

    StringBuilder getContent(StringBuilder sb, int index);
    ArrayList<IGraphic> getElements();
    String getName();
}
