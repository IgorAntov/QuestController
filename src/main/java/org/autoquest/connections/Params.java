package org.autoquest.connections;

// import static org.autoquest.quest.questConfig.mbunits.MBUnitList.WS_MB_UNIT_SLAVE;
import static org.autoquest.quest.questConfig.mbunits.MBUnitList.WS_MB_UNIT_SLAVE;

// Initialisation Order
// 1. Coil => Single(Control, Read) -> Group(Control -> Read)
// 2. Int32 => Single(Control, Read) -> Group(Control -> Read)
// 3. Float => Single(Control, Read) -> Group(Control -> Read)

public class Params {


    public static final MBParameter START = new MBParameter("START_SEQ", WS_MB_UNIT_SLAVE, false, ParamType.READ, MembershipType.GROUP);
    public static final MBParameter START_FB = new MBParameter("START_SEQ_FB", WS_MB_UNIT_SLAVE, true, ParamType.CONTROL, MembershipType.GROUP);
    public static final MBParameter ABORT = new MBParameter("ABORT_SEQ", WS_MB_UNIT_SLAVE, false, ParamType.READ, MembershipType.GROUP);
    public static final MBParameter ABORT_FB = new MBParameter("ABORT_SEQ_FB", WS_MB_UNIT_SLAVE, false, ParamType.CONTROL, MembershipType.GROUP);

    public static final MBParameter PAUSE = new MBParameter("PAUSE_SEQ", WS_MB_UNIT_SLAVE, false, ParamType.READ, MembershipType.GROUP);
    public static final MBParameter PAUSE_FB = new MBParameter("PAUSE_SEQ_FB", WS_MB_UNIT_SLAVE, false, ParamType.CONTROL, MembershipType.GROUP);
    public static final MBParameter RERUN = new MBParameter("RERUN_SEQ", WS_MB_UNIT_SLAVE, false, ParamType.READ, MembershipType.GROUP);
    public static final MBParameter RERUN_FB = new MBParameter("RERUN_SEQ_FB", WS_MB_UNIT_SLAVE, false, ParamType.CONTROL, MembershipType.GROUP);

    public static final MBParameter TIME_HOUR = new MBParameter("TIME_HOUR", WS_MB_UNIT_SLAVE, 0, ParamType.CONTROL, MembershipType.GROUP);
    public static final MBParameter TIME_MIN = new MBParameter("TIME_MIN", WS_MB_UNIT_SLAVE, 0, ParamType.CONTROL, MembershipType.GROUP);
    public static final MBParameter TIME_SEC = new MBParameter("TIME_SEC", WS_MB_UNIT_SLAVE, 0, ParamType.CONTROL, MembershipType.GROUP);

    public static final MBParameter STEPNUMBER = new MBParameter("STEPNUMBER", WS_MB_UNIT_SLAVE, 0, ParamType.CONTROL, MembershipType.SINGLE);

    public static final MBParameter QUEST_FINISHED = new MBParameter("TEST_FINISHED", WS_MB_UNIT_SLAVE, false, ParamType.CONTROL, MembershipType.SINGLE);

    // Test
    
//    public static final SlaveParameterCoil START = new SlaveParameterCoil("START_SEQ", WS_MB_UNIT_SLAVE, false, ParamType.CONTROL, MembershipType.SINGLE);
//    public static final SlaveParameterCoil ABORT = new SlaveParameterCoil("ABORT_SEQ", WS_MB_UNIT_SLAVE, true, ParamType.CONTROL, MembershipType.SINGLE);
//    public static final SlaveParameterCoil ACTION1 = new SlaveParameterCoil("ACTION1", WS_MB_UNIT_SLAVE, false, ParamType.CONTROL, MembershipType.SINGLE);
//    public static final SlaveParameterCoil ACTION2 = new SlaveParameterCoil("ACTION2", WS_MB_UNIT_SLAVE, false, ParamType.CONTROL, MembershipType.SINGLE);
//
//
//
//    public static final SlaveParameterCoil KEY1 = new SlaveParameterCoil("KEY1", WS_MB_UNIT_SLAVE, false, ParamType.READ, MembershipType.SINGLE);
//    public static final SlaveParameterCoil KEY2 = new SlaveParameterCoil("KEY2", WS_MB_UNIT_SLAVE, false, ParamType.READ, MembershipType.SINGLE);
//
//    public static final SlaveParameterCoil BYPASS_KEY1 = new SlaveParameterCoil("BYPASS_KEY1", WS_MB_UNIT_SLAVE, false, ParamType.READ, MembershipType.SINGLE);
//
//    public static final SlaveParameterCoil BYPASS_KEY2 = new SlaveParameterCoil("BYPASS_KEY2", WS_MB_UNIT_SLAVE, false, ParamType.READ, MembershipType.SINGLE);
//
//    public static final SlaveParameterCoil ACTION1_TEST_START = new SlaveParameterCoil("ACTION1_TEST_START", WS_MB_UNIT_SLAVE, false, ParamType.READ, MembershipType.SINGLE);
//
//    public static final SlaveParameterCoil ACTION1_TEST_STOP = new SlaveParameterCoil("ACTION1_TEST_STOP", WS_MB_UNIT_SLAVE, false, ParamType.READ, MembershipType.SINGLE);
//    public static final SlaveParameterCoil ACTION1_ENABLED = new SlaveParameterCoil("ACTION1_ENABLED", WS_MB_UNIT_SLAVE, true, ParamType.READ, MembershipType.SINGLE);
//    public static final SlaveParameterCoil ACTION2_ENABLED = new SlaveParameterCoil("ACTION2_ENABLED", WS_MB_UNIT_SLAVE, true, ParamType.READ, MembershipType.SINGLE);
//
//    public static final SlaveParameterCoil ACTION1_ENABLED_CFM = new SlaveParameterCoil("ACTION1_ENABLED_CFM", WS_MB_UNIT_SLAVE, true, ParamType.CONTROL, MembershipType.SINGLE);
//    public static final SlaveParameterCoil ACTION2_ENABLED_CFM = new SlaveParameterCoil("ACTION1_ENABLED_CFM", WS_MB_UNIT_SLAVE, true, ParamType.CONTROL, MembershipType.SINGLE);


    
    
    //Step1

//    public static final SlaveParameterCoil A1T1BYPASS1 = new SlaveParameterCoil("ByPass Key1", WS_MB_UNIT_SLAVE, false, ParamType.CONTROL, MembershipType.GROUP);
//    public static final SlaveParameterCoil A1T1BYPASS2 = new SlaveParameterCoil("BYPass Key2", WS_MB_UNIT_SLAVE, false, ParamType.CONTROL, MembershipType.GROUP);

//    public static final SlaveParameterCoil ACTION1 = new SlaveParameterCoil("Start_Quest", WS_MB_UNIT_SLAVE_SIM, false, ParamType.READ, MembershipType.SINGLE);
//    public static final SlaveParameterCoil ACTION2 = new SlaveParameterCoil("Start_Quest", WS_MB_UNIT_SLAVE_SIM, false, ParamType.READ, MembershipType.SINGLE);
//    public static final SlaveParameterCoil ACTION3 = new SlaveParameterCoil("Start_Quest", WS_MB_UNIT_SLAVE_SIM, false, ParamType.READ, MembershipType.SINGLE);


 //   public static SlaveParameterCoil KEY_1 = new SlaveParameterCoil("coil KEY1", WS_MB_UNIT_SLAVE_SIM,  false, ParamType.CONTROL, MembershipType.GROUP);
//    public static SlaveParameterCoil KEY_2 = new SlaveParameterCoil("coil KEY2", WS_MB_UNIT_SLAVE_SIM,  false, ParamType.CONTROL, MembershipType.GROUP);
//    public static SlaveParameterCoil KEY_3 = new SlaveParameterCoil("coil KEY3", WS_MB_UNIT_SLAVE_SIM,  false, ParamType.CONTROL, MembershipType.GROUP);
//    public static SlaveParameterCoil KEY_4 = new SlaveParameterCoil("coil KEY4", WS_MB_UNIT_SLAVE_SIM,  false, ParamType.CONTROL, MembershipType.GROUP);

//    public static final SlaveParameterInt32 I1 = new SlaveParameterInt32("I1 test SR", WS_MB_UNIT_SLAVE, -654165426, ParamType.READ, MembershipType.SINGLE);
//    public static final SlaveParameterInt32 I2 = new SlaveParameterInt32("I2 test SR", WS_MB_UNIT_SLAVE, 223165495, ParamType.READ, MembershipType.SINGLE);
//    public static final SlaveParameterInt32 I7 = new SlaveParameterInt32("I7 SW", WS_MB_UNIT_SLAVE, 3377, ParamType.CONTROL, MembershipType.SINGLE);
//    public static final SlaveParameterInt32 I3 = new SlaveParameterInt32("I3 test GW", WS_MB_UNIT_SLAVE, -30, ParamType.CONTROL, MembershipType.GROUP);
//    public static final SlaveParameterInt32 I5 = new SlaveParameterInt32("I3 GR", WS_MB_UNIT_SLAVE, 50, ParamType.READ, MembershipType.GROUP);
//    public static final SlaveParameterInt32 I6 = new SlaveParameterInt32("I4 GR", WS_MB_UNIT_SLAVE, 60, ParamType.READ, MembershipType.GROUP);


    // Float

//    public static final SlaveParameterFloat F1 = new SlaveParameterFloat("F1 SR", WS_MB_UNIT_SLAVE, 10.123f, ParamType.READ, MembershipType.SINGLE);
//    public static final SlaveParameterFloat F2 = new SlaveParameterFloat("F2 SR", WS_MB_UNIT_SLAVE, 20.654f, ParamType.READ, MembershipType.SINGLE);

//    public static final SlaveParameterFloat F3 = new SlaveParameterFloat("F3 GW", WS_MB_UNIT_SLAVE, -30.645f, ParamType.CONTROL, MembershipType.GROUP);
//    public static final SlaveParameterFloat F4 = new SlaveParameterFloat("F4 GW", WS_MB_UNIT_SLAVE, 40.645f, ParamType.CONTROL, MembershipType.GROUP);


//    public static final SlaveParameterFloat F5 = new SlaveParameterFloat("F5 GW", WS_MB_UNIT_SLAVE, 50.3f, ParamType.READ, MembershipType.GROUP);

//    public static final SlaveParameterFloat F6 = new SlaveParameterFloat("F6 GW", WS_MB_UNIT_SLAVE, 60.3f, ParamType.READ, MembershipType.GROUP);

    
    
    
    
    
    public static void init() {
    }

}
