package org.autoquest.connections.adapters;

import org.autoquest.connections.MBParameter;

public class AdapterParameter {
    private final MBParameter in;
    private final MBParameter out;
    AdapterParameter(MBParameter in, MBParameter out) {
        this.in = in;
        this.out = out;
    }

    public MBParameter getIn() {
        return this.in;
    }

    public MBParameter getOut() {
        return out;
    }
}
