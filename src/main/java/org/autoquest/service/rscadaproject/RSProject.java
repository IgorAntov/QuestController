package org.autoquest.service.rscadaproject;

import org.autoquest.quest.view.GraphicConfig;
import org.autoquest.quest.view.Group;

//import static org.autoquest.connections.units.MBUnitList.WS_MB_UNIT_SLAVE;

import static org.autoquest.connections.units.MBUnitList.WS_MB_UNIT_SLAVE2;

import static org.autoquest.connections.units.MBUnitList.WS_MB_UNIT_SLAVE_SIM;

public class RSProject {
    public final String path;

    public RSProject(String path) {
        this.path = path;
    }

    public void build() {
        ObjXML buildObj = new ObjXML("QuestTest", path);
        buildObj.build();

        KPXML kpCreating = new KPXML(path);
        kpCreating.add(WS_MB_UNIT_SLAVE2);
        kpCreating.add(WS_MB_UNIT_SLAVE_SIM);
        kpCreating.buildKP();
        kpCreating.buildCommLine();

        CommCfgXML commCfgCreating = new CommCfgXML(path);
        commCfgCreating.add(WS_MB_UNIT_SLAVE2);
        commCfgCreating.add(WS_MB_UNIT_SLAVE_SIM);
        commCfgCreating.build();

        ModBusListXML modBusListCreating = new ModBusListXML(path);
        modBusListCreating.add(WS_MB_UNIT_SLAVE2);
        modBusListCreating.add(WS_MB_UNIT_SLAVE_SIM);
        modBusListCreating.build();

        InCnlXML inCnlXML = new InCnlXML(path);
        inCnlXML.add(WS_MB_UNIT_SLAVE2);
        inCnlXML.add(WS_MB_UNIT_SLAVE_SIM);
        inCnlXML.build();

        CtrlCnlXML CtrlCnlXML = new CtrlCnlXML(path);
        CtrlCnlXML.add(WS_MB_UNIT_SLAVE2);
        CtrlCnlXML.add(WS_MB_UNIT_SLAVE_SIM);
        CtrlCnlXML.build();

        //Graphics
        GraphicConfig.build();
        InterfaceXML interfaceXML = new InterfaceXML(path);
        interfaceXML.build();
        interfaceXML.getScreenXML();

    }
}
