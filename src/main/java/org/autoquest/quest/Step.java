package org.autoquest.quest;

import org.autoquest.connections.MBParameter;
import org.autoquest.connections.MembershipType;
import org.autoquest.connections.ParamType;
import org.autoquest.service.Global;

import java.util.ArrayList;
import java.util.Iterator;

import static org.autoquest.connections.units.MBUnitList.WS_MB_UNIT_SLAVE;

public class Step extends Thread {

    private int scanRate = 300;
    private boolean stepDone;
    private int stepDelay = 0;
    private ArrayList<Action> actions = new ArrayList<>();
    private ArrayList<Transition> transitions = new ArrayList<>();
    private MBParameter statusActive;
    private MBParameter statusDone;
    protected Step nextStep;
    private String stepName = "";
    private boolean continuous;
    private boolean runOnInit;

    public Step(String stepName) {
        this.stepName = stepName;
        MBParameter actionStatus = new MBParameter(String.format("StepStatus%s", stepName), WS_MB_UNIT_SLAVE, false, ParamType.CONTROL, MembershipType.SINGLE);
        setStatusActive(actionStatus);
        MBParameter doneStatus = new MBParameter(String.format("StepDoneStatus%s", stepName), WS_MB_UNIT_SLAVE, false, ParamType.CONTROL, MembershipType.SINGLE);
        setStatusDone(doneStatus);
    }

    @Override
    public void run() {
        System.out.println("Run Thread current: " + getName());
        execute();
    }

    public void execute() {
        statusActive.setValue(true);
        stepDone = false;
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
        statusDone.setValue(false);
        do {
            isDone = true;
            for (Transition t: transitions) {
                isDone = isDone && t.isPassed();
            }
            if (isDone && nextStep != null) {
                goToNextStep();
                stepDone = true;
                statusActive.setValue(false);
                statusDone.setValue(true);
                Thread.currentThread().interrupt();
                if (isInterrupted()) {
                    System.out.println("Stop1 T:" + Thread.currentThread().getName());
                    Thread.currentThread().interrupt();
                    System.out.println("Stop2 T:" + Thread.currentThread().getName());
                }
//                break;
            }
            if (isDone) {
                statusActive.setValue(false);
                statusDone.setValue(true);
                stepDone = true;
                Thread.currentThread().interrupt();
                if (isInterrupted()) {
                    System.out.println("Stop11 T:" + Thread.currentThread().getName());
                    Thread.currentThread().interrupt();
                    System.out.println("Stop12 T:" + Thread.currentThread().getName());
                }
//                break;
            }
            if (Global.ABORT) {
                break;
            }
            try {
                sleep(scanRate);
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
                Thread.currentThread().interrupt();
            }
        } while (true);
        System.out.println("stepDoneFromWhile");
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

    public void setStatusActive(MBParameter statusActive) {
        this.statusActive = statusActive;
    }

    private void goToNextStep() {
        System.out.println("Go to Thread: " + nextStep.getName());
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

    public MBParameter getStatusActive() {
        return statusActive;
    }

    public boolean isContinuous() {
        return continuous;
    }

    public void setContinuous() {
        ContinuousStepStore.addStep(this);
        this.continuous = true;
    }

    public boolean isRunOnInit() {
        return runOnInit;
    }

    public void setRunOnInit() {
        this.runOnInit = true;
    }

    public MBParameter getStatusDone() {
        return statusDone;
    }

    public void setStatusDone(MBParameter statusDone) {
        this.statusDone = statusDone;
    }
}
