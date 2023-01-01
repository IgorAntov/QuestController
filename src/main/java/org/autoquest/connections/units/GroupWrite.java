package org.autoquest.connections.units;

import com.intelligt.modbus.jlibmodbus.data.ModbusCoils;
import com.intelligt.modbus.jlibmodbus.data.ModbusHoldingRegisters;
import org.autoquest.connections.DataType;
import org.autoquest.connections.MBParameter;
import org.autoquest.connections.MembershipType;
import org.autoquest.connections.ParamType;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class GroupWrite {

    private final ModbusCoils modbusCoils;
    private final ModbusHoldingRegisters modbusHoldingRegisters;
    private final ArrayList<MBParameter> mbParameters;

    public GroupWrite(ModbusCoils modbusCoils, ModbusHoldingRegisters modbusHoldingRegisters, ArrayList<MBParameter> mbParameters) {
        this.modbusCoils = modbusCoils;
        this.modbusHoldingRegisters = modbusHoldingRegisters;
        this.mbParameters = mbParameters;
    }


    public void startWriting() {
        ArrayList<MBParameter> mbParametersCoilControl = (ArrayList<MBParameter>) mbParameters.stream()
                .filter(x -> x.getDataType().equals(DataType.BOOL))
                .collect(Collectors.toList());
        CoilGroupWrite2 coilGroupWrite2 = new CoilGroupWrite2(modbusCoils, mbParametersCoilControl);
        coilGroupWrite2.startWriting();

        ArrayList<MBParameter> mbParametersInt32Control = (ArrayList<MBParameter>) mbParameters.stream()
                .filter(x -> x.getDataType().equals(DataType.INT32))
                .collect(Collectors.toList());
        Int32GroupWrite2 int32GroupWrite2 = new Int32GroupWrite2(modbusHoldingRegisters, mbParametersInt32Control);
        int32GroupWrite2.startWriting();

        ArrayList<MBParameter> mbParametersFloatControl = (ArrayList<MBParameter>) mbParameters.stream()
                .filter(x -> x.getDataType().equals(DataType.FLOAT))
                .collect(Collectors.toList());
        FloatGroupWrite2 floatGroupWrite2 = new FloatGroupWrite2(modbusHoldingRegisters, mbParametersFloatControl);
        floatGroupWrite2.startWriting();
    }
}
