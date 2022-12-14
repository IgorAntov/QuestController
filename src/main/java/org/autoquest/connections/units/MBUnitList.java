package org.autoquest.connections.units;

import org.autoquest.connections.units.units.WSMBUnitSlave;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MBUnitList {
    public static void init() throws UnknownHostException {

    /*
        // Slave Settings for local PC (WS)
        WSMBUnitSlave.getInstance().
                setAddress(InetAddress.getLocalHost()).
                setIsKeepAlive(true).
                setPort(1024).
                setSlaveID(1);

        // Slave Settings for local PC (WS Sim)
        WSMBUnitSlave.getInstance().
                setAddress(InetAddress.getLocalHost()).
                setIsKeepAlive(true).
                setPort(1024).
                setSlaveID(2);
*/
        // Master Settings for local PC

    }

    /**
     * Evaluating modbus data range and run listeners
     */
    public static void runListener() {
        WSMBUnitSlave.getInstance().evalMapSize();
        WSMBUnitSlave.getInstance().startListen();
    }
}
