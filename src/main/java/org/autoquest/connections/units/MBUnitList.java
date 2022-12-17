package org.autoquest.connections.units;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MBUnitList {
    public static final ModBusUnitSlave WS_MB_UNIT_SLAVE = new ModBusUnitSlave();
//    public static final ModBusUnitSlave WS_MB_UNIT_SLAVE_SIM = new ModBusUnitSlave();

    public static void init() throws UnknownHostException {
        WS_MB_UNIT_SLAVE.setName("WSModBusSlave");
        WS_MB_UNIT_SLAVE.
                setAddress(InetAddress.getLocalHost()).
                setIsKeepAlive(true).
                setPort(1024).
                setSlaveID(1);
/*
        // Slave Settings for local PC (WS Sim)
        WS_MB_UNIT_SLAVE_SIM.setName("Simulator");
        WS_MB_UNIT_SLAVE_SIM.
                setAddress(InetAddress.getLocalHost()).
                setIsKeepAlive(true).
                setPort(1025).
                setSlaveID(2);

 */
    }

    public static void runListener() {
        WS_MB_UNIT_SLAVE.evalMapSize();
    //    WS_MB_UNIT_SLAVE_SIM.evalMapSize();

       WS_MB_UNIT_SLAVE.startListen();
   //     WS_MB_UNIT_SLAVE_SIM.startListen();
    }
}
