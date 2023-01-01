package org.autoquest.connections.adapters;

import org.autoquest.connections.ParamType;
import org.autoquest.connections.Params;

public class ParamsAdapter {
    public static void ParamsAdapterInit() {

//        Adapter.setAdapter(SimulatorParams.KEY2, Params.START);
        Adapter.setAdapter(Params.ACTION1_ENABLED2, Params.ACTION1_ENABLED_CFM2);
        Adapter.setAdapter(Params.ACTION2_ENABLED2, Params.ACTION2_ENABLED_CFM2);

        Adapter.adapterStart();
    }
}
