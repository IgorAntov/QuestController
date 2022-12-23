package org.autoquest.quest.view;

import org.autoquest.connections.units.IParameter;

import java.util.ArrayList;

public class Led implements IGraphic {

    private int x;
    private int y;
    private String name;
    private IParameter parameter;
    private String hint;
    private int height = 20;
    private int width = 20;

    @Override
    public StringBuilder getContent(StringBuilder sb, int index) {
        return null;
    }

    @Override
    public ArrayList<IGraphic> getElements() {
        return null;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private void setName(String name) {
        this.name = name;
    }

    public void setParameter(IParameter parameter) {
        this.parameter = parameter;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public void setSize(int height, int width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public String getName() {
        return name;
    }
}