package org.autoquest.connections;

import org.autoquest.connections.units.units.WSMBUnitSlave;

public class Params {

    private static final WSMBUnitSlave WS_MB_UNIT_SLAVE = WSMBUnitSlave.getInstance();

    // Parameters from WS-local PC (Slave) to RepidScada (Master)

    public static SlaveParameterCoil paramB1 = new SlaveParameterCoil("test param", WS_MB_UNIT_SLAVE);
    public static SlaveParameterCoil paramB2 = new SlaveParameterCoil("test param", WS_MB_UNIT_SLAVE);
    public static SlaveParameterCoil paramB3 = new SlaveParameterCoil("test param", WS_MB_UNIT_SLAVE);


    public static SlaveParameterInt paramI1 = new SlaveParameterInt("test paramI1", WS_MB_UNIT_SLAVE);
    public static SlaveParameterInt paramI2 = new SlaveParameterInt("test paramI2", WS_MB_UNIT_SLAVE);
    public static SlaveParameterInt paramI3 = new SlaveParameterInt("test paramI3", WS_MB_UNIT_SLAVE);
    public static SlaveParameterInt paramI4 = new SlaveParameterInt("test paramI4", WS_MB_UNIT_SLAVE);

    public static SlaveParameterFloat paramF1 = new SlaveParameterFloat("test paramF1", WS_MB_UNIT_SLAVE);
    public static SlaveParameterFloat paramF2 = new SlaveParameterFloat("test paramF2", WS_MB_UNIT_SLAVE);

    public static SlaveParameterInt paramI5 = new SlaveParameterInt("test paramI5", WS_MB_UNIT_SLAVE);

    public static SlaveParameterFloat paramF3 = new SlaveParameterFloat("test paramF3", WS_MB_UNIT_SLAVE);
    public static void init() {};
}
