package org.autoquest.quest;

import org.autoquest.connections.Params;

public class StepSequence {

    Step step1 = new Step();
    Action action1 = new Action();


    public StepSequence() {
        init();
    }

    private void init() {
        action1.defineAction(() -> {
                }
        );
    }
}
