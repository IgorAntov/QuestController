package org.autoquest.connections.units;

import com.intelligt.modbus.jlibmodbus.Modbus;
import com.intelligt.modbus.jlibmodbus.data.ModbusCoils;
import com.intelligt.modbus.jlibmodbus.data.ModbusHoldingRegisters;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataAddressException;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataValueException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.slave.ModbusSlave;
import com.intelligt.modbus.jlibmodbus.slave.ModbusSlaveFactory;
import com.intelligt.modbus.jlibmodbus.tcp.TcpParameters;
import org.autoquest.connections.SlaveParameterCoil;
import org.autoquest.connections.SlaveParameterFloat;
import org.autoquest.connections.SlaveParameterInt;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

public class ModBusUnitSlave {

    private ModbusSlave slave;
    private final TcpParameters tcpParameters = new TcpParameters();
    private final ArrayList<SlaveParameterCoil> parameterCoils = new ArrayList<>();
    private final ArrayList<SlaveParameterInt> parameterInts = new ArrayList<>();
    private final ArrayList<SlaveParameterFloat> parameterFloats = new ArrayList<>();
    private final MBDataHolder dh = new MBDataHolder();
    private ModbusCoils modbusCoils;
    private ModbusHoldingRegisters modbusHoldingRegisters;

    public ModBusUnitSlave() {
        Modbus.setLogLevel(Modbus.LogLevel.LEVEL_DEBUG);
    }

    public void startListen() {
        try {
            slave.listen();
        } catch (ModbusIOException e) {
            throw new RuntimeException(e);
        }
    }
    public ModBusUnitSlave setAddress(InetAddress inetAddress) {
        tcpParameters.setHost(inetAddress);
        return this;
    }

    public ModBusUnitSlave setIsKeepAlive(boolean isKeepAlive) {
        tcpParameters.setKeepAlive(isKeepAlive);
        return this;
    }

    public ModBusUnitSlave setPort(int port) {
        tcpParameters.setPort(port);
        slave = ModbusSlaveFactory.createModbusSlaveTCP(tcpParameters);
        slave.setServerAddress(1);
        slave.setDataHolder(dh);
        return this;
    }

    public void setSlaveID(int inetAddress) {
        slave.setServerAddress(inetAddress);
    }

    public void evalMapSize() {
        modbusHoldingRegisters = new ModbusHoldingRegisters(parameterInts.size() + parameterFloats.size()*2);
        slave.getDataHolder().setHoldingRegisters(modbusHoldingRegisters);
        modbusCoils = new ModbusCoils(parameterCoils.size());
        slave.getDataHolder().setCoils(modbusCoils);
    }

    public void addCoilToDH(SlaveParameterCoil parameterCoil) {
        parameterCoils.add(parameterCoil);
    }

    public void addIntToDH(SlaveParameterInt parameterInt) {
        parameterInts.add(parameterInt);
    }

    public void addFloatToDH(SlaveParameterFloat parameterFloat) {
        parameterFloats.add(parameterFloat);
    }

    public void setCoilValue(int index, boolean value) {
        try {
            modbusCoils.set(index, value);
        } catch (IllegalDataAddressException e) {
            throw new RuntimeException(e);
        } catch (IllegalDataValueException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean getCoilValue(int index) {
        try {
            return modbusCoils.get(index);
        } catch (IllegalDataAddressException e) {
            throw new RuntimeException(e);
        }
    }
    public void setIntValue(int index, int value) {
        try {
            modbusHoldingRegisters.set(index, value);
        } catch (IllegalDataAddressException | IllegalDataValueException e) {
            throw new RuntimeException(e);
        }
    }

    public int getIntValue(int index) {
        try {
            return modbusHoldingRegisters.get(index);
        } catch (IllegalDataAddressException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFloatValue(int index, float value) {
        byte[] b = ByteBuffer.allocate(4).putFloat(value).array();
        try {
            int r = b[0] & 0xFF;
            r = (r << 8) | (b[1] & 0xFF);
            modbusHoldingRegisters.set(index, r);
            r = b[2] & 0xFF;
            r = (r << 8) | (b[3] & 0xFF);
            modbusHoldingRegisters.set(index + 1, r);
        } catch (IllegalDataAddressException | IllegalDataValueException e) {
            throw new RuntimeException(e);
        }
    }

    public float getFloatValue(int index) {
        try {
              int v1 = modbusHoldingRegisters.get(index);
              byte[] b1 = ByteBuffer.allocate(4).putInt(v1).array();
              int v2 = modbusHoldingRegisters.get(index+1);
              byte[] b2 = ByteBuffer.allocate(4).putInt(v2).array();
              b1[0] = b1[2];
              b1[1] = b1[3];
              b1[2] = b2[2];
              b1[3] = b2[3];
            return ByteBuffer.wrap(b1).getFloat();
        } catch (IllegalDataAddressException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<SlaveParameterCoil> getParameterCoils() {
        return parameterCoils;
    }

    public ArrayList<SlaveParameterCoil> getSlaveParameterCoilList() {
        return parameterCoils;
    }

    public ArrayList<SlaveParameterInt> getSlaveParameterIntList() {
        return parameterInts;
    }

    public ArrayList<SlaveParameterFloat> getSlaveParameterFloatList() {
        return parameterFloats;
    }

    public int intMapSize() {
        return parameterInts.size();
    }

    public int floatMapSize() {
        return parameterFloats.size();
    }

    public ModbusSlave getSlave() {
        return slave;
    }

    /*
    public boolean[] getMBCoilList() {
        boolean[] coilsList = new boolean[parameterCoils.size()];
        int index = 0;
        for (SlaveParameterCoil pc : parameterCoils) {
            coilsList[index++] = pc.isBit();
        }
        return coilsList;
    }
 */
}
