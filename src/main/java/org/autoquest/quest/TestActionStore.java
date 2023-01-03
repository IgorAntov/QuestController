package org.autoquest.quest;

import org.autoquest.connections.MBParameter;

import java.util.HashMap;

public class TestActionStore {

    private static final HashMap<MBParameter, Action> actions = new HashMap<>();

    public static void addAction(MBParameter parameter, Action action) {
        actions.put(parameter, action);
    }

    public static void actionsStoreInit() {
        new ActionStoreControl().watch();
    }

    private static class ActionStoreControl extends Thread {

        @Override
        public void run() {
            try {
                do {
                    for (MBParameter p : actions.keySet()) {
                        if (p.getBoolValue()) {
                            if (actions.get(p).getStatusParam().getBoolValue()) {
                                actions.get(p).start();
                            } else actions.get(p).stopAction();
                            p.setValue(false);
                        }
                    }
                    sleep(100);
                } while (true);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public void watch() {
            start();
        }
    }

}
