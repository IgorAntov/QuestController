package org.autoquest.quest.view;

import java.util.ArrayList;

public class Screen implements IGraphic {

    private String name = "";
    private int height;
    private int width;

    private final ArrayList<IGraphic> elements = new ArrayList<>();

    @Override
    public StringBuilder getContent(StringBuilder sb, int index) {
        return null;
    }

    @Override
    public ArrayList<IGraphic> getElements() {
        return elements;
    }

    public void addElement(IGraphic screen) {
        elements.add(screen);
    }
    @Override
    public String getName() {
        return name;
    }

    public Screen setName(String name) {
        this.name = name;
        return this;
    }

    public Screen setScreenSize(int height, int width) {
        this.height = height;
        this.width = width;
        return this;
    }


}
