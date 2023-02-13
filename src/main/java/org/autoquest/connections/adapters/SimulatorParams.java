package org.autoquest.connections.adapters;

import org.autoquest.connections.MBParameter;
import org.autoquest.connections.MembershipType;
import org.autoquest.connections.ParamType;

import static org.autoquest.connections.units.MBUnitList.*;

public class SimulatorParams {

      public static final MBParameter P1 = new MBParameter("P1_Int", UNOR3_1, 111111, ParamType.CONTROL, MembershipType.GROUP);
      public static final MBParameter P2 = new MBParameter("P2_Int", UNOR3_1, 222222, ParamType.CONTROL, MembershipType.GROUP);

    public static final MBParameter START = new MBParameter("START_SEQ", UNOR3_2, true, ParamType.CONTROL, MembershipType.GROUP);
    public static final MBParameter START1 = new MBParameter("START_SEQ", UNOR3_2, true, ParamType.CONTROL, MembershipType.GROUP);
    public static final MBParameter START2 = new MBParameter("START_SEQ", UNOR3_2, true, ParamType.CONTROL, MembershipType.GROUP);


    public static final MBParameter P11 = new MBParameter("P1_Int", UNOR3_1, 333333, ParamType.CONTROL, MembershipType.GROUP);
    public static final MBParameter P12 = new MBParameter("P2_Int", UNOR3_1, 444444, ParamType.CONTROL, MembershipType.GROUP);

    public static final MBParameter P13 = new MBParameter("P1_Int", UNOR3_1, 333333, ParamType.CONTROL, MembershipType.GROUP);
    public static final MBParameter P14 = new MBParameter("P2_Int", UNOR3_1, 444444, ParamType.CONTROL, MembershipType.GROUP);

    public static final MBParameter P15 = new MBParameter("P1_Int", UNOR3_1, 333333, ParamType.CONTROL, MembershipType.GROUP);
    public static final MBParameter P16 = new MBParameter("P2_Int", UNOR3_1, 444444, ParamType.CONTROL, MembershipType.GROUP);

    public static final MBParameter P17 = new MBParameter("P1_Int", UNOR3_1, 333333, ParamType.CONTROL, MembershipType.GROUP);
    public static final MBParameter P18 = new MBParameter("P2_Int", UNOR3_1, 444444, ParamType.CONTROL, MembershipType.GROUP);
    public static final MBParameter P19 = new MBParameter("P1_Int", UNOR3_1, 333333, ParamType.CONTROL, MembershipType.GROUP);
    public static final MBParameter P20 = new MBParameter("P2_Int", UNOR3_1, 444444, ParamType.CONTROL, MembershipType.GROUP);


    public static void init() {
    }

}
