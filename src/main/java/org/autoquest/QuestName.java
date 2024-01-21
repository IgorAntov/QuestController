package org.autoquest;

import org.autoquest.connections.Params;
import org.autoquest.connections.adapters.Router;
import org.autoquest.connections.adapters.RouterConfig;
import org.autoquest.connections.adapters.SimulatorParams;
import org.autoquest.connections.units.MBUnitList;
import org.autoquest.quest.*;
import org.autoquest.service.CommandLineP;
import org.autoquest.service.state.SaveStateService;

import java.net.UnknownHostException;

public class QuestName {

    public static void main(String[] args) {
        try {
            System.out.println("Thread current Main: " + Thread.currentThread().getName());
            MBUnitList.init();
            Params.init();
            SimulatorParams.init();
            Configuration.inti();
            MBUnitList.runListener();
            CommandLineP.parseCL(args);
            new SaveStateService();
            QuestTimer.runTimer();
            QuestTimer.pause();
            TestActionStore.actionsStoreInit();
            RouterConfig.RouterConfigInit();
            Router.routerStart();
            StepsExecutor.start();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

    }
}