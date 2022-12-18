package org.autoquest.connections.units;

import com.intelligt.modbus.jlibmodbus.data.ModbusHoldingRegisters;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataAddressException;
import org.autoquest.connections.SlaveParameterInt;

import java.util.ArrayList;

public class IntGroupRead extends Thread{
    private ModbusHoldingRegisters modbusHoldingRegisters;
    private ArrayList<SlaveParameterInt> parameterInts;
    private int index;

    public IntGroupRead(ModbusHoldingRegisters modbusHoldingRegisters, int index, ArrayList<SlaveParameterInt> parameterInts) {
        this.modbusHoldingRegisters = modbusHoldingRegisters;
        this.parameterInts = parameterInts;
        this.index = index;
    }

    public void startReading() {
        start();
    }
    @Override
    public void run() {
        try {
            do {
                int[] values = modbusHoldingRegisters.getRange(index, parameterInts.size());
                for (int i = 0; i < parameterInts.size(); i++) {
                    parameterInts.get(i).setValue(values[i]);
                }
                sleep(500);
            } while (true);
        } catch (IllegalDataAddressException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
