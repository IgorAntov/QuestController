package org.autoquest.connections;

import static org.autoquest.connections.units.MBUnitList.WS_MB_UNIT_SLAVE;

public class Params {

    public static final SlaveParameterCoil START = new SlaveParameterCoil("START SEQ", WS_MB_UNIT_SLAVE, true, ParamType.CONTROL, MembershipType.SINGLE);
    public static final SlaveParameterCoil ABORT = new SlaveParameterCoil("ABORT SEQ", WS_MB_UNIT_SLAVE, true, ParamType.CONTROL, MembershipType.SINGLE);

    public static final SlaveParameterCoil CONTROL = new SlaveParameterCoil("ControlTest", WS_MB_UNIT_SLAVE, true, ParamType.CONTROL, MembershipType.GROUP);
    public static final SlaveParameterCoil CONTROL2 = new SlaveParameterCoil("ControlTest2", WS_MB_UNIT_SLAVE, true, ParamType.CONTROL, MembershipType.GROUP);
    public static final SlaveParameterCoil READ = new SlaveParameterCoil("ReadTest", WS_MB_UNIT_SLAVE, false, ParamType.READ, MembershipType.GROUP);
    public static final SlaveParameterCoil READ2 = new SlaveParameterCoil("ReadTest2", WS_MB_UNIT_SLAVE, false, ParamType.READ, MembershipType.GROUP);

    //Step1

//    public static final SlaveParameterCoil A1T1BYPASS1 = new SlaveParameterCoil("ByPass Key1", WS_MB_UNIT_SLAVE, false, ParamType.CONTROL, MembershipType.GROUP);
//    public static final SlaveParameterCoil A1T1BYPASS2 = new SlaveParameterCoil("BYPass Key2", WS_MB_UNIT_SLAVE, false, ParamType.CONTROL, MembershipType.GROUP);

//    public static final SlaveParameterCoil ACTION1 = new SlaveParameterCoil("Start_Quest", WS_MB_UNIT_SLAVE_SIM, false, ParamType.READ, MembershipType.SINGLE);
//    public static final SlaveParameterCoil ACTION2 = new SlaveParameterCoil("Start_Quest", WS_MB_UNIT_SLAVE_SIM, false, ParamType.READ, MembershipType.SINGLE);
//    public static final SlaveParameterCoil ACTION3 = new SlaveParameterCoil("Start_Quest", WS_MB_UNIT_SLAVE_SIM, false, ParamType.READ, MembershipType.SINGLE);


//    public static SlaveParameterCoil KEY_1 = new SlaveParameterCoil("coil KEY1", WS_MB_UNIT_SLAVE_SIM,  false, ParamType.CONTROL, MembershipType.GROUP);
//    public static SlaveParameterCoil KEY_2 = new SlaveParameterCoil("coil KEY2", WS_MB_UNIT_SLAVE_SIM,  false, ParamType.CONTROL, MembershipType.GROUP);
//    public static SlaveParameterCoil KEY_3 = new SlaveParameterCoil("coil KEY3", WS_MB_UNIT_SLAVE_SIM,  false, ParamType.CONTROL, MembershipType.GROUP);
//    public static SlaveParameterCoil KEY_4 = new SlaveParameterCoil("coil KEY4", WS_MB_UNIT_SLAVE_SIM,  false, ParamType.CONTROL, MembershipType.GROUP);

    public static final SlaveParameterInt I1 = new SlaveParameterInt("I1 test SR", WS_MB_UNIT_SLAVE, 10, ParamType.READ, MembershipType.SINGLE);
    public static final SlaveParameterInt I2 = new SlaveParameterInt("I2 test SR", WS_MB_UNIT_SLAVE, 20, ParamType.READ, MembershipType.SINGLE);
    public static final SlaveParameterInt I3 = new SlaveParameterInt("I3 test GW", WS_MB_UNIT_SLAVE, 30, ParamType.CONTROL, MembershipType.GROUP);
    public static final SlaveParameterInt I4 = new SlaveParameterInt("I4 GW", WS_MB_UNIT_SLAVE, 40, ParamType.CONTROL, MembershipType.GROUP);
    public static final SlaveParameterInt I5 = new SlaveParameterInt("I3 GR", WS_MB_UNIT_SLAVE, 50, ParamType.READ, MembershipType.GROUP);
    public static final SlaveParameterInt I6 = new SlaveParameterInt("I4 GR", WS_MB_UNIT_SLAVE, 60, ParamType.READ, MembershipType.GROUP);


    // Float

    public static final SlaveParameterFloat F1 = new SlaveParameterFloat("F1 SR", WS_MB_UNIT_SLAVE, 10.123f, ParamType.CONTROL, MembershipType.SINGLE);
    public static final SlaveParameterFloat F2 = new SlaveParameterFloat("F2 SR", WS_MB_UNIT_SLAVE, 20.654f, ParamType.READ, MembershipType.SINGLE);

    public static final SlaveParameterInt I7 = new SlaveParameterInt("I1 RS", WS_MB_UNIT_SLAVE, 10, ParamType.READ, MembershipType.SINGLE);


    public static void init() {
    };

}
