package org.autoquest.quest.view;



import org.autoquest.connections.MBParameter;

import java.util.ArrayList;

public class ButtonQEnd implements IGraphic {

    private int x;
    private int y;
    private String name;
    private String desc = "";
    private MBParameter  parameterStatus;
    private String hint;
    private int height = 149;
    private int width = 49;

    @Override
    public void getContent(StringBuilder sb, int index) {
        sb.append("<basic:Button>\n" +
                "      <BackColor>Transparent</BackColor>\n" +
                "      <BorderColor>Transparent</BorderColor>\n" +
                "      <BorderWidth>0</BorderWidth>\n" +
                "      <ToolTip>" + hint + "</ToolTip>\\n\n" +
                "      <ID>" + index + "</ID>\n" +
                "      <Name>" + name + index + "</Name>\n" +
                "      <Location>\n" +
                "        <X>" + x + "</X>\n" +
                "        <Y>" + y + "</Y>\n" +
                "      </Location>\n" +
                "      <Size>\n" +
                "        <Width>" + width + "</Width>\n" +
                "        <Height>" + height + "</Height>\n" +
                "      </Size>\n" +
                "      <ZIndex>1</ZIndex>\n" +
                "      <ForeColor>DarkBlue</ForeColor>\n" +
                "<Font>\n" +
                "<Name>Arial</Name>\n" +
                "<Size>18</Size>\n" +
                "<Bold>true</Bold>\n" +
                "<Italic>false</Italic>\n" +
                "<Underline>false</Underline>\n" +
                "</Font>\n" +
                "      <ImageName/>\n" +
                "      <ImageSize>\n" +
                "        <Width>30</Width>\n" +
                "        <Height>30</Height>\n" +
                "      </ImageSize>\n" +
                "      <Text>" + name + "</Text>\n" +
                "      <Action>None</Action>\n" +
                "      <BoundProperty>Visible</BoundProperty>\n" +
                "      <InCnlNum>" + parameterStatus.getChannelNumber() + "</InCnlNum>\n" +
                "      <CtrlCnlNum>0</CtrlCnlNum>\n" +
                "    </basic:Button>\n");
    }

    @Override
    public ArrayList<IGraphic> getElements() {
        return null;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setParameterStatus(MBParameter  parameter) {
        this.parameterStatus = parameter;
    }

    public void setName(String name) {
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
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
