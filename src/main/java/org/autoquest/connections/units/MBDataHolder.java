package org.autoquest.connections.units;

import com.intelligt.modbus.jlibmodbus.data.DataHolder;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataAddressException;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataValueException;

public class MBDataHolder extends DataHolder {

    @Override
    public void writeHoldingRegister(int offset, int value) throws IllegalDataAddressException, IllegalDataValueException {
        super.writeHoldingRegister(offset, value);
    }

    @Override
    public void writeHoldingRegisterRange(int offset, int[] range) throws IllegalDataAddressException, IllegalDataValueException {
        super.writeHoldingRegisterRange(offset, range);
    }

    @Override
    public void writeCoil(int offset, boolean value) throws IllegalDataAddressException, IllegalDataValueException {
        super.writeCoil(offset, value);
    }

    @Override
    public void writeCoilRange(int offset, boolean[] range) throws IllegalDataAddressException, IllegalDataValueException {
        super.writeCoilRange(offset, range);
    }

}
