package org.autoquest.service.rscadaproject;

import static org.autoquest.connections.units.MBUnitList.WS_MB_UNIT_SLAVE;
import static org.autoquest.connections.units.MBUnitList.WS_MB_UNIT_SLAVE_SIM;
//import static org.autoquest.connections.units.MBUnitList.WS_MB_UNIT_SLAVE_SIM;

public class RSProject {
    public final String path;

    public RSProject(String path) {
        this.path = path;
    }

    public void build() {
        ObjCreating buildObj = new ObjCreating("QuestTest", path);
        buildObj.build();

        KPCreating kpCreating = new KPCreating(path);
        kpCreating.add(WS_MB_UNIT_SLAVE);
        kpCreating.add(WS_MB_UNIT_SLAVE_SIM);
        kpCreating.buildKP();
        kpCreating.buildCommLine();

        CommCfgCreating commCfgCreating = new CommCfgCreating(path);
        commCfgCreating.add(WS_MB_UNIT_SLAVE);
        commCfgCreating.add(WS_MB_UNIT_SLAVE_SIM);
        commCfgCreating.build();

        ModBusListCreating modBusListCreating = new ModBusListCreating(path);
        modBusListCreating.add(WS_MB_UNIT_SLAVE);
        modBusListCreating.add(WS_MB_UNIT_SLAVE_SIM);
        modBusListCreating.build();
    }
}
