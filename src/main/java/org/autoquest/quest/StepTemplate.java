package org.autoquest.quest;

public class StepTemplate {

    protected static final Step step = new Step();

    public static void start() {
        step.start();
    }

    public static Step getPointer() {
        return step;
    }
}
