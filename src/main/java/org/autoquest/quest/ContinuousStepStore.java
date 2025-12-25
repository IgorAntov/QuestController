package org.autoquest.quest;

import org.autoquest.connections.MBParameter;

import java.util.ArrayList;
import java.util.Iterator;

public class ContinuousStepStore {
    private static final ArrayList<Step> continuousSteps  = new ArrayList<>();
    private static final ArrayList<Step> steps  = new ArrayList<>();
    public static void addStep(Step step) {
        continuousSteps.add(step);
    }
    public static void addStepToStore(Step step) {
        steps.add(step);
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

    public synchronized static void initSteps() {
        Action action;
        Transition transition;
        for (Step s : steps) {
            Iterator<Transition> iteratorTransition = s.getTransitions().iterator();
            while (iteratorTransition.hasNext()) {
                System.out.println("Steps reset transition");
                transition = iteratorTransition.next();
                transition.getStatus().setValue(false);
            }
            Iterator<Action> iteratorAction = s.getActions().iterator();
            while (iteratorAction.hasNext()) {
                System.out.println("Steps reset action ");
                action = iteratorAction.next();
                action.getStatusParam().setValue(false);
            }
            s.getStatusDone().setValue(false);
        }

    }
}
