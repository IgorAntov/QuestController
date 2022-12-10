package org.autoquest.connections.units;

import org.autoquest.connections.units.units.WSMBUnit;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MBUnitList {
    public static void init() throws UnknownHostException {
        // Slave Settings for local PC
        WSMBUnit.getInstance().
        setAddress(InetAddress.getLocalHost()).
        setIsKeepAlive(true).
        setPort(1024).
        setSlaveID(2);

        // Master Settings for local PC
    }
}
