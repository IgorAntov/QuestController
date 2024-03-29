package org.autoquest.service.rscadaproject;

import org.autoquest.connections.*;
import org.autoquest.connections.units.MBUnitSlave;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class InCnlXML {

    public final String path;

    public final int step = 100;
    private ArrayList<MBUnitSlave> MBUS = new ArrayList<>();

    public InCnlXML(String path) {
        this.path = path;
    }

    public void add(MBUnitSlave modBusUnitSlave) {
        MBUS.add(modBusUnitSlave);
    }

    public void build() {
        Path file = Paths.get(path + "/BaseXML/InCnl.xml");
        try {
            Files.write(file, Collections.singleton(getXmlContent()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public StringBuilder getXmlContent() {
        int index = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("<ArrayOfInCnl xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n");

        for (MBUnitSlave ms : MBUS) {

            int paramIndex = 1;
            for (MBParameter p : ms.getCoilsList()) {
                    sb.append("<InCnl>\n");
                    int cnlNumber = step * index + paramIndex;
                    p.setChannelNumber(cnlNumber);
                    sb.append("<CnlNum>" + cnlNumber + "</CnlNum>\n" +
                            "<Active>true</Active>\n" +
                            "<Name>" + ms.getName() + " - " + p.getName() + "</Name>\n" +
                            "<CnlTypeID>1</CnlTypeID>\n" +
                            "<ObjNum>1</ObjNum>\n" +
                            "<KPNum>" + index + "</KPNum>\n" +
                            "<Signal>" + paramIndex + "</Signal>\n" +
                            "<FormulaUsed>false</FormulaUsed>\n" +
                            "<Formula/>\n" +
                            "<Averaging>false</Averaging>\n" +
                            "<ParamID xsi:nil=\"true\"/>\n" +
                            "<FormatID>10</FormatID>\n" +
                            "<UnitID>16</UnitID>\n" +
                            "<CtrlCnlNum xsi:nil=\"true\"/>\n" +
                            "<EvEnabled>true</EvEnabled>\n" +
                            "<EvSound>false</EvSound>\n" +
                            "<EvOnChange>true</EvOnChange>\n" +
                            "<EvOnUndef>false</EvOnUndef>\n" +
                            "<LimLowCrash xsi:nil=\"true\"/>\n" +
                            "<LimLow xsi:nil=\"true\"/>\n" +
                            "<LimHigh xsi:nil=\"true\"/>\n" +
                            "<LimHighCrash xsi:nil=\"true\"/>\n");
                    sb.append("</InCnl>\n");
                paramIndex++;
            }
            for (MBParameter p : ms.getInt32List()) {
                int cnlNumber = step * index + paramIndex;
                p.setChannelNumber(cnlNumber);
                sb.append("<InCnl>\n");
                        sb.append("<CnlNum>" + cnlNumber + "</CnlNum>\n" +
                                "<Active>true</Active>\n" +
                                "<Name>" + ms.getName() + " - " + p.getName() + "</Name>\n" +
                                "<CnlTypeID>2</CnlTypeID>\n" +
                                "<ObjNum>1</ObjNum>\n" +
                                "<KPNum>" + index + "</KPNum>\n" +
                                "<Signal>" + paramIndex + "</Signal>\n" +
                                "<FormulaUsed>false</FormulaUsed>\n" +
                                "<Formula/>\n" +
                                "<Averaging>false</Averaging>\n" +
                                "<ParamID xsi:nil=\"true\"/>\n" +
                                "<FormatID>0</FormatID>\n" +
                                "<UnitID xsi:nil=\"true\"/>\n" +
                                "<CtrlCnlNum xsi:nil=\"true\"/>\n" +
                                "<EvEnabled>false</EvEnabled>\n" +
                                "<EvSound>false</EvSound>\n" +
                                "<EvOnChange>false</EvOnChange>\n" +
                                "<EvOnUndef>false</EvOnUndef>\n" +
                                "<LimLowCrash xsi:nil=\"true\"/>\n" +
                                "<LimLow xsi:nil=\"true\"/>\n" +
                                "<LimHigh xsi:nil=\"true\"/>\n" +
                                "<LimHighCrash xsi:nil=\"true\"/>\n");
                    sb.append("</InCnl>\n");
                paramIndex++;
            }
            for (MBParameter p : ms.getFloatList()) {
                int cnlNumber = step * index + paramIndex;
                p.setChannelNumber(cnlNumber);
                sb.append("<InCnl>\n");
                    sb.append("<CnlNum>" + cnlNumber + "</CnlNum>\n" +
                            "<Active>true</Active>\n" +
                            "<Name>" + ms.getName() + " - " + p.getName() + "</Name>\n" +
                            "<CnlTypeID>2</CnlTypeID>\n" +
                            "<ObjNum>1</ObjNum>\n" +
                            "<KPNum>" + index + "</KPNum>\n" +
                            "<Signal>" + paramIndex + "</Signal>\n" +
                            "<FormulaUsed>false</FormulaUsed>\n" +
                            "<Formula/>\n" +
                            "<Averaging>false</Averaging>\n" +
                            "<ParamID xsi:nil=\"true\"/>\n" +
                            "<FormatID>3</FormatID>\n" +
                            "<UnitID xsi:nil=\"true\"/>\n" +
                            "<CtrlCnlNum xsi:nil=\"true\"/>\n" +
                            "<EvEnabled>false</EvEnabled>\n" +
                            "<EvSound>false</EvSound>\n" +
                            "<EvOnChange>false</EvOnChange>\n" +
                            "<EvOnUndef>false</EvOnUndef>\n" +
                            "<LimLowCrash xsi:nil=\"true\"/>\n" +
                            "<LimLow xsi:nil=\"true\"/>\n" +
                            "<LimHigh xsi:nil=\"true\"/>\n" +
                            "<LimHighCrash xsi:nil=\"true\"/>\n");
                    sb.append("</InCnl>\n");
                paramIndex++;
            }
            index++;
        }
        sb.append("</ArrayOfInCnl>");
        return sb;
    }
}
