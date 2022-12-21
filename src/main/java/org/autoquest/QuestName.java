package org.autoquest;

import org.autoquest.connections.Params;
import org.autoquest.connections.adapters.U1Params;
import org.autoquest.connections.units.MBUnitList;
import org.autoquest.quest.StepsExecuter;
import org.autoquest.service.CommandLineP;
import java.net.UnknownHostException;

public class QuestName {

    public static void main(String[] args) {
        try {
            MBUnitList.init();
            Params.init();
            U1Params.init();
            CommandLineP.parseCL(args);
            MBUnitList.runListener();
            StepsExecuter.start();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

    }
}