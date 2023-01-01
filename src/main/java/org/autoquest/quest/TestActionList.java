package org.autoquest.quest;

import org.autoquest.connections.MBParameter;
import org.autoquest.connections.SlaveParameterCoil;

import java.util.HashMap;

public class TestActionList {

    private static final HashMap<MBParameter, Action> storedActions = new HashMap<>();

    public static void addAction(MBParameter param, Action action) {
        storedActions.put(param, action);
    }

    public static void getAction(MBParameter param) {
        storedActions.get(param);
    }
}
