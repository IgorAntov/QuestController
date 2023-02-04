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
                            if (actions.get(p).getTestStart().getBoolValue() && !actions.get(p).isTestMode()) {
                                if (actions.get(p).getState().equals(Thread.State.NEW)) {
                                    actions.get(p).start();
                                } else {
                                    synchronized (actions.get(p).getLock()) {
                                        actions.get(p).getLock().notify();
                                    }
                                }
                                actions.get(p).setTestMode(true);
                            } else if (!actions.get(p).getTestStart().getBoolValue() && actions.get(p).isTestMode()) {
                                actions.get(p).setTestMode(false);
                            }
                            if (actions.get(p).getTestStop().getBoolValue()) {
                                actions.get(p).stopAction();
                                actions.get(p).setTestMode(false);
                                actions.get(p).getTestStart().farceValue(false);
                                p.farceValue(false);
                            }
                    }
                    sleep(500);
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
