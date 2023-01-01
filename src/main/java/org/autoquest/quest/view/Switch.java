package org.autoquest.quest.view;

import org.autoquest.connections.MBParameter;

import java.util.ArrayList;

public class Switch  implements IGraphic {

    private int x;
    private int y;
    private String name;
    private String desc = "";
    private MBParameter parameterControl;
    private MBParameter parameterStatus;
    private String hint;
    private int height = 15;
    private int width = 30;

    @Override
    public void getContent(StringBuilder sb, int index) {
        sb.append("    <basic:Toggle>\n" +
                "      <BackColor>Status</BackColor>\n" +
                "      <BorderColor>Status</BorderColor>\n" +
                "      <BorderWidth>2</BorderWidth>\n" +
                "      <ToolTip>" + hint + "</ToolTip>\\n\n" +
                "      <ID>" + index + "</ID>\n" +
                "      <Name>" + name + "</Name>\n" +
                "     <Location>\n" +
                "        <X>" + x + "</X>\n" +
                "        <Y>" + y + "</Y>\n" +
                "      </Location>\n" +
                "      <Size>\n" +
                "        <Width>" + width + "</Width>\n" +
                "        <Height>" + height + "</Height>\n" +
                "      </Size>\n" +
                "      <ZIndex>0</ZIndex>\n" +
                "      <LeverColor>White</LeverColor>\n" +
                "      <Padding>0</Padding>\n" +
                "      <Action>SendCommandNow</Action>\n" +
                "      <InCnlNum>" + parameterStatus.getChannelNumber() + "</InCnlNum>\n" +
                "      <CtrlCnlNum>" + parameterControl.getChannelNumber() + "</CtrlCnlNum>\n" +
                "    </basic:Toggle>\n");
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

    public void setParameterControl(MBParameter parameter) {
        this.parameterControl = parameter;
    }

    public void setParameterStatus(MBParameter parameter) {
        this.parameterStatus = parameter;
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
