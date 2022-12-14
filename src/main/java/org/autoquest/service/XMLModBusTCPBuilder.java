package org.autoquest.service;

import org.autoquest.connections.units.ModBusUnitSlave;

import java.util.stream.Collectors;

public class XMLModBusTCPBuilder {
    private final StringBuilder sb = new StringBuilder();
    private final ModBusUnitSlave modBusUnitSlave;

    public XMLModBusTCPBuilder(ModBusUnitSlave modBusUnitSlave) {
        this.modBusUnitSlave = modBusUnitSlave;
    }

    public String buildXMLContent() {
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
                .append(modBusUnitSlave.getSlaveParameterCoilList().stream()
                        .map(x-> "   <Elem name=\"" + x.getName() + "\"/>")
                        .collect(Collectors.joining ("\n"))).append("\n")
                .append("  </ElemGroup>\n")
                .append("  <ElemGroup active=\"true\" tableType=\"HoldingRegisters\" address=\"0\" name=\"intList\">\n")
                .append(modBusUnitSlave.getSlaveParameterIntList().stream()
                        .map(x-> "   <Elem name=\"" + x.getName() + "\" type=\"ushort\" byteOrder=\"\"/>")
                        .collect(Collectors.joining ("\n"))).append("\n")
                .append(modBusUnitSlave.getSlaveParameterFloatList().stream()
                        .map(x-> "   <Elem name=\"" + x.getName() + "\" type=\"float\" byteOrder=\"\"/>")
                        .collect(Collectors.joining ("\n"))).append("\n")
                .append("  </ElemGroup>\n")
                .append(" </ElemGroups>\n")
                .append("</DevTemplate>");
        return sb.toString();
    }
}
