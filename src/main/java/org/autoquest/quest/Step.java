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

    private final Object lock = new Object();

    public Step(String stepName) {
        this.stepName = stepName;
        setName(stepName);
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
            Action action;
            Transition transition;
            do {
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
                    while (iteratorAction.hasNext()) {
                        action = iteratorAction.next();
                        if (action.getState().equals(State.NEW)) {
                            System.out.println(action.getState());
                            action.start();
                        } else {
                            synchronized (action.getLock()) {
                                System.out.println(action.getState());
                                action.getLock().notify();
                            }
                        }
                    }
                    Iterator<Transition> iteratorTransition = transitions.iterator();
                    while (iteratorTransition.hasNext()) {
                        transition = iteratorTransition.next();
                        if (transition.getState().equals(State.NEW)) {
                            transition.start();
                        } else {
                            synchronized (transition.getLock()) {
                                transition.getLock().notify();
                            }
                        }
                    }
                }
                boolean isDone;
                statusDone.setValue(false);
                do {
                    try {
                        isDone = true;
                        for (Transition t : transitions) {
                            isDone = isDone && t.isPassed() && t.getState().equals(State.WAITING);
                        }
                        if (isDone && nextStep != null && (nextStep.getState().equals(State.NEW) || nextStep.getState().equals(State.WAITING))) {
                            if (StepsExecutor.isQuestRunning()) {
                                goToNextStep();
                            }
                            stepDone = true;
                            statusActive.setValue(false);
                            statusDone.setValue(true);
                            System.out.println("Wait Thread: " + getName());
                            synchronized (lock) {
                                lock.wait();
                            }
                            break;
                        }
                        if (isDone) {
                            statusActive.setValue(false);
                            statusDone.setValue(true);
                            stepDone = true;
                            System.out.println("Wait Thread: " + getName());
                            synchronized (lock) {
                               lock.wait();
                            }
                            break;
                        }
                        if (Global.ABORT) {
                            System.out.println("Wait Thread: " + getName());
                            synchronized (lock) {
                                lock.wait();
                            }
                            break;
                        }
                        sleep(scanRate);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } while (true);
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
        synchronized (this) {
            this.stepDone = stepDone;
        }
    }

    public void setStatusActive(MBParameter statusActive) {
        this.statusActive = statusActive;
    }

    private void goToNextStep() {
        System.out.println("Go to Thread: (wake up) " + nextStep.getName() + "status: " + nextStep.getState());
//        nextStep.start();
//        if (nextStep.getState() != Thread.State.WAITING && nextStep.getState() != State.TIMED_WAITING) {
        if (nextStep.getState().equals(State.NEW)) {
            nextStep.start();
        } else {
            synchronized (nextStep.getLock()) {
                nextStep.getLock().notify();
            }
        }
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

    public void launch () {
        if (getState() != Thread.State.WAITING) {
            start();
        };
    }

    public Object getLock() {
        return lock;
    }
}
