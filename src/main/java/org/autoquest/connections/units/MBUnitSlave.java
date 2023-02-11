package org.autoquest.connections.units;

import com.intelligt.modbus.jlibmodbus.data.ModbusCoils;
import com.intelligt.modbus.jlibmodbus.data.ModbusHoldingRegisters;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataAddressException;
import com.intelligt.modbus.jlibmodbus.exception.IllegalDataValueException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.slave.ModbusSlave;
import com.intelligt.modbus.jlibmodbus.slave.ModbusSlaveFactory;
import com.intelligt.modbus.jlibmodbus.tcp.TcpParameters;
import org.autoquest.connections.*;

import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MBUnitSlave {

    private String name = "";
    private ModbusSlave slave;
    private final TcpParameters tcpParameters = new TcpParameters();
    private final ArrayList<MBParameter> parameters = new ArrayList<>();
    private final MBDataHolder dh = new MBDataHolder();
    private ModbusCoils modbusCoils;
    private ModbusHoldingRegisters modbusHoldingRegisters;
    private boolean wsSlave = false;

    public MBUnitSlave() {
//        Modbus.setLogLevel(Modbus.LogLevel.LEVEL_DEBUG);
    }

    private void sortParameters(ArrayList<MBParameter> mbParameters) {
        Collections.sort(mbParameters, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                DataType s1 = ((MBParameter) o1).getDataType();
                DataType s2 = ((MBParameter) o2).getDataType();
                if (!s1.equals(s2)) {
                    return s1.compareTo(s2);
                } else {
                    MembershipType x1 = ((MBParameter) o1).getMembershipType();
                    MembershipType x2 = ((MBParameter) o2).getMembershipType();
                    if (!x1.equals(x2)) {
                        return x1.compareTo(x2);
                    } else {
                        ParamType y1 = ((MBParameter) o1).getParamType();
                        ParamType y2 = ((MBParameter) o2).getParamType();
                        return y1.compareTo(y2);
                    }
                }
            }
        });
    }

    private void setIndes() {
        int indexBool = 0;
        int indexInt = 0;
        for (MBParameter p : parameters) {
            if (p.getDataType().equals(DataType.BOOL)) {
                p.setIndex(indexBool++);
            } else {
                p.setIndex(indexInt);
                indexInt = indexInt + 2;
            }
        }
        for (MBParameter p : parameters) {
            System.out.println(p);
        }
    }

    public void startListen() {
        try {
            slave.listen();
            sortParameters(parameters);
            setIndes();
            setInitValue();

            ArrayList<MBParameter> mbParametersWrite = (ArrayList<MBParameter>) parameters.stream()
                    .filter(x -> (x.getParamType().equals(ParamType.CONTROL) && x.getMembershipType().equals(MembershipType.GROUP)))
                    .collect(Collectors.toList());
            GroupWrite groupWrite = new GroupWrite(modbusCoils, modbusHoldingRegisters, mbParametersWrite);
            groupWrite.startWriting();

            ArrayList<MBParameter> mbParametersRead = (ArrayList<MBParameter>) parameters.stream()
                    .filter(x -> (x.getParamType().equals(ParamType.READ) && x.getMembershipType().equals(MembershipType.GROUP)))
                    .collect(Collectors.toList());
            GroupRead groupRead = new GroupRead(modbusCoils, modbusHoldingRegisters, mbParametersRead);
            groupRead.startWriting();

        } catch (ModbusIOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setInitValue() {
        System.out.println("setInit");
        for (MBParameter p : parameters) {
            if (p.getDataType().equals(DataType.BOOL)) {
                p.setInitValue(p.getBoolInitialValue());
            }
            if (p.getDataType().equals(DataType.INT32)) {
                p.setInitValue(p.getInt32InitialValue());
            }
            if (p.getDataType().equals(DataType.FLOAT)) {
                p.setInitValue(p.getFloatInitialValue());
            }
        }
        System.out.println("end setInit");
    }

    public ArrayList<MBParameter> getCoilsList() {
        return (ArrayList<MBParameter>) parameters.stream()
                .filter(x -> x.getDataType().equals(DataType.BOOL))
                .collect(Collectors.toList());
    }

    public ArrayList<MBParameter> getInt32List() {
        return (ArrayList<MBParameter>) parameters.stream()
                .filter(x -> x.getDataType().equals(DataType.INT32))
                .collect(Collectors.toList());
    }

    public ArrayList<MBParameter> getFloatList() {
        return (ArrayList<MBParameter>) parameters.stream()
                .filter(x -> x.getDataType().equals(DataType.FLOAT))
                .collect(Collectors.toList());
    }

    public MBUnitSlave setAddress(InetAddress inetAddress) {
        tcpParameters.setHost(inetAddress);
        return this;
    }

    public MBUnitSlave setIsKeepAlive(boolean isKeepAlive) {
        tcpParameters.setKeepAlive(isKeepAlive);
        return this;
    }

    public MBUnitSlave setPort(int port) {
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
        int size = (int) parameters.stream().filter(x -> !x.getDataType().equals(DataType.BOOL)).count();
        modbusHoldingRegisters = new ModbusHoldingRegisters(size * 2);
        slave.getDataHolder().setHoldingRegisters(modbusHoldingRegisters);

        int coilMapSize = (int) parameters.stream().filter(x -> x.getDataType().equals(DataType.BOOL)).count();
        ;
        modbusCoils = new ModbusCoils(coilMapSize);
        slave.getDataHolder().setCoils(modbusCoils);
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

            String resultWithPadZero = String.format("%32s", Integer.toBinaryString(value))
                    .replace(" ", "0");

            System.out.println(resultWithPadZero);                                          // 00000000000000000000000011111110
            System.out.println(printBinary(resultWithPadZero, 8, "|"));


            int r = b[0] & 0xFF;
            r = (r << 8) | (b[1] & 0xFF);
            modbusHoldingRegisters.set(index, r);

            String resultWithPadZero1 = String.format("%32s", Integer.toBinaryString(r))
                    .replace(" ", "0");

            System.out.println("r1: " + r + " " + printBinary(resultWithPadZero1, 8, "|"));

            r = b[2] & 0xFF;
            r = (r << 8) | (b[3] & 0xFF);


            String resultWithPadZero2 = String.format("%32s", Integer.toBinaryString(r))
                    .replace(" ", "0");

            System.out.println("r2: "  + r + "  " + printBinary(resultWithPadZero2, 8, "|"));

            modbusHoldingRegisters.set(index + 1, r);
        } catch (IllegalDataAddressException | IllegalDataValueException e) {
            throw new RuntimeException(e);
        }
    }

    public static String printBinary(String binary, int blockSize, String separator) {

        // split by blockSize
        List<String> result = new ArrayList<>();
        int index = 0;
        while (index < binary.length()) {
            result.add(binary.substring(index, Math.min(index + blockSize, binary.length())));
            index += blockSize;
        }

        return result.stream().collect(Collectors.joining(separator));
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

    public int getPort() {
        return tcpParameters.getPort();
    }

    public void addParameter(MBParameter parameter) {
        parameters.add(parameter);
    }

    public void setMasterAddress(int address) {
           slave.setServerAddress(address);
    }

    public boolean isWsSlave() {
        return wsSlave;
    }

    public void setWsSlave(boolean wsSlave) {
        this.wsSlave = wsSlave;
    }
}

