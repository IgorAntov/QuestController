package org.autoquest.connections;

import org.autoquest.connections.units.ModBusUnitSlave;

public class SlaveParameterFloat {
    private String name = "";
    private final ModBusUnitSlave modBusUnitSlave;
    private final int index;
    private final float initialValue;
    private final ParamType paramType;
    private final MembershipType membershipType;

    public SlaveParameterFloat(String name, ModBusUnitSlave modBusUnitSlave, float initialValue, ParamType paramType, MembershipType membershipType) {
        this.name = name;
        this.modBusUnitSlave = modBusUnitSlave;
        this.initialValue = initialValue;
        this.paramType = paramType;
        this.membershipType = membershipType;
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
