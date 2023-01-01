package org.autoquest.quest.view;

import org.autoquest.connections.MBParameter;
import org.autoquest.connections.units.IParameter;

import java.util.ArrayList;

public class Led implements IGraphic {

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
        sb.append(" <basic:Led>\n" +
                "      <BackColor>Silver</BackColor>\n" +
                "      <BorderColor>Black</BorderColor>\n" +
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
                "      <BorderOpacity>30</BorderOpacity>\n" +
                "      <Action>None</Action>\n" +
                "      <Conditions>\n" +
                "        <Condition>\n" +
                "          <CompareOperator1>GreaterThan</CompareOperator1>\n" +
                "          <CompareArgument1>0</CompareArgument1>\n" +
                "          <CompareOperator2>LessThan</CompareOperator2>\n" +
                "          <CompareArgument2>0</CompareArgument2>\n" +
                "          <LogicalOperator>None</LogicalOperator>\n" +
                "          <Color>Green</Color>\n" +
                "        </Condition>\n" +
                "      </Conditions>\n" +
                "      <InCnlNum>" + parameter.getChannelNumber() + "</InCnlNum>\n" +
                "      <CtrlCnlNum>0</CtrlCnlNum>\n" +
                "    </basic:Led>\n");
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
