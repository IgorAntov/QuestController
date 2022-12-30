package org.autoquest.quest;

import org.autoquest.connections.SlaveParameterCoil;
import org.autoquest.service.Global;

import java.util.ArrayList;
import java.util.Iterator;

public class Step extends Thread {
    private boolean stepDone;
    private int stepDelay = 0;
    private ArrayList<Action> actions = new ArrayList<>();
    private ArrayList<Transition> transitions = new ArrayList<>();
    private SlaveParameterCoil statusParam;
    protected Step nextStep;
    private String stepName = "";

    @Override
    public void run() {
        execute();
    }

    public void execute() {
        statusParam.setValue(true);
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
        boolean isDone;
        do {
            isDone = true;
            for (Transition t: transitions) {
                isDone = isDone && t.isPassed();
            }
            if (isDone && nextStep != null) {
                goToNextStep();
                break;
            }
            if (isDone) {
                break;
            }
            if (Global.ABORT) {
                break;
            }
        } while (true);
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    public void addTransition(Transition transition) {
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

    public void setStatusParam(SlaveParameterCoil statusParam) {
        this.statusParam = statusParam;
    }

    private void goToNextStep() {
        nextStep.start();
    }

    public void setNextStep(Step step) {
        nextStep = step;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public ArrayList<Transition> getTransitions() {
        return transitions;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

}
