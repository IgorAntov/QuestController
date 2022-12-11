package org.autoquest.connections.units.units;

import org.autoquest.connections.units.ModBusUnitSlave;

public class WSMBUnitSlave extends ModBusUnitSlave {

    private static final WSMBUnitSlave INSTANCE = new WSMBUnitSlave();
    public static WSMBUnitSlave getInstance() {
        return INSTANCE;
    }

    private WSMBUnitSlave() {};
}
