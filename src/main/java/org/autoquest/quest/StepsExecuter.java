package org.autoquest.quest;

import org.autoquest.connections.Params;
import org.autoquest.quest.steps.Step1;

public class StepsExecuter {

    public static void start() {
        try {
            while (true) {
                if (Params.START.getValue()) {
                    Params.ABORT.setValue(false);
                    Step1.start();
                    Thread.sleep(1000);
                    Params.START.setValue(false);
                    Thread.sleep(1000);
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

