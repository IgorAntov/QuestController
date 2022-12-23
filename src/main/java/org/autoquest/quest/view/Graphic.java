package org.autoquest.quest.view;

import java.util.ArrayList;

public class Graphic implements IGraphic {
    private static final Graphic INSTANCE = new Graphic();
    public static Graphic getInstance() {
        return INSTANCE;
    }

    private Graphic() {}
    public final ArrayList<IGraphic> groups = new ArrayList<>();

    @Override
    public StringBuilder getContent(StringBuilder sb, int index) {
        return null;
    }

    @Override
    public ArrayList<IGraphic> getElements() {
        return groups;
    }

    @Override
    public String getName() {
        return null;
    }

    public StringBuilder getGraphicsXML(StringBuilder sb, int index){
        return getContent(sb, index);
    }

    public void addGroup(IGraphic group) {
        groups.add(group);
    }

}
