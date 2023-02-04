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
//                        System.out.println("Yes test " + p.getBoolValue());

                        if (p.getBoolValue()) {
//                            System.out.println("Yes test" );
                            if (!actions.get(p).getStatusParam().getBoolValue()) {
                                if (actions.get(p).getState().equals(Thread.State.NEW)) {
                                    actions.get(p).start();
                                } else {
                                    synchronized (actions.get(p).getLock()) {
                                        actions.get(p).getLock().notify();
                                    }
                                }
//                                actions.get(p).start();
                            } else actions.get(p).stopAction();
                            p.setValue(false);
                        }
                    }
                    sleep(2000);
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
