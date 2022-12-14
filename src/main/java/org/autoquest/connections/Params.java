package org.autoquest.connections;

import org.autoquest.connections.units.ModBusUnitSlave;
import org.autoquest.connections.units.units.WSMBUnitSlave;
import org.autoquest.connections.units.units.WSMBUnitSlaveSim;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Params {

    private static final ModBusUnitSlave WS_MB_UNIT_SLAVE = new ModBusUnitSlave();
    private static final ModBusUnitSlave WS_MB_UNIT_SLAVE_SIM = new ModBusUnitSlave();

    //Write value Actions

    public static final SlaveParameterCoil ACTION1 = new SlaveParameterCoil("Start_Quest", WS_MB_UNIT_SLAVE_SIM);
    public static final SlaveParameterCoil ACTION2 = new SlaveParameterCoil("Start_Quest", WS_MB_UNIT_SLAVE_SIM);
    public static final SlaveParameterCoil ACTION3 = new SlaveParameterCoil("Start_Quest", WS_MB_UNIT_SLAVE_SIM);


    //Read value (Keys)
    public static final SlaveParameterCoil START = new SlaveParameterCoil("Start_Quest", WS_MB_UNIT_SLAVE);
    public static final SlaveParameterCoil PAUSE = new SlaveParameterCoil("coil param1", WS_MB_UNIT_SLAVE);
    public static final SlaveParameterCoil ABORT = new SlaveParameterCoil("coil param1", WS_MB_UNIT_SLAVE);

    public static SlaveParameterCoil KEY_1 = new SlaveParameterCoil("coil KEY1", WS_MB_UNIT_SLAVE);
    public static SlaveParameterCoil KEY_2 = new SlaveParameterCoil("coil KEY2", WS_MB_UNIT_SLAVE);
    public static SlaveParameterCoil KEY_3 = new SlaveParameterCoil("coil KEY3", WS_MB_UNIT_SLAVE);
    public static SlaveParameterCoil KEY_4 = new SlaveParameterCoil("coil KEY4", WS_MB_UNIT_SLAVE);





    // Parameters from WS-local PC (Slave) to RepidScada (Master)

//    public static SlaveParameterCoil paramB1 = new SlaveParameterCoil("coil param1", WS_MB_UNIT_SLAVE);
//    public static SlaveParameterCoil paramB2 = new SlaveParameterCoil("coil param2", WS_MB_UNIT_SLAVE);

//    public static SlaveParameterInt paramI1 = new SlaveParameterInt("int paramI1", WS_MB_UNIT_SLAVE);
//    public static SlaveParameterInt paramI2 = new SlaveParameterInt("int paramI2", WS_MB_UNIT_SLAVE);
//    public static SlaveParameterInt paramI3 = new SlaveParameterInt("int paramI3", WS_MB_UNIT_SLAVE);
//    public static SlaveParameterInt paramI4 = new SlaveParameterInt("int paramI4", WS_MB_UNIT_SLAVE);
//    public static SlaveParameterFloat paramF1 = new SlaveParameterFloat("float paramF1", WS_MB_UNIT_SLAVE);
//    public static SlaveParameterFloat paramF2 = new SlaveParameterFloat("float paramF2", WS_MB_UNIT_SLAVE);
    public static void init() throws UnknownHostException {
        // Slave Settings for local PC (WS)
        WS_MB_UNIT_SLAVE.
                setAddress(InetAddress.getLocalHost()).
                setIsKeepAlive(true).
                setPort(1024).
                setSlaveID(1);

        // Slave Settings for local PC (WS Sim)
        WS_MB_UNIT_SLAVE_SIM.
                setAddress(InetAddress.getLocalHost()).
                setIsKeepAlive(true).
                setPort(1024).
                setSlaveID(2);
    };
}
