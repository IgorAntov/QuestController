package org.autoquest.quest;

import java.util.ArrayList;
import java.util.Iterator;

public class Step extends Thread {

    private boolean stepDone;
    private int stepDelay = 0;
    private ArrayList<Action> actions = new ArrayList<>();
    private ArrayList<Transition> transitions = new ArrayList<>();

    @Override
    public void run() {
        execute();
    }

    public void execute() {

        if (stepDelay > 0) {
            try {
                sleep(stepDelay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            Iterator<Action> iteratorAction = actions.iterator();
            while(iteratorAction.hasNext()) {
                iteratorAction.next().start();
            }
            Iterator<Transition> iteratorTransition = transitions.iterator();
            while(iteratorTransition.hasNext()) {
                Transition transition = iteratorTransition.next();
                transition.start();
            }
        }
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    public void addTransition(Transition transition) {
        transition.setStep(this);
        transitions.add(transition);
    }

    public int getStepDelay() {
        return stepDelay;
    }

    public void setStepDelay(int stepDelay) {
        this.stepDelay = stepDelay;
    }

    public boolean isStepDone() {
        synchronized (this) {
            return stepDone;
        }
    }

    public void setStepDone(boolean stepDone) {
        synchronized (this){
            this.stepDone = stepDone;
        }
    }
}
