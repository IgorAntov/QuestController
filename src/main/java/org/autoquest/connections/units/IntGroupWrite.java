package org.autoquest.connections.units;

import com.intelligt.modbus.jlibmodbus.data.ModbusHoldingRegisters;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataAddressException;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataValueException;
import org.autoquest.connections.SlaveParameterInt;

import java.util.ArrayList;

public class IntGroupWrite extends Thread{
    private ModbusHoldingRegisters modbusHoldingRegisters;
    private ArrayList<SlaveParameterInt> parameterInts;
    private int index;

    public IntGroupWrite(ModbusHoldingRegisters modbusHoldingRegisters, int index, ArrayList<SlaveParameterInt> parameterInts) {
        this.modbusHoldingRegisters = modbusHoldingRegisters;
        this.parameterInts = parameterInts;
        this.index = index;
    }

    public void startWriting() {
        start();
    }
    @Override
    public void run() {
        try {
            do {
                int[] values = new int[parameterInts.size()];
                for (int i = 0; i < parameterInts.size(); i++) {
                    values[i] = parameterInts.get(i).getValue();
//                    parameterInts.get(i).setValue(parameterInts.get(i).getValue() + 10);
                }
                modbusHoldingRegisters.setRange(index, values);
                sleep(500);
            } while (true);
        } catch (IllegalDataAddressException | IllegalDataValueException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
