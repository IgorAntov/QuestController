package org.autoquest.quest.view;



import org.autoquest.connections.MBParameter;

import java.util.ArrayList;

public class ButtonTest  implements IGraphic {

    private int x;
    private int y;
    private String name = "Тест";
    private String desc = "";
    private MBParameter parameterControl;
    private MBParameter parameterStatus;
    private String hint="";
    private int height = 25;
    private int width = 45;

    @Override
    public void getContent(StringBuilder sb, int index) {
        int statusChannelNumber = 0;
        int controlChannelNumber = parameterControl.getChannelNumber();
        String boundProperty = "None";
        if (parameterStatus != null) {
            statusChannelNumber = parameterStatus.getChannelNumber();
            boundProperty = "Visible";
        }
        sb.append("<basic:Button>\n" +
                "      <BackColor></BackColor>\n" +
                "      <BorderColor />\n" +
                "      <BorderWidth>1</BorderWidth>\n" +
                "      <ToolTip>" + hint + "</ToolTip>\n" +
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
                "      <ForeColor>Black</ForeColor>\n" +
                "      <ImageName />\n" +
                "      <ImageSize>\n" +
                "        <Width>30</Width>\n" +
                "        <Height>30</Height>\n" +
                "      </ImageSize>\n" +
                "      <Text>" + name + "</Text>\n" +
                "      <Action>SendCommand</Action>\n" +
                "      <BoundProperty>" + boundProperty + "</BoundProperty>\n" +
                "      <InCnlNum>" + statusChannelNumber + "</InCnlNum>\n" +
                "      <CtrlCnlNum>" + controlChannelNumber + "</CtrlCnlNum>\n" +
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

    public void setName(String name) {
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

    public int getWidth() {
        return width;
    }
}
