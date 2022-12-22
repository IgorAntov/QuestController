package org.autoquest.connections.adapters;

import org.autoquest.connections.MembershipType;
import org.autoquest.connections.ParamType;
import org.autoquest.connections.SlaveParameterCoil;

 import static org.autoquest.connections.units.MBUnitList.WS_MB_UNIT_SLAVE_SIM;

public class SimulatorParams {

    public static final SlaveParameterCoil KEY1 = new SlaveParameterCoil("KEY1 SIM", WS_MB_UNIT_SLAVE_SIM, false, ParamType.CONTROL, MembershipType.SINGLE);
    public static final SlaveParameterCoil KEY2 = new SlaveParameterCoil("KEY1 SIM", WS_MB_UNIT_SLAVE_SIM, true, ParamType.READ, MembershipType.SINGLE);


    public static void init() {
    }

}
