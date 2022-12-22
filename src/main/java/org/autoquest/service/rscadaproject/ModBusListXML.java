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
import java.util.stream.Collectors;

public class ModBusListXML {

    private ArrayList<ModBusUnitSlave> MBUS = new ArrayList<>();
    private final String path;


    public ModBusListXML(String path) {
        this.path = path;
    }

    public void add(ModBusUnitSlave modBusUnitSlave) {
        MBUS.add(modBusUnitSlave);
    }

    public void build() {
        try {
            for (ModBusUnitSlave ms : MBUS) {
                Path file = Paths.get(path + "/Instances/Default/ScadaComm/Config/" + ms.getName() + "ModBusList.xml");
                Files.write(file, Collections.singleton(getXmlContent(ms)), StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public StringBuilder getXmlContent(ModBusUnitSlave ms) {
        StringBuilder sb = new StringBuilder();
        int index = 1;
//        for (ModBusUnitSlave ms : MBUS) {
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

                //              .append(ms.getParameterCoilsGroupWrite().stream()
                //                      .map(x -> "   <Elem name=\"" + x.getName() + "\"/>")
                //                      .collect(Collectors.joining("\n"))).append("\n")
                //              .append(ms.getParameterCoilsGroupRead().stream()
                //                      .map(x -> "   <Elem name=\"" + x.getName() + "\"/>")
                //                      .collect(Collectors.joining("\n"))).append("\n")
                .append("  </ElemGroup>\n")
                .append("  <ElemGroup active=\"true\" tableType=\"HoldingRegisters\" address=\"0\" name=\"intList\">\n")

                .append(ms.getSlaveParameterInt32List().stream()
                        .map(x -> "   <Elem name=\"" + x.getName() + "\" type=\"int\" byteOrder=\"\"/>")
                        .collect(Collectors.joining("\n"))).append("\n")

                //            .append(ms.getParameterIntsGroupWrite().stream()
                //                    .map(x -> "   <Elem name=\"" + x.getName() + "\" type=\"int\" byteOrder=\"\"/>")
                //                    .collect(Collectors.joining("\n"))).append("\n")
                //            .append(ms.getParameterIntsGroupRead().stream()
                //                    .map(x -> "   <Elem name=\"" + x.getName() + "\" type=\"int\" byteOrder=\"\"/>")
                //                    .collect(Collectors.joining("\n"))).append("\n")

                .append(ms.getSlaveParameterFloatList().stream()
                        .map(x -> "   <Elem name=\"" + x.getName() + "\" type=\"float\" byteOrder=\"\"/>")
                        .collect(Collectors.joining("\n"))).append("\n")
                .append("  </ElemGroup>\n")
                .append(" </ElemGroups>\n")
                .append("<Cmds>\n");

        for (SlaveParameterCoil p : ms.getParameterCoils()) {
            if (p.getParamType().equals(ParamType.READ)) {
                sb.append("<Cmd tableType=\"Coils\" multiple=\"false\" address=\"" + p.getIndex() + "\" cmdNum=\"" + index++ + "\" name=\"" + p.getName() + "control\"/>\n");
                System.out.println("xml:" + p);
            }
        }
        for (SlaveParameterInt32 p : ms.getSlaveParameterInt32List()) {
            if (p.getParamType().equals(ParamType.READ)) {
                sb.append("<Cmd tableType=\"HoldingRegisters\" multiple=\"true\" address=\"" + p.getIndex() + "\" elemType=\"int\" elemCnt=\"1\" byteOrder=\"\" cmdNum=\"" + index++ + "\" name=\"" + p.getName() + "control\"/>\n");
            }
        }
        for (SlaveParameterFloat p : ms.getSlaveParameterFloatList()) {
            if (p.getParamType().equals(ParamType.READ)) {
                sb.append("<Cmd tableType=\"HoldingRegisters\" multiple=\"true\" address=\"" + p.getIndex() + "\" elemType=\"float\" elemCnt=\"1\" byteOrder=\"\" cmdNum=\"" + index++ + "\" name=\"" + p.getName() + "control\"/>\n");
            }
        }

        //           .append(ms.getSlaveParameterCoilList().stream().filter(e->e.getParamType().equals(ParamType.READ))
        //                   .map(x -> "<Cmd tableType=\"Coils\" multiple=\"false\" address=\"" + x.getIndex() + "\" cmdNum=\"1\" name=\"writeC3\"/>"))


        //                     "<Cmd tableType=\"HoldingRegisters\" multiple=\"true\" address=\"8\" elemType=\"int\" elemCnt=\"1\" byteOrder=\"\" cmdNum=\"3\" name=\"writeInt32I3\"/>\n" +
        //                     "<Cmd tableType=\"HoldingRegisters\" multiple=\"true\" address=\"10\" elemType=\"int\" elemCnt=\"1\" byteOrder=\"\" cmdNum=\"4\" name=\"writeInt32I4\"/>\n" +
        //                     "<Cmd tableType=\"HoldingRegisters\" multiple=\"true\" address=\"16\" elemType=\"float\" elemCnt=\"1\" byteOrder=\"\" cmdNum=\"5\" name=\"writeF3\"/>\n" +
        //                     "<Cmd tableType=\"HoldingRegisters\" multiple=\"true\" address=\"18\" elemType=\"float\" elemCnt=\"1\" byteOrder=\"\" cmdNum=\"6\" name=\"writeF4\"/>\n" +
        sb.append("</Cmds>\n");
        sb.append("</DevTemplate>");
        return sb;
    }

}
