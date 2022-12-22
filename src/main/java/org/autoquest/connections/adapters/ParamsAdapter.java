package org.autoquest.connections.adapters;

import org.autoquest.connections.Params;

public class ParamsAdapter {
    public static void ParamsAdapterInit() {

        Adapter.setAdapterBoolean(SimulatorParams.KEY2, Params.START);

        Adapter.adapterStart();
    }
}
