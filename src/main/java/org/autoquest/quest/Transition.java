package org.autoquest.quest;

import org.autoquest.connections.SlaveParameterCoil;

public class Transition extends Thread {
    private final int scanRate = 300;
    private boolean passed = false;
    private cnd checkCND;
    private SlaveParameterCoil bypass;
    private SlaveParameterCoil status;
    private String name;

    public Transition(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        do {
            try {
                sleep(scanRate);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (checkCND.apply() || bypass.getValue()) {
                passed = true;
            }
        } while (true);
    }

    public void condition(cnd<Boolean> cnd) {
        checkCND = cnd;
    }

    public String getTransitionName() {
        return name;
    }

    @FunctionalInterface
    public interface cnd<R> {
        boolean apply();
    }

    public boolean isPassed() {
        return this.passed;
    }

    public void setBypassParam(SlaveParameterCoil bypass) {
        this.bypass = bypass;
    }

    public void setStatusParam(SlaveParameterCoil status) {
        this.status = status;
    }


}
