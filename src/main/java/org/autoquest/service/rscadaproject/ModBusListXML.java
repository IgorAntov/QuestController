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
import java.util.stream.Collectors;

public class ModBusListXML {

    private ArrayList<MBUnitSlave> MBUS = new ArrayList<>();
    private final String path;


    public ModBusListXML(String path) {
        this.path = path;
    }

    public void add(MBUnitSlave modBusUnitSlave) {
        MBUS.add(modBusUnitSlave);
    }

    public void build() {
        try {
            for (MBUnitSlave ms : MBUS) {
                Path file = Paths.get(path + "/Instances/Default/ScadaComm/Config/" + ms.getName() + "ModBusList.xml");
                Files.write(file, Collections.singleton(getXmlContent(ms)), StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public StringBuilder getXmlContent(MBUnitSlave ms) {
        StringBuilder sb = new StringBuilder();
        int index = 1;
        sb.append("""
                        <?xml version="1.0" encoding="utf-8"?>
                        <DevTemplate>
                         <Settings>
                          <ZeroAddr>false</ZeroAddr>
                          <DecAddr>true</DecAddr>
                          <DefByteOrder2/>
                          <DefByteOrder4/>
                          <DefByteOrder8/>
                         </Settings>
                         <ElemGroups>
                        """)
                .append("  <ElemGroup active=\"true\" tableType=\"Coils\" address=\"0\" name=\"coilList\">\n")
                .append(ms.getCoilsList().stream()
                        .map(x -> "   <Elem name=\"" + x.getName() + "\"/>")
                        .collect(Collectors.joining("\n"))).append("\n")

                .append("  </ElemGroup>\n")
                .append("  <ElemGroup active=\"true\" tableType=\"HoldingRegisters\" address=\"0\" name=\"intList\">\n")

                .append(ms.getInt32List().stream()
                        .map(x -> "   <Elem name=\"" + x.getName() + "\" type=\"int\" byteOrder=\"\"/>")
                        .collect(Collectors.joining("\n"))).append("\n")

                .append(ms.getFloatList().stream()
                        .map(x -> "   <Elem name=\"" + x.getName() + "\" type=\"float\" byteOrder=\"\"/>")
                        .collect(Collectors.joining("\n"))).append("\n")
                .append("  </ElemGroup>\n")
                .append(" </ElemGroups>\n")
                .append("<Cmds>\n");

        for (MBParameter p : ms.getCoilsList()) {
            if (p.getParamType().equals(ParamType.READ)) {
                sb.append("<Cmd tableType=\"Coils\" multiple=\"false\" address=\"" + p.getIndex() + "\" cmdNum=\"" + index++ + "\" name=\"" + p.getName() + "control\"/>\n");
            }
        }
        for (MBParameter p : ms.getInt32List()) {
            if (p.getParamType().equals(ParamType.READ)) {
                sb.append("<Cmd tableType=\"HoldingRegisters\" multiple=\"true\" address=\"" + p.getIndex() + "\" elemType=\"int\" elemCnt=\"1\" byteOrder=\"\" cmdNum=\"" + index++ + "\" name=\"" + p.getName() + "control\"/>\n");
            }
        }
        for (MBParameter p : ms.getFloatList()) {
            if (p.getParamType().equals(ParamType.READ)) {
                sb.append("<Cmd tableType=\"HoldingRegisters\" multiple=\"true\" address=\"" + p.getIndex() + "\" elemType=\"float\" elemCnt=\"1\" byteOrder=\"\" cmdNum=\"" + index++ + "\" name=\"" + p.getName() + "control\"/>\n");
            }
        }
     sb.append("</Cmds>\n");
        sb.append("</DevTemplate>");
        return sb;
    }

}
