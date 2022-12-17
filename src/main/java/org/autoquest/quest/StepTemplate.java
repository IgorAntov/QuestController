package org.autoquest.quest;

import org.autoquest.connections.Params;
import org.autoquest.quest.steps.StepLast;

public class StepTemplate {

    protected static final Step step = new Step();

    public StepTemplate() {
        Transition lastTransition = new Transition(new StepLast());
//        lastTransition.condition(Params.ABORT::getValue);
        lastTransition.condition(()->true);

        step.addTransition(lastTransition);
    }

    public static void start() {
        step.start();
    }

    public static Step getPointer() {
        return step;
    }
}
