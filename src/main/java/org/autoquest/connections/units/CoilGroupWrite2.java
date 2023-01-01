package org.autoquest.connections.units;

import com.intelligt.modbus.jlibmodbus.data.ModbusCoils;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataAddressException;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataValueException;
import org.autoquest.connections.MBParameter;

import java.util.ArrayList;

public class CoilGroupWrite2 extends Thread {

    private final ModbusCoils modbusCoils;
    private final ArrayList<MBParameter> parameterCoils;

    public CoilGroupWrite2(ModbusCoils modbusCoils, ArrayList<MBParameter> parameterCoils) {
        this.modbusCoils = modbusCoils;
        this.parameterCoils = parameterCoils;
    }

    public void startWriting() {
        start();
    }

    @Override
    public void run() {
        try {
            do {
                if (parameterCoils.size() > 0) {
                    boolean[] values = new boolean[parameterCoils.size()];
                    for (int i = 0; i < parameterCoils.size(); i++) {
                        values[i] = parameterCoils.get(i).getBoolValue();
                    }
                    modbusCoils.setRange(parameterCoils.get(0).getIndex(), values);
                }
                sleep(1000);
            } while (true);
        } catch (IllegalDataAddressException | IllegalDataValueException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
