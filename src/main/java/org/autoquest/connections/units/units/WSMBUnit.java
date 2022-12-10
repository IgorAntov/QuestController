package org.autoquest.connections.units.units;

import org.autoquest.connections.units.ModBusUnitSlave;

public class WSMBUnit extends ModBusUnitSlave {

    private static final WSMBUnit INSTANCE = new WSMBUnit();
    public static WSMBUnit getInstance() {
        return INSTANCE;
    }

    private WSMBUnit() {};
}
