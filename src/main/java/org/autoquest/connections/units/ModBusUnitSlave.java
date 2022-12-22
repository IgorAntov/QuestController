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
import org.autoquest.connections.SlaveParameterInt32;

import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class ModBusUnitSlave {

    private String name = "";
    private ModbusSlave slave;
    private final TcpParameters tcpParameters = new TcpParameters();
    private final ArrayList<SlaveParameterCoil> parameterCoils = new ArrayList<>();
    private final ArrayList<SlaveParameterCoil> parameterCoilsGroupRead = new ArrayList<>();
    private final ArrayList<SlaveParameterCoil> parameterCoilsGroupWrite = new ArrayList<>();
    private final ArrayList<SlaveParameterInt> parameterInts = new ArrayList<>();
    private final ArrayList<SlaveParameterInt> parameterIntsGroupRead = new ArrayList<>();
    private final ArrayList<SlaveParameterInt> parameterIntsGroupWrite = new ArrayList<>();
    private final ArrayList<SlaveParameterInt32> parameterInts32 = new ArrayList<>();
    private final ArrayList<SlaveParameterInt32> parameterIntsGroupRead32 = new ArrayList<>();
    private final ArrayList<SlaveParameterInt32> parameterIntsGroupWrite32 = new ArrayList<>();
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

            int startIndex = parameterCoils.size() - parameterCoilsGroupWrite.size() - parameterCoilsGroupRead.size();
            if (parameterCoilsGroupWrite.size() > 0) {
                CoilGroupWrite coilGroupWrite = new CoilGroupWrite(modbusCoils, startIndex, parameterCoilsGroupWrite);
                coilGroupWrite.calcIndex();
                coilGroupWrite.startWriting();
            }

            startIndex = parameterCoils.size() - parameterCoilsGroupRead.size();
            if (parameterCoilsGroupRead.size() > 0) {
                CoilGroupRead coilGroupRead = new CoilGroupRead(modbusCoils, startIndex, parameterCoilsGroupRead);
                coilGroupRead.calcIndex();
                coilGroupRead.startReading();
            }

//            startIndex = parameterInts.size();
//            IntGroupWrite intGroupWrite = new IntGroupWrite(modbusHoldingRegisters, startIndex, parameterIntsGroupWrite);
//            intGroupWrite.start();

//            startIndex = parameterInts.size() + parameterIntsGroupWrite.size();
//            IntGroupRead intGroupRead = new IntGroupRead(modbusHoldingRegisters, startIndex, parameterIntsGroupRead);
//            intGroupRead.start();

            startIndex = (parameterInts32.size() - parameterIntsGroupRead32.size() - parameterIntsGroupWrite32.size()) * 2;
            if (parameterIntsGroupWrite32.size() > 0) {
                Int32GroupWrite int32GroupWrite = new Int32GroupWrite(modbusHoldingRegisters, startIndex, parameterIntsGroupWrite32);
                int32GroupWrite.calcIndex();
                int32GroupWrite.startWriting();
            }

            startIndex = (parameterInts32.size() - parameterIntsGroupRead32.size()) * 2;
            if (parameterIntsGroupRead32.size() > 0) {
                Int32GroupRead int32GroupRead = new Int32GroupRead(modbusHoldingRegisters, startIndex, parameterIntsGroupRead32);
                int32GroupRead.calcIndex();
                int32GroupRead.startReading();
            }

            startIndex = (parameterInts32.size() + parameterFloats.size() - parameterFloatsGroupRead.size() - parameterFloatsGroupWrite.size()) * 2;;
            if (parameterFloatsGroupWrite.size() > 0) {
                FloatGroupWrite floatGroupWrite = new FloatGroupWrite(modbusHoldingRegisters, startIndex, parameterFloatsGroupWrite);
                floatGroupWrite.calcIndex();
                floatGroupWrite.startWriting();
            }

            startIndex = (parameterInts32.size() + parameterFloats.size() - parameterFloatsGroupRead.size()) * 2;;
            if (parameterFloatsGroupRead.size() > 0) {
                FloatGroupRead floatGroupRead = new FloatGroupRead(modbusHoldingRegisters, startIndex, parameterFloatsGroupRead);
                floatGroupRead.calcIndex();
                floatGroupRead.startReading();
            }

        } catch (ModbusIOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setInitValue() {
        for (SlaveParameterCoil p : parameterCoils) {
            p.setValue(p.getInitialValue());
        }
        for (SlaveParameterInt32 p : parameterInts32) {
            p.setValue(p.getInitialValue());
        }
        for (SlaveParameterFloat p : parameterFloats) {
            p.setValue(p.getInitialValue());
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
        int size = parameterInts.size() + (parameterInts32.size() * 2) + (parameterFloats.size() * 2);
        modbusHoldingRegisters = new ModbusHoldingRegisters(size);
        slave.getDataHolder().setHoldingRegisters(modbusHoldingRegisters);

//        calcCoilsIndex();
        int coilMapSize = parameterCoils.size();
        modbusCoils = new ModbusCoils(coilMapSize);
        slave.getDataHolder().setCoils(modbusCoils);
    }

    private void calcCoilsIndex() {
        int index = parameterCoils.size();
        for (SlaveParameterCoil coil : parameterCoilsGroupRead) {
            coil.setIndex(index++);
        }
        index = parameterCoils.size() + parameterCoilsGroupRead.size();
        for (SlaveParameterCoil coil : parameterCoilsGroupWrite) {
            coil.setIndex(index++);
        }
    }

    ;

    public void addCoilToDH(SlaveParameterCoil parameterCoil) {
        parameterCoils.add(parameterCoil);
    }

    public void addIntToDH(SlaveParameterInt parameterInt) {
        parameterInts.add(parameterInt);
    }

    public void addFloatToDH(SlaveParameterFloat parameterFloat) {
        parameterFloats.add(parameterFloat);
    }

    public void addInt32ToDH(SlaveParameterInt32 parameterInt32) {
        parameterInts32.add(parameterInt32);
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
            int v2 = modbusHoldingRegisters.get(index + 1);
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

    public void setInt32Value(int index, int value) {
        byte[] b = ByteBuffer.allocate(4).putInt(value).array();
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

    public int getInt32Value(int index) {
        try {
            int v1 = modbusHoldingRegisters.get(index);
            byte[] b1 = ByteBuffer.allocate(4).putInt(v1).array();
            int v2 = modbusHoldingRegisters.get(index + 1);
            byte[] b2 = ByteBuffer.allocate(4).putInt(v2).array();
            b1[0] = b1[2];
            b1[1] = b1[3];
            b1[2] = b2[2];
            b1[3] = b2[3];
            return ByteBuffer.wrap(b1).getInt();
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

    public ArrayList<SlaveParameterInt32> getSlaveParameterInt32List() {
        return parameterInts32;
    }

    public int intMapSize() {
        return parameterInts.size();
    }

    public int floatMapSize() {
        return parameterFloats.size();
    }

    public int int32MapSize() {
        return parameterInts32.size();
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

    public void addToIntGroupRead32(SlaveParameterInt32 slaveParameterInt32) {
        parameterIntsGroupRead32.add(slaveParameterInt32);
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

    public void addToIntGroupWrite32(SlaveParameterInt32 slaveParameterInt32) {
        parameterIntsGroupWrite32.add(slaveParameterInt32);
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


    public ArrayList<SlaveParameterInt32> getParameterIntsGroupRead32() {
        return parameterIntsGroupRead32;
    }

    public ArrayList<SlaveParameterInt32> getParameterFloatsGroupWrite32() {
        return parameterIntsGroupWrite32;
    }

    public int getPort() {
        return tcpParameters.getPort();
    }
}
