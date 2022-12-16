package org.autoquest.connections;

import org.autoquest.connections.units.ModBusUnitSlave;
import org.autoquest.connections.units.ParamType;

public class SlaveParameterCoil {
    private String name = "";
    private final ModBusUnitSlave modBusUnitSlave;
    private final int index;
    private final boolean initialValue;
    private final ParamType paramType;

    public SlaveParameterCoil(String name, ModBusUnitSlave modBusUnitSlave, boolean initialValue, ParamType paramType) {
        this.name = name;
        this.modBusUnitSlave = modBusUnitSlave;
        this.initialValue = initialValue;
        this.paramType = paramType;
        modBusUnitSlave.addCoilToDH(this);
        this.index = modBusUnitSlave.getSlaveParameterCoilList().size()-1;
    }

    public void setValue(boolean value) {
        synchronized (this) {
            modBusUnitSlave.setCoilValue(this.index, value);
        }
    }

    public boolean getValue() {
        synchronized (this) {
            return modBusUnitSlave.getCoilValue(this.index);
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
