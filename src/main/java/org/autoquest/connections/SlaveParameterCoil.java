package org.autoquest.connections;

import org.autoquest.connections.units.IParameter;
import org.autoquest.connections.units.ModBusUnitSlave;

public class SlaveParameterCoil implements IParameterCoil, IParameter {
    private String name = "";
    private final ModBusUnitSlave modBusUnitSlave;
    private int index;
    private final ParamType paramType;
    private final MembershipType membershipType;
    private boolean value;
    private int channelNumber;

    public SlaveParameterCoil(String name, ModBusUnitSlave modBusUnitSlave, boolean initialValue, ParamType paramType, MembershipType membershipType) {
        this.name = name;
        this.modBusUnitSlave = modBusUnitSlave;
        this.value = initialValue;
        this.paramType = paramType;
        this.membershipType = membershipType;
        this.modBusUnitSlave.addCoilToDH(this);
        if (membershipType.equals(MembershipType.SINGLE)) {
            this.index = modBusUnitSlave.getSlaveParameterCoilList().size() - 1;
        }
        if (membershipType.equals(MembershipType.GROUP) && paramType.equals(ParamType.READ)) {
            this.modBusUnitSlave.addToCoilGroupRead(this);
        }
        if (membershipType.equals(MembershipType.GROUP) && paramType.equals(ParamType.CONTROL)) {
            this.modBusUnitSlave.addToCoilGroupWrite(this);
        }
    }

    @Override
    public void setValue(boolean value) {
        synchronized (this) {
            if (membershipType.equals(MembershipType.SINGLE)) {
                modBusUnitSlave.setCoilValue(this.index, value);
            } else this.value = value;
        }
    }

    @Override
    public boolean getValue() {
        synchronized (this) {
            if (membershipType.equals(MembershipType.SINGLE)) {
                return modBusUnitSlave.getCoilValue(this.index);
            } else return this.value;
        }
    }

    @Override
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

    public boolean getInitialValue() {
        return value;
    }

    @Override
    public ParamType getParamType() {
        return paramType;
    }

    @Override
    public int getChannelNumber() {
        return channelNumber;
    }

    public void setChannelNumber(int channelNumber) {
        this.channelNumber = channelNumber;
    }

    @Override
    public String toString() {
        return "SlaveParameterCoil{" +
                "name='" + name + '\'' +
                ", paramType=" + paramType +
                '}';
    }
}
