package org.autoquest.connections.adapters;

import org.autoquest.connections.Params;

public class ParamsAdapter {
    public static void ParamsAdapterInit() {

//        Adapter.setAdapter(SimulatorParams.KEY2, Params.START);
        Adapter.setAdapter(Params.ACTION1_ENABLED, Params.ACTION1_ENABLED_CFM);
        Adapter.setAdapter(Params.ACTION2_ENABLED, Params.ACTION2_ENABLED_CFM);

        Adapter.adapterStart();
    }
}
