package org.autoquest.quest.view;

import java.util.ArrayList;

public class Screen {

    private String name = "";
    private String desc = "";
    private int height;
    private int width;

    private final ArrayList<IGraphic> elements = new ArrayList<>();

    private final ArrayList<String> images = new ArrayList<>();

    public void getContent(StringBuilder sb, int index) {
        sb.append("<Scheme>\n" +
                "    <Version>5.3.1.1</Version>\n" +
                "    <Size>\n" +
                "      <Width>" + width + "</Width>\n" +
                "      <Height>" + height + "</Height>\n" +
                "    </Size>\n" +
                "    <BackColor>White</BackColor>\n" +
                "    <BackImageName />\n" +
                "    <Font>\n" +
                "      <Name>Arial</Name>\n" +
                "      <Size>12</Size>\n" +
                "      <Bold>false</Bold>\n" +
                "      <Italic>false</Italic>\n" +
                "      <Underline>false</Underline>\n" +
                "    </Font>\n" +
                "    <ForeColor>Black</ForeColor>\n" +
                "    <Title />\n" +
                "    <CnlFilter />\n" +
                "  </Scheme>\n");
    }


    public ArrayList<IGraphic> getElements() {
        return elements;
    }

    public void addElement(IGraphic screen) {
        elements.add(screen);
    }

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

    public String getDesc() {
        return desc;
    }

    public Screen setDesc(String desc) {
        this.desc = desc;
        return this;
    }
    public ArrayList<String> getImages() {
        return images;
    }

    public void addImage(String name) {
        images.add(name);
    }

    public void addCollection(ArrayList<IGraphic> frameContent) {
      elements.addAll(frameContent);
    }
}
