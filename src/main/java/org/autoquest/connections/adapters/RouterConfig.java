package org.autoquest.connections.adapters;

import org.autoquest.quest.steps.Step1;

public class RouterConfig {
    public static void RouterConfigInit() {

//        Adapter.setAdapter(SimulatorParams.KEY2, Params.START);
//        Adapter.setAdapter(Params.ACTION1_ENABLED, Params.ACTION1_ENABLED_CFM);
//        Adapter.setAdapter(Params.ACTION2_ENABLED, Params.ACTION2_ENABLED_CFM);

        Router.setRoute(Step1.ACTION1, SimulatorParams.START);
    }
}
