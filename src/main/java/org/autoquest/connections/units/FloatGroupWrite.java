package org.autoquest.connections.units;

import com.intelligt.modbus.jlibmodbus.data.ModbusHoldingRegisters;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataAddressException;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataValueException;
import org.autoquest.connections.SlaveParameterFloat;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class FloatGroupWrite extends Thread {

    private ModbusHoldingRegisters modbusHoldingRegisters;
    private ArrayList<SlaveParameterFloat> parameterFloats;
    private int index;


    public FloatGroupWrite(ModbusHoldingRegisters modbusHoldingRegisters, int index, ArrayList<SlaveParameterFloat> parameterFloats) {
        this.modbusHoldingRegisters = modbusHoldingRegisters;
        this.parameterFloats = parameterFloats;
        this.index = index;
    }

    public void startWriting() {
        start();
    }
    @Override
    public void run() {
        try {
            do {
                int[] values = new int[parameterFloats.size() * 2];
                for (int i = 0; i < (parameterFloats.size() * 2); i++) {
                    int[] int32 = intToFloat(parameterFloats.get(i).getValue());
                    values[i++] = int32[0];
                    values[i] = int32[1];
//                    parameterInts.get(i).setValue(parameterInts.get(i).getValue() + 10);
                }
                modbusHoldingRegisters.setRange(index, values);
                sleep(500);
            } while (true);
        } catch (IllegalDataAddressException | IllegalDataValueException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private int[] intToFloat(float value) {
        byte[] b = ByteBuffer.allocate(4).putFloat(value).array();
        int[] result = new int[2];
        int r = b[0] & 0xFF;
        r = (r << 8) | (b[1] & 0xFF);
        result[0] = r;
        r = b[2] & 0xFF;
        r = (r << 8) | (b[3] & 0xFF);
        result[1] = r;
        return result;
    }

}
