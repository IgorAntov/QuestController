package org.autoquest.quest;

import org.autoquest.connections.MBParameter;

import java.util.ArrayList;

public class ContinuousStepStore {
    private static final ArrayList<Step> continuousSteps  = new ArrayList<>();
    public static void addStep(Step step) {
        continuousSteps.add(step);
    }

    public static void init() {
        for (Step s : continuousSteps) {
            if (s.isRunOnInit()) {
                if (s.getState().equals(Thread.State.NEW)) {
                    s.start();
                } else {
                    synchronized (s.getLock()) {
                        s.getLock().notify();
                    }
                }
            }
        }
    }
}
