package org.autoquest.quest;

import java.io.IOException;

public class Transition extends Thread {
    private final int scanRate = 1000;
    private Step step;
    private final Step nextStep;

    private boolean cndResult;
    public Transition(Step nextStep) {
        this.nextStep = nextStep;
    }

    @Override
    public void run() {
        while (!step.isStepDone()) {
            try {
                sleep(scanRate);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (cndResult) {
                step.setStepDone(true);
                nextStep.start();
            }
        }
    }

    public void condition (cnd<Boolean> cnd) {
        this.cndResult = cnd.apply();
    }

    @FunctionalInterface
    public interface cnd<R> {
        R apply();
    }

    public void setStep(Step step) {
        this.step = step;
    }
}
