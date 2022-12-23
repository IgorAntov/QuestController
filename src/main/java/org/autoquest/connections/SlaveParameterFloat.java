package org.autoquest.connections;

import org.autoquest.connections.units.IParameter;
import org.autoquest.connections.units.ModBusUnitSlave;

public class SlaveParameterFloat implements IParameterFloat, IParameter {
    private String name = "";
    private final ModBusUnitSlave modBusUnitSlave;
    private int index;
    private final ParamType paramType;
    private final MembershipType membershipType;
    private float value;
    private int channelNumber;

    public SlaveParameterFloat(String name, ModBusUnitSlave modBusUnitSlave, float initialValue, ParamType paramType, MembershipType membershipType) {
        this.name = name;
        this.modBusUnitSlave = modBusUnitSlave;
        this.value = initialValue;
        this.paramType = paramType;
        this.membershipType = membershipType;
        modBusUnitSlave.addFloatToDH(this);
        if (membershipType.equals(MembershipType.SINGLE)) {
            this.index = (modBusUnitSlave.int32MapSize() - 1) * 2 + (modBusUnitSlave.floatMapSize() * 2);
//            System.out.println("float index " + this.index + " MapInt:" + modBusUnitSlave.intMapSize() + " MapFlow: " + modBusUnitSlave.floatMapSize());
        }
        if (membershipType.equals(MembershipType.GROUP) && paramType.equals(ParamType.READ)) {
            this.modBusUnitSlave.addToFloatGroupRead(this);
        }
        if (membershipType.equals(MembershipType.GROUP) && paramType.equals(ParamType.CONTROL)) {
            this.modBusUnitSlave.addToFloatGroupWrite(this);
        }
    }

    @Override
    public void setValue(float value) {
        synchronized (this) {
            if (membershipType.equals(MembershipType.SINGLE)) {
                modBusUnitSlave.setFloatValue(this.index, value);
            } else this.value = value;
        }
    }

    @Override
    public float getValue() {
        synchronized (this) {
            if (membershipType.equals(MembershipType.SINGLE)) {
                return modBusUnitSlave.getFloatValue(this.index);
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

    public float getInitialValue() {
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
}
