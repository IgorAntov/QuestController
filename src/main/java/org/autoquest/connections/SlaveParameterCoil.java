package org.autoquest.connections;

import org.autoquest.connections.units.ModBusUnitSlave;

public class SlaveParameterCoil {
    private String name = "";
    private final ModBusUnitSlave modBusUnitSlave;
    private final int index;

    public SlaveParameterCoil(String name, ModBusUnitSlave modBusUnitSlave) {
        this.name = name;
        this.modBusUnitSlave = modBusUnitSlave;
        modBusUnitSlave.addCoilToDH(this);
        this.index = modBusUnitSlave.getSlaveParameterCoilList().size()-1;
    }

    public void setValue(boolean value) {
        modBusUnitSlave.setCoilValue(this.index, value);
    }

    public boolean getValue() {
        return modBusUnitSlave.getCoilValue(this.index);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
