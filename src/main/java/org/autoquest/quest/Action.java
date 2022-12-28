package org.autoquest.quest;

import org.autoquest.connections.SlaveParameterCoil;

public class Action extends Thread {

    private final int scanRate = 300;
    private boolean stored;
    private boolean stopAction;
    private int delay = 0;
    private int afterActionDelay = 0;
    private SlaveParameterCoil statusParam;
    private act doAction;
    private ActionType actionType;
    private String actionName;
    private SlaveParameterCoil testStart;
    private SlaveParameterCoil testStop;

    public Action(String actionName, ActionType actionType) {
        this.actionType = actionType;
        this.actionName = actionName;
        if (actionType.equals(ActionType.STORED))
            StoredActions.addAction(actionName, this);
    }

    public Action(String actionName) {
        this.actionType = ActionType.NON_STORED;
        this.actionName = actionName;
    }

    @Override
    public void run() {
        execute();
    }

    private void execute() {
        statusParam.setValue(true);
        do {
            try {
                if ((delay + scanRate) > 0) {
                    sleep(delay);
                }
                doAction.apply();
                if (afterActionDelay > 0) {
                    sleep(afterActionDelay);
                }
                if (stopAction) {
                    break;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (stored);
    }

    public void defineAction(act act) {
        doAction = act;
    }

    public void setStatusParam(SlaveParameterCoil statusParam) {
        this.statusParam = statusParam;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public String getActionName() {
        return actionName;
    }

    @FunctionalInterface
    public interface act {
        void apply();
    }

    public boolean isStored() {
        return stored;
    }

    public boolean isStopAction() {
        return stopAction;
    }

    public void setStopAction(boolean stopAction) {
        this.stopAction = stopAction;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getAfterActionDelay() {
        return afterActionDelay;
    }

    public void setAfterActionDelay(int afterActionDelay) {
        this.afterActionDelay = afterActionDelay;
    }

    public void setTestParams(SlaveParameterCoil startParam, SlaveParameterCoil stopParam) {
        TestActionList.addAction(startParam, this);
         this.testStart = startParam;
        TestActionList.addAction(stopParam, this);
        this.testStop = stopParam;
    }
    public void setTestParams(SlaveParameterCoil startParam) {
        TestActionList.addAction(startParam, this);
        this.testStart = startParam;
    }

    public SlaveParameterCoil getTestStart() {
        return testStart;
    }

    public SlaveParameterCoil getTestStop() {
        return testStop;
    }

    public SlaveParameterCoil getStatusParam() {
        return statusParam;
    }
}
