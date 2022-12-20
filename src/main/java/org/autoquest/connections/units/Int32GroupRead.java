package org.autoquest.connections.units;

import com.intelligt.modbus.jlibmodbus.data.ModbusHoldingRegisters;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataAddressException;
import org.autoquest.connections.SlaveParameterFloat;
import org.autoquest.connections.SlaveParameterInt32;

import java.nio.ByteBuffer;
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
                int[] values = modbusHoldingRegisters.getRange(index, parameterInt32.size()*2);
                System.out.println("SS " + values.length);
                for (int i = 0; i < parameterInt32.size(); i++) {
                    int v1 = values[i*2];
                    byte[] b1 = ByteBuffer.allocate(4).putInt(v1).array();
                    int v2 = values[i*2+1];
                    byte[] b2 = ByteBuffer.allocate(4).putInt(v2).array();
                    b1[0] = b1[2];
                    b1[1] = b1[3];
                    b1[2] = b2[2];
                    b1[3] = b2[3];
                    parameterInt32.get(i).setValue(ByteBuffer.wrap(b1).getInt());
                }
                sleep(500);
            } while (true);
        } catch (IllegalDataAddressException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
