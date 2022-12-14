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
        this.index = (modBusUnitSlave.intMapSize()-1) + ((modBusUnitSlave.floatMapSize()) * 2);
        System.out.println("int index: " + this.index);
    }

    public void setValue(int value) {
        synchronized (this) {
            modBusUnitSlave.setIntValue(this.index, value);
        }
    }

    public void getValue() {
        synchronized (this) {
            modBusUnitSlave.getIntValue(this.index);
        }
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
