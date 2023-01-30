package org.autoquest.quest.view;

import org.autoquest.connections.MBParameter;

import java.util.ArrayList;

public class DynamicText implements IGraphic {
    private int x;
    private int y;
    private String name="";
    private String desc="";
    private MBParameter parameter;
    private String hint;
    private int height = 15;
    private int width = 15;

    @Override
    public void getContent(StringBuilder sb, int index) {
        sb.append("    <DynamicText>\n" +
                "      <BackColor />\n" +
                "      <BorderColor />\n" +
                "      <BorderWidth>0</BorderWidth>\n" +
                "      <ToolTip />\n" +
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
                "        <Name>Arial</Name>\n" +
                "        <Size>16</Size>\n" +
                "        <Bold>false</Bold>\n" +
                "        <Italic>false</Italic>\n" +
                "        <Underline>false</Underline>\n" +
                "      </Font>\n" +
                "      <Text>XX</Text>\n" +
                "      <HAlign>Left</HAlign>\n" +
                "      <VAlign>Top</VAlign>\n" +
                "      <WordWrap>false</WordWrap>\n" +
                "      <AutoSize>true</AutoSize>\n" +
                "      <BackColorOnHover />\n" +
                "      <BorderColorOnHover />\n" +
                "      <ForeColorOnHover />\n" +
                "      <UnderlineOnHover>false</UnderlineOnHover>\n" +
                "      <Action>None</Action>\n" +
                "      <ShowValue>ShowWithUnit</ShowValue>\n" +
                "      <InCnlNum>" + parameter.getChannelNumber() + "</InCnlNum>\n" +
                "      <CtrlCnlNum>0</CtrlCnlNum>\n" +
                "    </DynamicText>\n");
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

    public void setParameter(MBParameter parameter) {
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

    @Override
    public String getDesc() {
        return null;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}
