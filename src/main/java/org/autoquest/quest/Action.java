package org.autoquest.quest;

import org.autoquest.connections.MBParameter;
import org.autoquest.connections.MembershipType;
import org.autoquest.connections.ParamType;
import org.autoquest.connections.Params;
import org.autoquest.connections.adapters.Adapter;

import static org.autoquest.connections.units.MBUnitList.WS_MB_UNIT_SLAVE;

public class Action extends Thread {

    private final int scanRate = 300;
    private boolean stopAction;
    boolean stored;
    private int delay = 0;
    private int afterActionDelay = 0;
    private MBParameter statusParam;
    private act doAction;
    private ActionType actionType;
    private String actionName;
    private MBParameter testStart;
    private MBParameter testStop;
    private MBParameter enabled;
    private MBParameter enabledConfirm;
    private String desc = "";

    public Action(String actionName, ActionType actionType) {
        this.actionType = actionType;
        this.actionName = actionName;
        if (actionType.equals(ActionType.STORED))
            StoredActions.addAction(actionName, this);
        this.stored = true;
        initAction();
    }

    public Action(String actionName) {
        this.actionType = ActionType.NON_STORED;
        this.actionName = actionName;
        initAction();
    }

    private void initAction() {
        MBParameter actionStatus = new MBParameter(String.format("ActStatus%s", actionName), WS_MB_UNIT_SLAVE, false, ParamType.CONTROL, MembershipType.SINGLE);
        setStatusParam(actionStatus);
        MBParameter actionTestStart = new MBParameter(String.format("ActTestStart%s", actionName), WS_MB_UNIT_SLAVE, false, ParamType.READ, MembershipType.GROUP);
        setTestStart(actionTestStart);
        if (actionType.equals(ActionType.STORED)) {
            MBParameter actionTestStop = new MBParameter(String.format("ActTestStop%s", actionName), WS_MB_UNIT_SLAVE, false, ParamType.READ, MembershipType.GROUP);
            setTestStop(actionTestStop);
        }
        MBParameter action1Enabled = new MBParameter(String.format("ActEnabled%s", actionName), WS_MB_UNIT_SLAVE, true, ParamType.READ, MembershipType.GROUP);
        MBParameter actionEnabledConfirm = new MBParameter(String.format("ActEnabledCFM%s", actionName), WS_MB_UNIT_SLAVE, true, ParamType.CONTROL, MembershipType.GROUP);
        setEnabled(action1Enabled);
        setEnabledConfirm(actionEnabledConfirm);
        Adapter.setAdapter(action1Enabled, actionEnabledConfirm);
    }

    @Override
    public void run() {
        execute();
    }

    private void execute() {
        if (enabled.getBoolValue()) {
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
            statusParam.setValue(false);
        }
    }

    public void defineAction(act act) {
        doAction = act;
    }

    public void setStatusParam(MBParameter statusParam) {
        this.statusParam = statusParam;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public String getActionName() {
        return actionName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public MBParameter getEnabled() {
        return enabled;
    }

    public void setEnabled(MBParameter enabled) {
        this.enabled = enabled;
    }

    public MBParameter getEnabledConfirm() {
        return enabledConfirm;
    }

    public void setEnabledConfirm(MBParameter enabledConfirm) {
        this.enabledConfirm = enabledConfirm;
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

    public void setTestStart(MBParameter startParam) {
        TestActionList.addAction(startParam, this);
        this.testStart = startParam;
    }

    public void setTestStop(MBParameter stopParam) {
        TestActionList.addAction(stopParam, this);
        this.testStop = stopParam;

    }

    public void setTestParams(MBParameter startParam) {
        TestActionList.addAction(startParam, this);
        this.testStart = startParam;
    }

    public MBParameter getTestStart() {
        return testStart;
    }

    public MBParameter getTestStop() {
        return testStop;
    }

    public MBParameter getStatusParam() {
        return statusParam;
    }
}
