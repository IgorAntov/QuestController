package org.autoquest.quest.view;

import org.autoquest.connections.units.IParameter;

import java.util.ArrayList;

public class DynamicPicture implements IGraphic {

    private int x;
    private int y;
    private String name="";
    private String desc="";
    private IParameter parameter;
    private String hint = "";
    private String image = "";
    private int height = 43;
    private int width = 43;
    private String action = "NotEqual";

    @Override
    public void getContent(StringBuilder sb, int index) {
        sb.append("<DynamicPicture>\n" +
                "      <BackColor />\n" +
                "      <BorderColor>Gray</BorderColor>\n" +
                "      <BorderWidth>0</BorderWidth>\n" +
                "      <ToolTip>" + hint + "</ToolTip>\n" +
                "      <ID>24</ID>\n" +
                "      <Name>" + name + "</Name>\n" +
                "      <Location>\n" +
                "        <X>" + x + "</X>\n" +
                "        <Y>" + y + "</Y>\n" +
                "      </Location>\n" +
                "      <Size>\n" +
                "        <Width>" + width + "</Width>\n" +
                "        <Height>" + height + "</Height>\n" +
                "      </Size>\n" +
                "      <ZIndex>1</ZIndex>\n" +
                "      <ImageName>" + image + "</ImageName>\n" +
                "      <ImageStretch>None</ImageStretch>\n" +
                "      <BackColorOnHover />\n" +
                "      <BorderColorOnHover />\n" +
                "      <ImageOnHoverName />\n" +
                "      <Action>None</Action>\n" +
                "      <Conditions>\n" +
                "        <Condition>\n" +
                "          <CompareOperator1>" + action + "</CompareOperator1>\n" +
                "          <CompareArgument1>0</CompareArgument1>\n" +
                "          <CompareOperator2>LessThan</CompareOperator2>\n" +
                "          <CompareArgument2>0</CompareArgument2>\n" +
                "          <LogicalOperator>None</LogicalOperator>\n" +
                "          <ImageName />\n" +
                "        </Condition>\n" +
                "      </Conditions>\n" +
                "      <InCnlNum>" + parameter.getChannelNumber() + "</InCnlNum>\n" +
                "      <CtrlCnlNum>0</CtrlCnlNum>\n" +
                "    </DynamicPicture>\n");
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

    @Override
    public String getDesc() {
        return null;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setVisibleOnFalse() {
        this.action = "Equal";
    }
}
