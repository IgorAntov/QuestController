package org.autoquest.connections.units;

import com.intelligt.modbus.jlibmodbus.data.ModbusCoils;
import com.intelligt.modbus.jlibmodbus.data.ModbusHoldingRegisters;
import org.autoquest.connections.DataType;
import org.autoquest.connections.MBParameter;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class GroupRead {

    private final ModbusCoils modbusCoils;
    private final ModbusHoldingRegisters modbusHoldingRegisters;
    private final ArrayList<MBParameter> mbParameters;

    public GroupRead(ModbusCoils modbusCoils, ModbusHoldingRegisters modbusHoldingRegisters, ArrayList<MBParameter> mbParameters) {
        this.modbusCoils = modbusCoils;
        this.modbusHoldingRegisters = modbusHoldingRegisters;
        this.mbParameters = mbParameters;
    }

    public void startWriting() {
        ArrayList<MBParameter> mbParametersCoilRead = (ArrayList<MBParameter>) mbParameters.stream()
                .filter(x -> x.getDataType().equals(DataType.BOOL))
                .collect(Collectors.toList());
        CoilGroupRead2 coilGroupRead2 = new CoilGroupRead2(modbusCoils, mbParametersCoilRead);
        coilGroupRead2.startReading();

        ArrayList<MBParameter> mbParametersInt32Read = (ArrayList<MBParameter>) mbParameters.stream()
                .filter(x -> x.getDataType().equals(DataType.INT32))
                .collect(Collectors.toList());
        Int32GroupRead2 int32GroupRead2 = new Int32GroupRead2(modbusHoldingRegisters, mbParametersInt32Read);
        int32GroupRead2.startReading();

        ArrayList<MBParameter> mbParametersFloatRead = (ArrayList<MBParameter>) mbParameters.stream()
                .filter(x -> x.getDataType().equals(DataType.FLOAT))
                .collect(Collectors.toList());
        FloatGroupRead2 floatGroupRead2 = new FloatGroupRead2(modbusHoldingRegisters, mbParametersFloatRead);
        floatGroupRead2.startReading();
    }
}
