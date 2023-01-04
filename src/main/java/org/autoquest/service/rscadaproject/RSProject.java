package org.autoquest.service.rscadaproject;

import org.autoquest.quest.view.graphics.GraphicConfig;

//import static org.autoquest.connections.units.MBUnitList.WS_MB_UNIT_SLAVE;

import static org.autoquest.connections.units.MBUnitList.WS_MB_UNIT_SLAVE;

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
        kpCreating.add(WS_MB_UNIT_SLAVE);
        kpCreating.add(WS_MB_UNIT_SLAVE_SIM);
        kpCreating.buildKP();
        kpCreating.buildCommLine();

        CommCfgXML commCfgCreating = new CommCfgXML(path);
        commCfgCreating.add(WS_MB_UNIT_SLAVE);
        commCfgCreating.add(WS_MB_UNIT_SLAVE_SIM);
        commCfgCreating.build();

        ModBusListXML modBusListCreating = new ModBusListXML(path);
        modBusListCreating.add(WS_MB_UNIT_SLAVE);
        modBusListCreating.add(WS_MB_UNIT_SLAVE_SIM);
        modBusListCreating.build();

        InCnlXML inCnlXML = new InCnlXML(path);
        inCnlXML.add(WS_MB_UNIT_SLAVE);
        inCnlXML.add(WS_MB_UNIT_SLAVE_SIM);
        inCnlXML.build();

        CtrlCnlXML CtrlCnlXML = new CtrlCnlXML(path);
        CtrlCnlXML.add(WS_MB_UNIT_SLAVE);
        CtrlCnlXML.add(WS_MB_UNIT_SLAVE_SIM);
        CtrlCnlXML.build();

        //Graphics
        GraphicConfig.build();
        InterfaceXML interfaceXML = new InterfaceXML(path);
        interfaceXML.build();
        interfaceXML.getScreenXML();

    }
}
