package org.autoquest.connections;

import org.autoquest.connections.units.ModBusUnitSlave;

public class SlaveParameterInt implements IParameterInt {
    private String name = "";
    private final ModBusUnitSlave modBusUnitSlave;
    private int index;
    private final ParamType paramType;
    private final MembershipType membershipType;
    private int value;
    private int channelNumber;

    public SlaveParameterInt(String name, ModBusUnitSlave modBusUnitSlave, int initialValue, ParamType paramType, MembershipType membershipType) {
        this.name = name;
        this.modBusUnitSlave = modBusUnitSlave;
        this.value = initialValue;
        this.paramType = paramType;
        this.membershipType = membershipType;
        modBusUnitSlave.addIntToDH(this);
        if (membershipType.equals(MembershipType.SINGLE)) {
            this.index = (modBusUnitSlave.intMapSize()-1) + ((modBusUnitSlave.floatMapSize()) * 2);
//            this.index = modBusUnitSlave.intMapSize() + ((modBusUnitSlave.floatMapSize() + 1) * 2);
            System.out.println("int index " + this.index + " MapInt:" + modBusUnitSlave.intMapSize() + " MapFlow: " + modBusUnitSlave.floatMapSize());
        }
        if (membershipType.equals(MembershipType.GROUP) && paramType.equals(ParamType.READ)) {
            this.modBusUnitSlave.addToIntGroupRead(this);
        }
        if (membershipType.equals(MembershipType.GROUP) && paramType.equals(ParamType.CONTROL)) {
            this.modBusUnitSlave.addToIntGroupWrite(this);
        }
    }
    @Override
    public void setValue(int value) {
        synchronized (this) {
            if (membershipType.equals(MembershipType.SINGLE)) {
                modBusUnitSlave.setIntValue(this.index, value);
            } else this.value = value;
        }
    }

    @Override
    public int getValue() {
        synchronized (this) {
            if (membershipType.equals(MembershipType.SINGLE)) {
                return modBusUnitSlave.getIntValue(this.index);
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

    public int getChannelNumber() {
        return channelNumber;
    }

    public void setChannelNumber(int channelNumber) {
        this.channelNumber = channelNumber;
    }
}
