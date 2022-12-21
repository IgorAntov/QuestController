package org.autoquest.connections;

import org.autoquest.connections.units.ModBusUnitSlave;

public class SlaveParameterInt32 implements  IParameterInt {

    private String name = "";
    private final ModBusUnitSlave modBusUnitSlave;
    private int index;
    private final ParamType paramType;
    private final MembershipType membershipType;
    private int value;

    public SlaveParameterInt32(String name, ModBusUnitSlave modBusUnitSlave, int initialValue, ParamType paramType, MembershipType membershipType) {
        this.name = name;
        this.modBusUnitSlave = modBusUnitSlave;
        this.value = initialValue;
        this.paramType = paramType;
        this.membershipType = membershipType;
        modBusUnitSlave.addInt32ToDH(this);
        if (membershipType.equals(MembershipType.SINGLE)) {
            this.index = (modBusUnitSlave.int32MapSize() - 1) * 2 + (modBusUnitSlave.floatMapSize() * 2);
//            System.out.println("int32 index " + this.index + " MapInt:" + modBusUnitSlave.int32MapSize() + " MapFlow: " + modBusUnitSlave.floatMapSize());
        }
        if (membershipType.equals(MembershipType.GROUP) && paramType.equals(ParamType.READ)) {
            this.modBusUnitSlave.addToIntGroupRead32(this);
        }
        if (membershipType.equals(MembershipType.GROUP) && paramType.equals(ParamType.CONTROL)) {
            this.modBusUnitSlave.addToIntGroupWrite32(this);
        }
    }

    @Override
    public void setValue(int value) {
        synchronized (this) {
            if (membershipType.equals(MembershipType.SINGLE)) {
                modBusUnitSlave.setInt32Value(this.index, value);
            } else this.value = value;
        }
    }

    @Override
    public int getValue() {
        synchronized (this) {
            if (membershipType.equals(MembershipType.SINGLE)) {
                return modBusUnitSlave.getInt32Value(this.index);
            } else return this.value;
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

    public void setIndex(int index) {
        this.index = index;
    }

    public int getInitialValue() {
        return value;
    }

}
