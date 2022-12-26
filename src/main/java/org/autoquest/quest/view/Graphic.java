package org.autoquest.quest.view;

import java.util.ArrayList;

public class Graphic{
    private static final Graphic INSTANCE = new Graphic();
    public static Graphic getInstance() {
        return INSTANCE;
    }

    private Graphic() {}
    public final ArrayList<Group> groups = new ArrayList<>();


    public void getContent(StringBuilder sb, int index) {

    }

    public ArrayList<Group> getElements() {
        return groups;
    }

    public String getName() {
        return null;
    }


    public String getDesc() {
        return null;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

}
