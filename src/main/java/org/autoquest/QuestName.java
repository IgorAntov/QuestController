package org.autoquest;

import org.autoquest.connections.Params;
import org.autoquest.connections.adapters.ParamsAdapter;
import org.autoquest.connections.adapters.SimulatorParams;
import org.autoquest.connections.units.MBUnitList;
import org.autoquest.quest.Configuration;
import org.autoquest.quest.ContinuousStepStore;
import org.autoquest.quest.StepsExecuter;
import org.autoquest.service.CommandLineP;
import java.net.UnknownHostException;

public class QuestName {

    public static void main(String[] args) {
        try {
            System.out.println("Thread current Main: " + Thread.currentThread().getName());
            MBUnitList.init();
            Params.init();
            SimulatorParams.init();
            ParamsAdapter.ParamsAdapterInit();
            Configuration.inti();
            MBUnitList.runListener();
            CommandLineP.parseCL(args);
            ContinuousStepStore.init();
            StepsExecuter.start();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

    }
}