package org.autoquest.service.rscadaproject;

import static org.autoquest.connections.units.MBUnitList.WS_MB_UNIT_SLAVE;
import static org.autoquest.connections.units.MBUnitList.WS_MB_UNIT_SLAVE_SIM;

public class RSProject {
    public final String path;

    public RSProject(String path) {
        this.path = path;
    }

    public void build() {
        ObjCreating buildObj = new ObjCreating("QuestTest", path);
        buildObj.Build();

        KPCreating kpCreating = new KPCreating(path);
        kpCreating.add(WS_MB_UNIT_SLAVE);
        kpCreating.add(WS_MB_UNIT_SLAVE_SIM);
        kpCreating.buildKP();
        kpCreating.buildCommLine();
    }
}
