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
    public void getContent(StringBuilder sb, int index) {

    }

    @Override
    public ArrayList<IGraphic> getElements() {
        return groups;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDesc() {
        return null;
    }

    public void addGroup(IGraphic group) {
        groups.add(group);
    }

}
