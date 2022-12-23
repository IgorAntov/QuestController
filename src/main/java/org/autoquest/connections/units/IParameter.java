package org.autoquest.connections.units;

import org.autoquest.connections.ParamType;

public interface IParameter {
    String getName();

    int getChannelNumber();

    ParamType getParamType();
}
