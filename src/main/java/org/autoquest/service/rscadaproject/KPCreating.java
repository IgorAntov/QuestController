package org.autoquest.service.rscadaproject;

import org.autoquest.connections.units.ModBusUnitSlave;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class KPCreating {
    public final String path;
    private ArrayList<ModBusUnitSlave> MBUS = new ArrayList<>();

    public KPCreating(String path) {
        this.path = path;
    }

    public void add(ModBusUnitSlave modBusUnitSlave) {
        MBUS.add(modBusUnitSlave);
    }

    public void buildKP() {
        Path file = Paths.get(path + "/BaseXML/KP.xml");
        try {
            Files.write(file, Collections.singleton(getXmlContent()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void buildCommLine() {
        Path file = Paths.get(path + "/BaseXML/CommLine.xml");
        try {
            Files.write(file, Collections.singleton(getXmlContentCommLine()), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public StringBuilder getXmlContent() {
        int index = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("<ArrayOfKP xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");

        for (ModBusUnitSlave ms: MBUS) {
            sb.append("<KP>\n" +
                    "<KPNum>" + (index+1) + "</KPNum>\n" +
                    "<Name>" + ms.getName() + "</Name>\n" +
                    "<KPTypeID>214</KPTypeID>\n" +
                    "<Address>" + ms.getAddress() + "</Address>\n" +
                    "<CallNum>" + ms.getInetAddress() + "</CallNum>\n" +
                    "<CommLineNum>" + (index + 1) + "</CommLineNum>\n" +
                    "<Descr/>\n" +
                    "</KP>");
            index++;
        }
        sb.append("</ArrayOfKP>");
        return sb;
    }

    public StringBuilder getXmlContentCommLine() {
        int index = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("<ArrayOfCommLine xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");

        for (ModBusUnitSlave ms: MBUS) {
            sb.append("<CommLine>\n" +
                    "<CommLineNum>" + (index+1) + "</CommLineNum>\n" +
                    "<Name>" + ms.getName() + "CommLine" + "</Name>\n" +
                    "<Descr/>\n" +
                    "</CommLine>");
            index++;
        }
        sb.append("</ArrayOfCommLine>");
        return sb;
    }
}