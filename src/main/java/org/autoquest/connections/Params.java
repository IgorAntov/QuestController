package org.autoquest.connections;

import org.autoquest.connections.units.ParamType;

import static org.autoquest.connections.units.MBUnitList.WS_MB_UNIT_SLAVE;
import static org.autoquest.connections.units.MBUnitList.WS_MB_UNIT_SLAVE_SIM;

public class Params {

    public static final SlaveParameterCoil START = new SlaveParameterCoil("START SEQ", WS_MB_UNIT_SLAVE, false, ParamType.CONTROL);
    public static final SlaveParameterCoil ABORT = new SlaveParameterCoil("ABORT SEQ", WS_MB_UNIT_SLAVE, false, ParamType.CONTROL);

    //Step1

    public static final SlaveParameterCoil A1T1BYPASS1 = new SlaveParameterCoil("ByPass Key1", WS_MB_UNIT_SLAVE, false, ParamType.CONTROL);
    public static final SlaveParameterCoil A1T1BYPASS2 = new SlaveParameterCoil("BYPass Key2", WS_MB_UNIT_SLAVE, false, ParamType.CONTROL);

    public static final SlaveParameterCoil ACTION1 = new SlaveParameterCoil("Start_Quest", WS_MB_UNIT_SLAVE_SIM, false, ParamType.READ);
    public static final SlaveParameterCoil ACTION2 = new SlaveParameterCoil("Start_Quest", WS_MB_UNIT_SLAVE_SIM, false, ParamType.READ);
    public static final SlaveParameterCoil ACTION3 = new SlaveParameterCoil("Start_Quest", WS_MB_UNIT_SLAVE_SIM, false, ParamType.READ);


    public static SlaveParameterCoil KEY_1 = new SlaveParameterCoil("coil KEY1", WS_MB_UNIT_SLAVE_SIM,  false, ParamType.CONTROL);
    public static SlaveParameterCoil KEY_2 = new SlaveParameterCoil("coil KEY2", WS_MB_UNIT_SLAVE_SIM,  false, ParamType.CONTROL);
    public static SlaveParameterCoil KEY_3 = new SlaveParameterCoil("coil KEY3", WS_MB_UNIT_SLAVE_SIM,  false, ParamType.CONTROL);
    public static SlaveParameterCoil KEY_4 = new SlaveParameterCoil("coil KEY4", WS_MB_UNIT_SLAVE_SIM,  false, ParamType.CONTROL);

    public static void init() {
    };

}
