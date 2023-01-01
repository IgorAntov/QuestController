package org.autoquest.connections.units;

import com.intelligt.modbus.jlibmodbus.data.ModbusCoils;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataAddressException;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataValueException;
import org.autoquest.connections.MBParameter;
import org.autoquest.connections.SlaveParameterCoil;

import java.util.ArrayList;

public class CoilGroupRead2 extends Thread {

    private ModbusCoils modbusCoils;
    private ArrayList<MBParameter> parameterCoils;

    public CoilGroupRead2(ModbusCoils modbusCoils, ArrayList<MBParameter> parameterCoils) {
        this.modbusCoils = modbusCoils;
        this.parameterCoils = parameterCoils;
    }

    public void startReading() {
        start();
    }

    @Override
    public void run() {
        try {
            do {
                if (parameterCoils.size() > 0) {
                    boolean[] values = modbusCoils.getRange(parameterCoils.get(0).getIndex(), parameterCoils.size());
                    for (int i = 0; i < parameterCoils.size(); i++) {
                        parameterCoils.get(i).setValue(values[i]);
                    }
                }
                sleep(1000);
            } while (true);
        } catch (IllegalDataAddressException | IllegalDataValueException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
