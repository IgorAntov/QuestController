package org.autoquest.quest;

import org.autoquest.connections.Params;

public class StepsExecuter {

    void start() {
        try {
            while (true) {
                if (Params.START.getValue()) {
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

