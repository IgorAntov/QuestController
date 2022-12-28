package org.autoquest.quest;

import org.autoquest.connections.SlaveParameterCoil;

import java.util.HashMap;

public class TestActionList {

    private static final HashMap<SlaveParameterCoil, Action> storedActions = new HashMap<>();

    public static void addAction(SlaveParameterCoil param, Action action) {
        storedActions.put(param, action);
    }

    public static void getAction(SlaveParameterCoil param) {
        storedActions.get(param);
    }
}
