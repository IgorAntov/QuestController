package org.autoquest.quest.view;

import java.util.ArrayList;

public class StaticTextFrame implements IGraphic {

    private int x;
    private int y;
    private String name = "";
    private String desc = "";
    private String hint = "";
    private int height = 270;
    private int width = 230;

    @Override
    public void getContent(StringBuilder sb, int index) {
        sb.append("    <StaticText>\n" +
                "      <BackColor>WhiteSmoke</BackColor>\n" +
                "      <BorderColor>DodgerBlue</BorderColor>\n" +
                "      <BorderWidth>1</BorderWidth>\n" +
                "      <ToolTip>" + hint + "</ToolTip>\n" +
                "      <ID>" + index + "</ID>\n" +
                "      <Name>" + name + "</Name>\n" +
                "      <Location>\n" +
                "        <X>" + x + "</X>\n" +
                "        <Y>" + y + "</Y>\n" +
                "      </Location>\n" +
                "      <Size>\n" +
                "        <Width>" + width + "</Width>\n" +
                "        <Height>" + height + "</Height>\n" +
                "      </Size>\n" +
                "      <ZIndex>0</ZIndex>\n" +
                "      <ForeColor />\n" +
                "      <Font>\n" +
                "        <Name>STIX</Name>\n" +
                "        <Size>20</Size>\n" +
                "        <Bold>false</Bold>\n" +
                "        <Italic>false</Italic>\n" +
                "        <Underline>false</Underline>\n" +
                "      </Font>\n" +
                "      <Text>" + desc + "</Text>\n" +
                "      <HAlign>Center</HAlign>\n" +
                "      <VAlign>Top</VAlign>\n" +
                "      <WordWrap>false</WordWrap>\n" +
                "      <AutoSize>false</AutoSize>\n" +
                "    </StaticText>\n");
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
