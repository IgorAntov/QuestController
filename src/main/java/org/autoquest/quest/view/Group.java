package org.autoquest.quest.view;

import java.util.ArrayList;

public class Group {

    private final String name;

    private final String desc;

    private final ArrayList<Screen> screens = new ArrayList<>();

    public Group(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public void getContent(StringBuilder sb, int index) {
        sb.append("<Interface>\n" +
                "                    <ItfID>" + index + "</ItfID>\n" +
                "                    <Name>" + name + "/</Name>\n" +
                "                    <Descr>" + desc + "</Descr>\n" +
                "                    <Hidden>false</Hidden>\n" +
                "                    <ObjNum xsi:nil=\"true\"/>\n" +
                "                    </Interface>\n");
    }

    public ArrayList<Screen> getElements() {
        return screens;
    }

    public void addScreen(Screen screen) {
        screens.add(screen);
    }
    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return desc;
    }

}
