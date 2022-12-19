package org.autoquest.connections.units;

import com.intelligt.modbus.jlibmodbus.data.ModbusHoldingRegisters;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataAddressException;
import org.autoquest.connections.SlaveParameterFloat;
import org.autoquest.connections.SlaveParameterInt;

import java.util.ArrayList;

public class FloatGroupRead extends Thread {

    private ModbusHoldingRegisters modbusHoldingRegisters;
    private ArrayList<SlaveParameterFloat> parameterFloats;
    private int index;

    public FloatGroupRead(ModbusHoldingRegisters modbusHoldingRegisters, int index, ArrayList<SlaveParameterFloat> parameterFloats) {
        this.modbusHoldingRegisters = modbusHoldingRegisters;
        this.parameterFloats = parameterFloats;
        this.index = index;
    }

    public void startReading() {
        start();
    }
    @Override
    public void run() {
        try {
            do {
                int[] values = modbusHoldingRegisters.getRange(index, parameterFloats.size());
                for (int i = 0; i < parameterFloats.size(); i++) {
                    parameterFloats.get(i).setValue(values[i]);
                }
                sleep(500);
            } while (true);
        } catch (IllegalDataAddressException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
