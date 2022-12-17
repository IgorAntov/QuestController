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
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ModBusUnitSlave {

    private String name ="";
    private ModbusSlave slave;
    private final TcpParameters tcpParameters = new TcpParameters();
    private final ArrayList<SlaveParameterCoil> parameterCoils = new ArrayList<>();
    private final ArrayList<SlaveParameterCoil> parameterCoilsGroupRead = new ArrayList<>();
    private final ArrayList<SlaveParameterCoil> parameterCoilsGroupWrite = new ArrayList<>();
    private final ArrayList<SlaveParameterInt> parameterInts = new ArrayList<>();
    private final ArrayList<SlaveParameterInt> parameterIntsGroupRead = new ArrayList<>();
    private final ArrayList<SlaveParameterInt> parameterIntsGroupWrite = new ArrayList<>();
    private final ArrayList<SlaveParameterFloat> parameterFloats = new ArrayList<>();
    private final ArrayList<SlaveParameterFloat> parameterFloatsGroupRead = new ArrayList<>();
    private final ArrayList<SlaveParameterFloat> parameterFloatsGroupWrite = new ArrayList<>();
    private final MBDataHolder dh = new MBDataHolder();
    private ModbusCoils modbusCoils;
    private ModbusHoldingRegisters modbusHoldingRegisters;

    public ModBusUnitSlave() {
        Modbus.setLogLevel(Modbus.LogLevel.LEVEL_DEBUG);
    }

    public void startListen() {
        try {
            slave.listen();
            setInitValue();

            CoilGroupWrite coilGroupWrite = new CoilGroupWrite(modbusCoils, parameterCoils.size(), parameterCoilsGroupWrite);
            coilGroupWrite.start();

            int startIndex = parameterCoils.size() + parameterCoilsGroupWrite.size();
            CoilGroupRead coilGroupRead = new CoilGroupRead(modbusCoils, startIndex, parameterCoilsGroupRead);
            coilGroupRead.start();

        } catch (ModbusIOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setInitValue() {
        for (SlaveParameterCoil p: parameterCoils) {
            p.setValue(p.getInitialValue());

        }
    //    for (SlaveParameterCoil p: parameterCoilsGroupRead) {
          //  p.setValue(p.getInitialValue());

     //   }

     //   setCoilValues(parameterCoils.size() + 1, values);
     //   for (SlaveParameterCoil p: parameterCoilsGroupWrite) {
     //       p.setValue(p.getInitialValue());
     //   }
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
        calcCoilsIndex();
        int coilMapSize = parameterCoils.size() + parameterCoilsGroupRead.size() + parameterCoilsGroupWrite.size();
        modbusCoils = new ModbusCoils(coilMapSize);
        slave.getDataHolder().setCoils(modbusCoils);
    }
    private void calcCoilsIndex() {
        int index = parameterCoils.size();
        for (SlaveParameterCoil coil: parameterCoilsGroupRead) {
            coil.setIndex(index++);
        }
        index = parameterCoils.size() + parameterCoilsGroupRead.size();
        for (SlaveParameterCoil coil: parameterCoilsGroupWrite) {
            coil.setIndex(index++);
        }

    };

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
        } catch (IllegalDataAddressException | IllegalDataValueException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCoilValues(int index, boolean[] value) {
        try {
            modbusCoils.setRange(index, value);
        } catch (IllegalDataAddressException | IllegalDataValueException e) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInetAddress() {
        return tcpParameters.getHost().getHostAddress();
    }

    public String getAddress() {
        return String.valueOf(slave.getServerAddress());
    }

    public void addToCoilGroupRead(SlaveParameterCoil slaveParameterCoil) {
        parameterCoilsGroupRead.add(slaveParameterCoil);
    }

    public void addToIntGroupRead(SlaveParameterInt slaveParameterInt) {
        parameterIntsGroupRead.add(slaveParameterInt);
    }

    public void addToFloatGroupRead(SlaveParameterFloat slaveParameterFloat) {
        parameterFloatsGroupRead.add(slaveParameterFloat);
    }

    public void addToCoilGroupWrite(SlaveParameterCoil slaveParameterCoil) {
        parameterCoilsGroupWrite.add(slaveParameterCoil);
    }

    public void addToIntGroupWrite(SlaveParameterInt slaveParameterInt) {
        parameterIntsGroupWrite.add(slaveParameterInt);
    }

    public void addToFloatGroupWrite(SlaveParameterFloat slaveParameterFloat) {
        parameterFloatsGroupWrite.add(slaveParameterFloat);
    }


    public ArrayList<SlaveParameterCoil> getParameterCoilsGroupRead() {
        return parameterCoilsGroupRead;
    }

    public ArrayList<SlaveParameterCoil> getParameterCoilsGroupWrite() {
        return parameterCoilsGroupWrite;
    }

    public ArrayList<SlaveParameterInt> getParameterIntsGroupRead() {
        return parameterIntsGroupRead;
    }

    public ArrayList<SlaveParameterInt> getParameterIntsGroupWrite() {
        return parameterIntsGroupWrite;
    }

    public ArrayList<SlaveParameterFloat> getParameterFloatsGroupRead() {
        return parameterFloatsGroupRead;
    }

    public ArrayList<SlaveParameterFloat> getParameterFloatsGroupWrite() {
        return parameterFloatsGroupWrite;
    }

    public int getPort() {
        return tcpParameters.getPort();
    }
}
