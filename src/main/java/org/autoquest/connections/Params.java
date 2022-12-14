package org.autoquest.connections;

import static org.autoquest.connections.units.MBUnitList.WS_MB_UNIT_SLAVE;
import static org.autoquest.connections.units.MBUnitList.WS_MB_UNIT_SLAVE_SIM;

public class Params {

    //Write value Actions

    public static final SlaveParameterCoil ACTION1 = new SlaveParameterCoil("Start_Quest", WS_MB_UNIT_SLAVE_SIM);
    public static final SlaveParameterCoil ACTION2 = new SlaveParameterCoil("Start_Quest", WS_MB_UNIT_SLAVE_SIM);
    public static final SlaveParameterCoil ACTION3 = new SlaveParameterCoil("Start_Quest", WS_MB_UNIT_SLAVE_SIM);


    //Read value (Keys)
    public static final SlaveParameterCoil START = new SlaveParameterCoil("Start_Quest", WS_MB_UNIT_SLAVE);
    public static final SlaveParameterCoil ABORT = new SlaveParameterCoil("coil param1", WS_MB_UNIT_SLAVE);

    public static SlaveParameterCoil KEY_1 = new SlaveParameterCoil("coil KEY1", WS_MB_UNIT_SLAVE);
    public static SlaveParameterCoil KEY_2 = new SlaveParameterCoil("coil KEY2", WS_MB_UNIT_SLAVE);
    public static SlaveParameterCoil KEY_3 = new SlaveParameterCoil("coil KEY3", WS_MB_UNIT_SLAVE);
    public static SlaveParameterCoil KEY_4 = new SlaveParameterCoil("coil KEY4", WS_MB_UNIT_SLAVE);

    public static void init() {
    };

}
