package org.autoquest.connections.units;

import com.intelligt.modbus.jlibmodbus.data.ModbusHoldingRegisters;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataAddressException;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataValueException;
import org.autoquest.connections.MBParameter;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class Int32GroupWrite2 extends Thread {

    private final ModbusHoldingRegisters modbusHoldingRegisters;
    private final ArrayList<MBParameter> parameterInt32;

    public Int32GroupWrite2(ModbusHoldingRegisters modbusHoldingRegisters, ArrayList<MBParameter> parameterInt32) {
        this.modbusHoldingRegisters = modbusHoldingRegisters;
        this.parameterInt32 = parameterInt32;
    }

    public void startWriting() {
        start();
    }
    @Override
    public void run() {
        try {
            do {
                if (parameterInt32.size() > 0) {
                    int[] values = new int[parameterInt32.size() * 2];
                    for (int i = 0; i < parameterInt32.size(); i++) {
                        int[] int32 = intToInt32(parameterInt32.get(i).getInt32Value());
                        values[i * 2] = int32[0];
                        values[i * 2 + 1] = int32[1];
                    }
                    modbusHoldingRegisters.setRange(parameterInt32.get(0).getIndex(), values);
                }
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

}
