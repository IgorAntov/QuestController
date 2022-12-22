package org.autoquest.service.rscadaproject;

import org.autoquest.connections.ParamType;
import org.autoquest.connections.SlaveParameterCoil;
import org.autoquest.connections.SlaveParameterFloat;
import org.autoquest.connections.SlaveParameterInt32;
import org.autoquest.connections.units.ModBusUnitSlave;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class CtrlCnlXML {

    public final String path;

    public final int step = 100;
    private ArrayList<ModBusUnitSlave> MBUS = new ArrayList<>();

    public CtrlCnlXML(String path) {
        this.path = path;
    }

    public void add(ModBusUnitSlave modBusUnitSlave) {
        MBUS.add(modBusUnitSlave);
    }

    public void build() {
        Path file = Paths.get(path + "/BaseXML/CtrlCnl.xml");
        try {
            Files.write(file, Collections.singleton(getXmlContent()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public StringBuilder getXmlContent() {
        int index = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("<ArrayOfCtrlCnl xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n");

        for (ModBusUnitSlave ms : MBUS) {

            int paramIndex = 1;
            for (SlaveParameterCoil p : ms.getParameterCoils()) {
                if (p.getParamType().equals(ParamType.READ)) {
                    int cnlNumber = step * index + paramIndex;
                    p.setChannelNumber(cnlNumber);
                    sb.append("<CtrlCnl>\n");
                    sb.append("<CtrlCnlNum>" + cnlNumber + "</CtrlCnlNum>\n" +
                            "<Active>true</Active>\n" +
                            "<Name>" + ms.getName() + " - " + p.getName() + "</Name>\n" +
                            "<CmdTypeID>0</CmdTypeID>\n" +
                            "<ObjNum>1</ObjNum>\n" +
                            "<KPNum>" + index + "</KPNum>\n" +
                            "<CmdNum>" + paramIndex + "</CmdNum>\n" +
                            "<CmdValID xsi:nil=\"true\"/>\n" +
                            "<FormulaUsed>false</FormulaUsed>\n" +
                            "<Formula/>\n" +
                            "<EvEnabled>false</EvEnabled>");
                    sb.append("</CtrlCnl>\n");
                    paramIndex++;
                }
            }
            for (SlaveParameterInt32 p : ms.getSlaveParameterInt32List()) {
                if (p.getParamType().equals(ParamType.READ)) {
                    int cnlNumber = step * index + paramIndex;
                    p.setChannelNumber(cnlNumber);
                    sb.append("<CtrlCnl>\n");
                    sb.append("<CtrlCnlNum>" + cnlNumber + "</CtrlCnlNum>\n" +
                            "<Active>true</Active>\n" +
                            "<Name>" + ms.getName() + " - " + p.getName() + "</Name>\n" +
                            "<CmdTypeID>0</CmdTypeID>\n" +
                            "<ObjNum>1</ObjNum>\n" +
                            "<KPNum>" + index + "</KPNum>\n" +
                            "<CmdNum>" + paramIndex + "</CmdNum>\n" +
                            "<CmdValID xsi:nil=\"true\"/>\n" +
                            "<FormulaUsed>false</FormulaUsed>\n" +
                            "<Formula/>\n" +
                            "<EvEnabled>false</EvEnabled>");
                    sb.append("</CtrlCnl>\n");
                    paramIndex++;
                }
            }
            for (SlaveParameterFloat p : ms.getSlaveParameterFloatList()) {
                if (p.getParamType().equals(ParamType.READ)) {
                    int cnlNumber = step * index + paramIndex;
                    p.setChannelNumber(cnlNumber);
                    sb.append("<CtrlCnl>\n");
                    sb.append("<CtrlCnlNum>" + cnlNumber + "</CtrlCnlNum>\n" +
                            "<Active>true</Active>\n" +
                            "<Name>" + ms.getName() + " - " + p.getName() + "</Name>\n" +
                            "<CmdTypeID>0</CmdTypeID>\n" +
                            "<ObjNum>1</ObjNum>\n" +
                            "<KPNum>" + index + "</KPNum>\n" +
                            "<CmdNum>" + paramIndex + "</CmdNum>\n" +
                            "<CmdValID xsi:nil=\"true\"/>\n" +
                            "<FormulaUsed>false</FormulaUsed>\n" +
                            "<Formula/>\n" +
                            "<EvEnabled>false</EvEnabled>");
                    sb.append("</CtrlCnl>\n");
                    paramIndex++;
                }
            }
            index++;
        }
        sb.append("</ArrayOfCtrlCnl>");
        return sb;
    }
}
