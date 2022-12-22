package org.autoquest.connections.units;

import com.intelligt.modbus.jlibmodbus.data.ModbusHoldingRegisters;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataAddressException;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataValueException;
import org.autoquest.connections.SlaveParameterInt32;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class Int32GroupWrite extends Thread {
    private final ModbusHoldingRegisters modbusHoldingRegisters;
    private final ArrayList<SlaveParameterInt32> parameterInt32;
    private final int index;


    public Int32GroupWrite(ModbusHoldingRegisters modbusHoldingRegisters, int index, ArrayList<SlaveParameterInt32> parameterInt32) {
        this.modbusHoldingRegisters = modbusHoldingRegisters;
        this.parameterInt32 = parameterInt32;
        this.index = index;
    }

    public void startWriting() {
        start();
    }
    @Override
    public void run() {
        try {
            do {
                int[] values = new int[parameterInt32.size() * 2];
                for (int i = 0; i < parameterInt32.size(); i++) {
                    int[] int32 = intToInt32(parameterInt32.get(i).getValue());
                    values[i*2] = int32[0];
                    values[i*2+1] = int32[1];
//                    parameterInts.get(i).setValue(parameterInts.get(i).getValue() + 10);
                }
                modbusHoldingRegisters.setRange(index, values);
                sleep(500);
            } while (true);
        } catch (IllegalDataAddressException | IllegalDataValueException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private int[] intToInt32(int value) {
        byte[] b = ByteBuffer.allocate(4).putInt(value).array();
        int[] result = new int[2];
        int r = b[0] & 0xFF;
        r = (r << 8) | (b[1] & 0xFF);
        result[0] = r;
        r = b[2] & 0xFF;
        r = (r << 8) | (b[3] & 0xFF);
        result[1] = r;
        return result;
    }

    public void calcIndex() {
        int index = this.index;
        for (int i = 0; i < parameterInt32.size(); i++) {
            parameterInt32.get(i).setIndex(index);
            index = index + 2;
        }
    }
}
