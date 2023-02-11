package org.autoquest.connections.adapters;

import org.autoquest.connections.MBParameter;
import org.autoquest.connections.MembershipType;
import org.autoquest.connections.ParamType;

import static org.autoquest.connections.units.MBUnitList.*;

public class SimulatorParams {

//    public static final MBParameter START = new MBParameter("START_SEQ", WS_MB_UNIT_SLAVE_SIM, true, ParamType.CONTROL, MembershipType.GROUP);
//    public static final MBParameter START1 = new MBParameter("START_SEQ", WS_MB_UNIT_SLAVE_SIM, false, ParamType.CONTROL, MembershipType.GROUP);
//    public static final MBParameter START2 = new MBParameter("START_SEQ", WS_MB_UNIT_SLAVE_SIM, true, ParamType.CONTROL, MembershipType.GROUP);

      public static final MBParameter P1 = new MBParameter("P1_Int", WS_MB_UNIT_SLAVE_UNO_R3, 111111, ParamType.CONTROL, MembershipType.GROUP);
      public static final MBParameter P2 = new MBParameter("P2_Int", WS_MB_UNIT_SLAVE_UNO_R3, 111111, ParamType.CONTROL, MembershipType.GROUP);


    public static void init() {
    }

}
