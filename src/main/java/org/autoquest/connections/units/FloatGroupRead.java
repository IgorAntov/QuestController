package org.autoquest.connections.units;

import com.intelligt.modbus.jlibmodbus.data.ModbusHoldingRegisters;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataAddressException;
import org.autoquest.connections.SlaveParameterFloat;
import org.autoquest.connections.SlaveParameterInt;

import java.nio.ByteBuffer;
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
                int[] values = modbusHoldingRegisters.getRange(index, parameterFloats.size() * 2);
//                System.out.println("SS " + values.length);
                for (int i = 0; i < parameterFloats.size(); i++) {
                    int v1 = values[i * 2];
                    byte[] b1 = ByteBuffer.allocate(4).putInt(v1).array();
                    int v2 = values[i * 2 + 1];
                    byte[] b2 = ByteBuffer.allocate(4).putInt(v2).array();
                    b1[0] = b1[2];
                    b1[1] = b1[3];
                    b1[2] = b2[2];
                    b1[3] = b2[3];
                    parameterFloats.get(i).setValue(ByteBuffer.wrap(b1).getFloat());
                }
                sleep(500);
            } while (true);
        } catch (IllegalDataAddressException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void calcIndex() {
        int index = this.index;
        for (int i = 0; i < parameterFloats.size(); i++) {
            parameterFloats.get(i).setIndex(index);
            index = index + 2;
        }
    }
}
