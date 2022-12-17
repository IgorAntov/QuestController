package org.autoquest.connections;

import org.autoquest.connections.units.ModBusUnitSlave;

public class SlaveParameterInt {
    private String name = "";
    private final ModBusUnitSlave modBusUnitSlave;
    private int index;
    private final int initialValue;
    private final ParamType paramType;
    private final MembershipType membershipType;

    public SlaveParameterInt(String name, ModBusUnitSlave modBusUnitSlave, int initialValue, ParamType paramType, MembershipType membershipType) {
        this.name = name;
        this.modBusUnitSlave = modBusUnitSlave;
        this.initialValue = initialValue;
        this.paramType = paramType;
        this.membershipType = membershipType;
        if (membershipType.equals(MembershipType.SINGLE)) {
            modBusUnitSlave.addIntToDH(this);
            this.index = (modBusUnitSlave.intMapSize() - 1) + ((modBusUnitSlave.floatMapSize()) * 2);
        }

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
