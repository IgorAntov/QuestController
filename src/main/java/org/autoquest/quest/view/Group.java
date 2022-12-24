package org.autoquest.quest.view;

import java.util.ArrayList;

public class Group implements IGraphic{

    private final String name;

    private final String desc;

    private final ArrayList<IGraphic> screens = new ArrayList<>();

    public Group(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    @Override
    public void getContent(StringBuilder sb, int index) {
        sb.append("<Interface>\n" +
                "                    <ItfID>" + index + "</ItfID>\n" +
                "                    <Name>" + name + "/</Name>\n" +
                "                    <Descr>" + desc + "</Descr>\n" +
                "                    <Hidden>false</Hidden>\n" +
                "                    <ObjNum xsi:nil=\"true\"/>\n" +
                "                    </Interface>\n");
    }

    @Override
    public ArrayList<IGraphic> getElements() {
        return screens;
    }

    public void addScreen(IGraphic screen) {
        screens.add(screen);
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDesc() {
        return desc;
    }

}
