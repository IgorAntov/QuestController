package org.autoquest.connections.units;

import com.intelligt.modbus.jlibmodbus.data.ModbusCoils;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataAddressException;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataValueException;
import org.autoquest.connections.Params;
import org.autoquest.connections.SlaveParameterCoil;

import java.util.ArrayList;

public class CoilGroupWrite extends Thread {

    private ModbusCoils modbusCoils;
    private ArrayList<SlaveParameterCoil> parameterCoils;
    private int index;

    public CoilGroupWrite(ModbusCoils modbusCoils, int index, ArrayList<SlaveParameterCoil> parameterCoils) {
        this.modbusCoils = modbusCoils;
        this.parameterCoils = parameterCoils;
        this.index = index;
    }

    public void startWriting() {
        start();
    }

    @Override
    public void run() {
        try {
            do {
                boolean[] values = new boolean[parameterCoils.size()];
                for (int i = 0; i < parameterCoils.size(); i++) {
                    values[i] = parameterCoils.get(i).getValue();
                }
                modbusCoils.setRange(index, values);
                sleep(1000);
            } while (true);
        } catch (IllegalDataAddressException | IllegalDataValueException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void calcIndex() {
        int index = this.index;
        for (int i = 0; i < parameterCoils.size(); i++) {
            parameterCoils.get(i).setIndex(index++);
        }
    }
}
