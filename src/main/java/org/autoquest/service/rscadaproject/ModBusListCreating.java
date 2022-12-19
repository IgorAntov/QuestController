package org.autoquest.service.rscadaproject;

import org.autoquest.connections.units.ModBusUnitSlave;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class ModBusListCreating {

    private ArrayList<ModBusUnitSlave> MBUS = new ArrayList<>();
    private final String path;


    public ModBusListCreating(String path) {
        this.path = path;
    }

    public void add(ModBusUnitSlave modBusUnitSlave) {
        MBUS.add(modBusUnitSlave);
    }

    public void build() {
        try {
            for (ModBusUnitSlave ms : MBUS) {
                Path file = Paths.get(path + "/Instances/Default/ScadaComm/Config/" + ms.getName() + "ModBusList.xml");
                Files.write(file, Collections.singleton(getXmlContent()), StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public StringBuilder getXmlContent() {
        StringBuilder sb = new StringBuilder();
        for (ModBusUnitSlave ms : MBUS) {
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
                    .append(ms.getSlaveParameterCoilList().stream()
                            .map(x -> "   <Elem name=\"" + x.getName() + "\"/>")
                            .collect(Collectors.joining("\n"))).append("\n")
                    .append(ms.getParameterCoilsGroupWrite().stream()
                            .map(x -> "   <Elem name=\"" + x.getName() + "\"/>")
                            .collect(Collectors.joining("\n"))).append("\n")
                    .append(ms.getParameterCoilsGroupRead().stream()
                            .map(x -> "   <Elem name=\"" + x.getName() + "\"/>")
                            .collect(Collectors.joining("\n"))).append("\n")
                    .append("  </ElemGroup>\n")
                    .append("  <ElemGroup active=\"true\" tableType=\"HoldingRegisters\" address=\"0\" name=\"intList\">\n")

                    .append(ms.getSlaveParameterIntList().stream()
                            .map(x -> "   <Elem name=\"" + x.getName() + "\" type=\"int\" byteOrder=\"\"/>")
                            .collect(Collectors.joining("\n"))).append("\n")
                    .append(ms.getParameterIntsGroupWrite().stream()
                            .map(x -> "   <Elem name=\"" + x.getName() + "\" type=\"int\" byteOrder=\"\"/>")
                            .collect(Collectors.joining("\n"))).append("\n")
                    .append(ms.getParameterIntsGroupRead().stream()
                            .map(x -> "   <Elem name=\"" + x.getName() + "\" type=\"int\" byteOrder=\"\"/>")
                            .collect(Collectors.joining("\n"))).append("\n")

                    .append(ms.getSlaveParameterFloatList().stream()
                            .map(x -> "   <Elem name=\"" + x.getName() + "\" type=\"float\" byteOrder=\"\"/>")
                            .collect(Collectors.joining("\n"))).append("\n")
                    .append("  </ElemGroup>\n")
                    .append(" </ElemGroups>\n")
                    .append("</DevTemplate>");
        }
        return sb;
    }


}
