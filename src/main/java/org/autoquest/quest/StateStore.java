package org.autoquest.quest;

import org.autoquest.connections.MBParameter;

import java.util.ArrayList;

public class StateStore {
    private static final ArrayList<MBParameter> parametersState  = new ArrayList<>();

    public static void addParameter(MBParameter mbParameter) {
        parametersState.add(mbParameter);
    }

    public static int getStateCode() {
        int code = 1;
        int index = 1;
        for (MBParameter p: parametersState) {
            if (p.getBoolValue()) code = 31 * code + (p == null ? 0 : (p.getName() + index).hashCode());
            index++;
        }
        return code;
    }

    public static ArrayList<MBParameter> getParameterStore() {
        return parametersState;
    }

}
