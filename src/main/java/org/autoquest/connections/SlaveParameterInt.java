package org.autoquest.connections;

import org.autoquest.connections.units.ModBusUnitSlave;

public class SlaveParameterInt {
    private String name = "";
    private final ModBusUnitSlave modBusUnitSlave;
    private final int index;

    public SlaveParameterInt(String name, ModBusUnitSlave modBusUnitSlave) {
        this.name = name;
        this.modBusUnitSlave = modBusUnitSlave;
        modBusUnitSlave.addIntToDH(this);
        this.index = modBusUnitSlave.getSlaveParameterIntList().size()-1;
    }

    public void setValue(int value) {
        modBusUnitSlave.setIntValue(this.index, value);
    }

    public void getValue() {
        modBusUnitSlave.getIntValue(this.index);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }
}
