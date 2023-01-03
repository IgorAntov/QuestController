package org.autoquest.quest.view;

import org.autoquest.connections.MBParameter;

import java.util.ArrayList;

public class StatisImage implements IGraphic {


    private int x = 0;
    private int y = 0;
    private String name="";
    private String desc="";
    private String hint="";
    private int height = 15;
    private int width = 15;
    private final String imageFileName;

    public StatisImage(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    @Override
    public void getContent(StringBuilder sb, int index) {
        sb.append("    <StaticPicture>\n" +
                "      <BackColor />\n" +
                "      <BorderColor>Gray</BorderColor>\n" +
                "      <BorderWidth>1</BorderWidth>\n" +
                "      <ToolTip>" + hint + "</ToolTip>\n" +
                "      <ID>" + index + "</ID>\n" +
                "      <Name />\n" +
                "      <Location>\n" +
                "        <X>" + x + "</X>\n" +
                "        <Y>" + y + "</Y>\n" +
                "      </Location>\n" +
                "      <Size>\n" +
                "        <Width>" + width + "</Width>\n" +
                "        <Height>" + height + "</Height>\n" +
                "      </Size>\n" +
                "      <ZIndex>0</ZIndex>\n" +
                "      <ImageName>" + imageFileName + "</ImageName>\n" +
                "      <ImageStretch>Fill</ImageStretch>\n" +
                "    </StaticPicture>");
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

    @Override
    public String getDesc() {
        return null;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImageSize(int height, int width) {
        this.height = height;
        this.width = width;
    }

}
