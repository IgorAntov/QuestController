package org.autoquest.quest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class StoredActions {

    private static final HashMap<String, Action> storedActions = new HashMap<>();

    public static void addAction(String name, Action action) {
        storedActions.put(name, action);
    }

    public static Action getAction(String name) {
        return storedActions.get(name);
    }
}
