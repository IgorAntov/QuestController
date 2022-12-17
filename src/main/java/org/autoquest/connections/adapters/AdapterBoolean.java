package org.autoquest.connections.adapters;

import org.autoquest.connections.IParameterCoil;

public class AdapterBoolean {
    private final IParameterCoil in;
    private final IParameterCoil out;
    AdapterBoolean(IParameterCoil in, IParameterCoil out) {
        this.in = in;
        this.out = out;
    }

    public IParameterCoil getIn() {
        return this.in;
    }

    public IParameterCoil getOut() {
        return out;
    }
}
