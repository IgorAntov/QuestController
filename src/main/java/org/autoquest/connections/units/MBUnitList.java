package org.autoquest.connections.units;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MBUnitList {
//    public static final ModBusUnitSlave WS_MB_UNIT_SLAVE = new ModBusUnitSlave();
    public static final MBUnitSlave WS_MB_UNIT_SLAVE = new MBUnitSlave();
    public static final MBUnitSlave WS_MB_UNIT_SLAVE_SIM = new MBUnitSlave();
    public static final MBUnitSlave WS_MB_UNIT_SLAVE_UNO_R3 = new MBUnitSlave();

    public static void init() throws UnknownHostException {

        // Slave Settings for UNO R3
        InetAddress addressIp = InetAddress.getByName("192.168.1.5");
        WS_MB_UNIT_SLAVE_UNO_R3.setName("Simulator");
        WS_MB_UNIT_SLAVE_UNO_R3.
                setAddress(addressIp).
                setIsKeepAlive(true).
                setPort(1026).
                setSlaveID(1);

        // Slave Settings for local PC (WS Sim)
        WS_MB_UNIT_SLAVE_SIM.setName("Simulator");
        WS_MB_UNIT_SLAVE_SIM.
                setAddress(InetAddress.getLocalHost()).
                setIsKeepAlive(true).
                setPort(1025).
                setSlaveID(1);
        WS_MB_UNIT_SLAVE_SIM.setWsSlave(true);

        WS_MB_UNIT_SLAVE.setName("WSModBusSlave");
        WS_MB_UNIT_SLAVE.
                setAddress(InetAddress.getLocalHost()).
                setIsKeepAlive(true).
                setPort(1024).
                setSlaveID(1);
        WS_MB_UNIT_SLAVE.setWsSlave(true);
    }

    public static void runListener() {
//        WS_MB_UNIT_SLAVE.evalMapSize();
        WS_MB_UNIT_SLAVE.evalMapSize();
        WS_MB_UNIT_SLAVE_SIM.evalMapSize();
        WS_MB_UNIT_SLAVE_UNO_R3.evalMapSize();

//        WS_MB_UNIT_SLAVE.startListen();
        WS_MB_UNIT_SLAVE.startListen();
        WS_MB_UNIT_SLAVE_SIM.startListen();
        WS_MB_UNIT_SLAVE_UNO_R3.startListen();
    }
}
