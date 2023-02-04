package org.autoquest.connections;

import org.autoquest.connections.units.MBUnitSlave;

public class MBParameter {

    private String name = "";
    private final  MBUnitSlave  modBusUnitSlave;
    private int index;
    private final ParamType paramType;
    private final DataType dataType;
    private final MembershipType membershipType;
    private boolean valueBool;
    private boolean initvalueBool;
    private int valueInt32;
    private float valueFloat;
    private int channelNumber;


    //new MBParameter("START_SEQ", WS_MB_UNIT_SLAVE2, false, ParamType.CONTROL, MembershipType.SINGLE);
    public MBParameter(String name, MBUnitSlave modBusUnitSlave, boolean initialValue, ParamType paramType, MembershipType membershipType) {
        this.name = name;
        this.modBusUnitSlave = modBusUnitSlave;
        this.valueBool = initialValue;
        this.initvalueBool = initialValue;
        this.paramType = paramType;
        this.membershipType = membershipType;
        this.dataType = DataType.BOOL;
        this.modBusUnitSlave.addParameter(this);
    }

    public MBParameter(String name,  MBUnitSlave modBusUnitSlave, int initialValue, ParamType paramType, MembershipType membershipType) {
        this.name = name;
        this.modBusUnitSlave = modBusUnitSlave;
        this.valueInt32 = initialValue;
        this.paramType = paramType;
        this.membershipType = membershipType;
        this.dataType = DataType.INT32;
        this.modBusUnitSlave.addParameter(this);
    }

    public MBParameter(String name,  MBUnitSlave modBusUnitSlave, float initialValue, ParamType paramType, MembershipType membershipType) {
        this.name = name;
        this.modBusUnitSlave = modBusUnitSlave;
        this.valueFloat = initialValue;
        this.paramType = paramType;
        this.membershipType = membershipType;
        this.dataType = DataType.FLOAT;
        this.modBusUnitSlave.addParameter(this);
    }

    public void setValue(boolean value) {
        if (membershipType.equals(MembershipType.SINGLE)) {
            modBusUnitSlave.setCoilValue(this.index, value);
        } else this.valueBool = value;
    }

    public void farceValue(boolean value) {
            modBusUnitSlave.setCoilValue(this.index, value);
            valueBool = value;
    }

    public void setValue(int value) {
        if (membershipType.equals(MembershipType.SINGLE)) {
            modBusUnitSlave.setInt32Value(this.index, value);
        } else  this.valueInt32 = value;
    }

    public void setValue(float value) {
        if (membershipType.equals(MembershipType.SINGLE)) {
            modBusUnitSlave.setFloatValue(this.index, value);
        } else this.valueFloat = value;
    }
    public void setInitValue(boolean value) {
        modBusUnitSlave.setCoilValue(this.index, value);
        valueBool = value;
    }

    public void setInitValue(int value) {
        modBusUnitSlave.setInt32Value(this.index, value);
        valueInt32 = value;
    }

    public void setInitValue(float value) {
        modBusUnitSlave.setFloatValue(this.index, value);
        valueFloat = value;
    }

    public boolean getBoolValue() {
            if (membershipType.equals(MembershipType.SINGLE)) {
                return modBusUnitSlave.getCoilValue(this.index);
            } else return this.valueBool;
    }


    public int getInt32Value() {
        if (membershipType.equals(MembershipType.SINGLE)) {
            return modBusUnitSlave.getIntValue(this.index);
        } else return this.valueInt32;
    }


    public float getFloatValue() {
        if (membershipType.equals(MembershipType.SINGLE)) {
            return modBusUnitSlave.getFloatValue(this.index);
        } else return this.valueFloat;
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

    public ParamType getParamType() {
        return paramType;
    }

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
                ", paramMS=" + membershipType +
                ", dataType=" + dataType +
                ", index=" + index +
                '}';
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public DataType getDataType() {
        return dataType;
    }

    public boolean getBoolInitialValue() {
        return initvalueBool;
    }

    public int getInt32InitialValue() {
        return valueInt32;
    }

    public float getFloatInitialValue() {
        return valueFloat;
    }

    public boolean isInitvalueBool() {
        return initvalueBool;
    }
}
