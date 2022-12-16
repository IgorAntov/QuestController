package org.autoquest.connections;

import org.autoquest.connections.units.ModBusUnitSlave;
import org.autoquest.connections.units.ParamType;

public class SlaveParameterInt {
    private String name = "";
    private final ModBusUnitSlave modBusUnitSlave;
    private final int index;
    private final int initialValue;
    private final ParamType paramType;

    public SlaveParameterInt(String name, ModBusUnitSlave modBusUnitSlave, int initialValue, ParamType paramType) {
        this.name = name;
        this.modBusUnitSlave = modBusUnitSlave;
        this.initialValue = initialValue;
        this.paramType = paramType;
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
