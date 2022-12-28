package org.autoquest.quest.view;

import org.autoquest.connections.units.IParameter;

import java.util.ArrayList;

public class ButtonBypass implements IGraphic {

    private int x;
    private int y;
    private String name;
    private String desc = "";
    private IParameter parameterControl;
    private IParameter parameterStatus;
    private String hint;
    private int height = 35;
    private int width = 88;

    @Override
    public void getContent(StringBuilder sb, int index) {
        sb.append("<basic:Button>\n" +
                "      <BackColor>Transparent</BackColor>\n" +
                "      <BorderColor />\n" +
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
                "      <ForeColor>Yellow</ForeColor>\n" +
                "      <ImageName>key.svg</ImageName>\n" +
                "      <ImageSize>\n" +
                "        <Width>30</Width>\n" +
                "        <Height>30</Height>\n" +
                "      </ImageSize>\n" +
                "      <Text>Обход</Text>\n" +
                "      <Action>SendCommand</Action>\n" +
                "      <BoundProperty>Visible</BoundProperty>\n" +
                "      <InCnlNum>" + parameterStatus.getChannelNumber() + "</InCnlNum>\n" +
                "      <CtrlCnlNum>" + parameterControl.getChannelNumber() + "</CtrlCnlNum>\n" +
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

    private void setName(String name) {
        this.name = name;
    }

    public void setParameterControl(IParameter parameter) {
        this.parameterControl = parameter;
    }

    public void setParameterStatus(IParameter parameter) {
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
}
