package org.autoquest.quest;

import org.autoquest.connections.Params;
import org.autoquest.connections.units.MBUnitSlave;
import org.autoquest.quest.steps.Step1;

import static org.autoquest.connections.units.MBUnitList.WS_MB_UNIT_SLAVE;

public class StepsExecutor {
    private static QuestSeqStatus questSeqStatus = QuestSeqStatus.STOPPED;

    public static void start() {
        try {
            while (true) {
                if (!Params.START.getBoolValue() && questSeqStatus.equals(QuestSeqStatus.STOPPED)) {
                    QuestTimer.reRun();
                    System.out.println("Start Seq");
                    Params.START.farceValue(true);
                    Params.START_FB.setValue(false);
                    questSeqStatus = QuestSeqStatus.RUNNING;
                    Params.ABORT_FB.setValue(true);
                    Params.ABORT.farceValue(true);
                    Params.PAUSE_FB.setValue(true);
                    Params.PAUSE.farceValue(true);
                    Step s = Step1.getInstance();
                    if (s.getState().equals(Thread.State.NEW)) {
                        s.start();
                    } else {
                        synchronized (s.getLock()) {
                            s.getLock().notify();
                        }
                    }
                }
                if (!Params.ABORT.getBoolValue() && (questSeqStatus.equals(QuestSeqStatus.RUNNING) || questSeqStatus.equals(QuestSeqStatus.PAUSED))) {
                    System.out.println("Stopping");
                    questSeqStatus = QuestSeqStatus.STOPPED;
                    Params.ABORT.setValue(false);
                    Params.ABORT_FB.setValue(false);
                    Params.PAUSE_FB.setValue(false);
                    Params.PAUSE.farceValue(false);
                    Params.START.farceValue(true);
                    Params.START_FB.setValue(true);
                    QuestTimer.resetTimer();
                    Thread.sleep(3000);
                    WS_MB_UNIT_SLAVE.setInitValue();
                }
                if (!Params.PAUSE.getBoolValue() && questSeqStatus.equals(QuestSeqStatus.RUNNING)) {
                    System.out.println("Pause");
                    questSeqStatus = QuestSeqStatus.PAUSED;
                    Params.PAUSE_FB.setValue(false);
                    Params.PAUSE.farceValue(false);
                    Params.RERUN.farceValue(true);
                    Params.RERUN_FB.setValue(true);
                    QuestTimer.pause();
                }
                if (!Params.RERUN.getBoolValue() && questSeqStatus.equals(QuestSeqStatus.PAUSED)) {
                    System.out.println("Rerun");
                    questSeqStatus = QuestSeqStatus.RUNNING;
                    Params.PAUSE_FB.setValue(true);
                    Params.PAUSE.farceValue(true);
                    Params.RERUN.farceValue(false);
                    Params.RERUN_FB.setValue(false);
                    QuestTimer.reRun();
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isQuestRunning() {
        return questSeqStatus.equals(QuestSeqStatus.RUNNING) || questSeqStatus.equals(QuestSeqStatus.PAUSED);
    }
}

