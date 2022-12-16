package org.autoquest.quest;

import org.autoquest.connections.SlaveParameterCoil;

public class Action extends Thread {

    private final int scanRate = 1000;
    private boolean stored;
    private boolean stopAction;
    private int delay = 0;
    private int afterDelay = 0;
    private SlaveParameterCoil statusParam;

    private act doAction;
    @Override
    public void run() {
        execute();
    }

    private void execute() {
        statusParam.setValue(true);
        do  {
                try {
                    if ((delay + scanRate) > 0) {
                        sleep(delay);
                    }
                    doAction.apply();
                    if (delay > 0) {
                        sleep(afterDelay);
                    }
                    if (stopAction) {
                        break;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
        } while(stored);
    }

    public void defineAction(act act) {
        doAction = act;
    }

    public void setStatusParam(SlaveParameterCoil statusParam) {
        this.statusParam = statusParam;
    }

    @FunctionalInterface
    public interface act {
        void apply();
    }

    public boolean isStored() {
        return stored;
    }

    public void setStored(boolean stored) {
        this.stored = stored;
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

    public int getAfterDelay() {
        return afterDelay;
    }

    public void setAfterDelay(int afterDelay) {
        this.afterDelay = afterDelay;
    }
}
