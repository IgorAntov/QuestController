package org.autoquest.quest;

import org.autoquest.connections.Params;
import org.autoquest.quest.steps.Step1;

public class StepsExecutor {
    private static QuestSeqStatus questSeqStatus = QuestSeqStatus.STOPPED;

    public static void start() {
        try {
            while (true) {
                if (!Params.START.getBoolValue() && questSeqStatus.equals(QuestSeqStatus.STOPPED)) {
                    if (QuestTimer.getTime() == 0) {
                        QuestTimer.runTimer();
                    }
                    System.out.println("Start Seq");
                    Params.START.setValue(true);
                    Params.START_FB.setValue(false);
                    questSeqStatus = QuestSeqStatus.RUNNING;
                    Params.ABORT_FB.setValue(true);
                    Step s = Step1.getInstance();
                    if (s.getState().equals(Thread.State.NEW)) {
                        s.start();
                    } else {
                        synchronized (s.getLock()) {
                            s.getLock().notify();
                        }
                    }
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

