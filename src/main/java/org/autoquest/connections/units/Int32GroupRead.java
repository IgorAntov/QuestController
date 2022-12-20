package org.autoquest.connections.units;

import com.intelligt.modbus.jlibmodbus.data.ModbusHoldingRegisters;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataAddressException;
import org.autoquest.connections.SlaveParameterFloat;
import org.autoquest.connections.SlaveParameterInt32;

import java.util.ArrayList;

public class Int32GroupRead extends Thread {
    private ModbusHoldingRegisters modbusHoldingRegisters;
    private ArrayList<SlaveParameterInt32> parameterInt32;
    private int index;

    public Int32GroupRead(ModbusHoldingRegisters modbusHoldingRegisters, int index, ArrayList<SlaveParameterInt32> parameterInts32) {
        this.modbusHoldingRegisters = modbusHoldingRegisters;
        this.parameterInt32 = parameterInts32;
        this.index = index;
    }

    public void startReading() {
        start();
    }
    @Override
    public void run() {
        try {
            do {
                int[] values = modbusHoldingRegisters.getRange(index, parameterInt32.size());
                for (int i = 0; i < parameterInt32.size(); i++) {
                    parameterInt32.get(i).setValue(values[i]);
                }
                sleep(500);
            } while (true);
        } catch (IllegalDataAddressException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
