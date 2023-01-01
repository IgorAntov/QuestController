package org.autoquest.connections.units;

import com.intelligt.modbus.jlibmodbus.data.ModbusHoldingRegisters;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataAddressException;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataValueException;
import org.autoquest.connections.MBParameter;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class FloatGroupWrite2 extends Thread {


    private ModbusHoldingRegisters modbusHoldingRegisters;
    private ArrayList<MBParameter> parameterFloats;

    public FloatGroupWrite2(ModbusHoldingRegisters modbusHoldingRegisters, ArrayList<MBParameter> parameterFloats) {
        this.modbusHoldingRegisters = modbusHoldingRegisters;
        this.parameterFloats = parameterFloats;
    }

    public void startWriting() {
        start();
    }
    @Override
    public void run() {
        try {
            do {
                if (parameterFloats.size() > 0) {
                    int[] values = new int[parameterFloats.size() * 2];
                    for (int i = 0; i < parameterFloats.size(); i++) {
                        int[] int32 = intToFloat(parameterFloats.get(i).getFloatValue());
                        values[i * 2] = int32[0];
                        values[i * 2 + 1] = int32[1];
                    }
                    modbusHoldingRegisters.setRange(parameterFloats.get(0).getIndex(), values);
                }
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
