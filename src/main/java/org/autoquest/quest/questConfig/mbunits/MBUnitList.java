package org.autoquest.quest.questConfig.mbunits;

import org.autoquest.connections.units.MBUnitSlave;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MBUnitList {
    public static final MBUnitSlave WS_MB_UNIT_SLAVE = new MBUnitSlave();
    public static final MBUnitSlave WS_MB_UNIT_SLAVE_SIM = new MBUnitSlave();
    public static final MBUnitSlave UNOR3_1 = new MBUnitSlave();
    public static final MBUnitSlave UNOR3_1_PACK2 = new MBUnitSlave();
    public static final MBUnitSlave UNOR3_1_PACK3 = new MBUnitSlave();
    public static final MBUnitSlave UNOR3_2 = new MBUnitSlave();

    public static void init() throws UnknownHostException {
        // WorkStation or PC Node NIC IP Address
        InetAddress addressIp = InetAddress.getByName("192.168.1.5");

        // Slave Settings for UNO R3 CARD 2
        UNOR3_2.setName("UnoR3_Card2");
        UNOR3_2.
                setAddress(addressIp).
                setIsKeepAlive(true).
                setPort(1027).
                setSlaveID(1);

        // Slave Settings for UNO R3 CARD 1
        UNOR3_1.setName("UnoR3_Card1");
        UNOR3_1.
                setAddress(addressIp).
                setIsKeepAlive(true).
                setPort(1026).
                setSlaveID(1);

        UNOR3_1_PACK2.setName("UnoR3_Card1_Pack2");
        UNOR3_1_PACK2.
                setAddress(addressIp).
                setIsKeepAlive(true).
                setPort(1028).
                setSlaveID(1);

        UNOR3_1_PACK3.setName("UnoR3_Card1_Pack3");
        UNOR3_1_PACK3.
                setAddress(addressIp).
                setIsKeepAlive(true).
                setPort(1029).
                setSlaveID(1);

        // Slave Settings for local PC (WS Sim)
        WS_MB_UNIT_SLAVE_SIM.setName("Simulator");
        WS_MB_UNIT_SLAVE_SIM.
                setAddress(InetAddress.getLocalHost()).
                setIsKeepAlive(true).
                setPort(1025).
                setSlaveID(2);
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
        /**
           WS_MB_UNIT_SLAVE.evalMapSize();
        */
        WS_MB_UNIT_SLAVE.evalMapSize();
//        WS_MB_UNIT_SLAVE_SIM.evalMapSize();
//        UNOR3_1.evalMapSize();
//        UNOR3_1_PACK2.evalMapSize();
//        UNOR3_1_PACK3.evalMapSize();
//        UNOR3_2.evalMapSize();

        /**
            WS_MB_UNIT_SLAVE.startListen();
        */
        WS_MB_UNIT_SLAVE.startListen();
//        WS_MB_UNIT_SLAVE_SIM.startListen();
//        UNOR3_1.startListen();
//        UNOR3_1_PACK2.startListen();
//        UNOR3_1_PACK3.startListen();
//        UNOR3_2.startListen();
    }
}
