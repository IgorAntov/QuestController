package org.autoquest.connections;

import org.autoquest.connections.units.ModBusUnitSlave;

public class SlaveParameterFloat {
    private String name = "";
    private final ModBusUnitSlave modBusUnitSlave;
    private final int index;

    public SlaveParameterFloat(String name, ModBusUnitSlave modBusUnitSlave) {
        this.name = name;
        this.modBusUnitSlave = modBusUnitSlave;
        modBusUnitSlave.addFloatToDH(this);
        this.index = modBusUnitSlave.intMapSize() + ((modBusUnitSlave.floatMapSize()-1) * 2);
        System.out.println("float index: " + this.index);
    }

    public void setValue(float value) {
        synchronized (this) {
            modBusUnitSlave.setFloatValue(this.index, value);
        }
    }

    public float getValue() {
        synchronized (this) {
            return modBusUnitSlave.getFloatValue(this.index);
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
