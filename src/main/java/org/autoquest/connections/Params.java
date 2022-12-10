package org.autoquest.connections;

import org.autoquest.connections.units.units.WSMBUnit;

public class Params {

    private static final WSMBUnit wsMBUnit = WSMBUnit.getInstance();

    // Parameters from WS-local PC (Slave) to RepidScada (Master)

    public static SlaveParameterCoil paramB1 = new SlaveParameterCoil("test param", wsMBUnit);
    public static SlaveParameterCoil paramB2 = new SlaveParameterCoil("test param", wsMBUnit);


    public static SlaveParameterInt paramI1 = new SlaveParameterInt("test paramI1", wsMBUnit);
    public static SlaveParameterInt paramI2 = new SlaveParameterInt("test paramI2", wsMBUnit);
    public static SlaveParameterInt paramI3 = new SlaveParameterInt("test paramI3", wsMBUnit);
    public static SlaveParameterInt paramI4 = new SlaveParameterInt("test paramI4", wsMBUnit);
    public static SlaveParameterInt paramI5 = new SlaveParameterInt("test paramI5", wsMBUnit);

    public static void init() {};
}
