package org.autoquest.quest;

import org.autoquest.connections.Params;
import org.autoquest.quest.questConfig.steps.Step1;
import org.autoquest.service.Global;
import org.autoquest.service.audio.AudioPlayer;
import org.autoquest.service.state.ReadXML;
import org.autoquest.service.video.VideoPlayer;

import static org.autoquest.quest.questConfig.mbunits.MBUnitList.WS_MB_UNIT_SLAVE;

public class StepsExecutor {
    private static QuestSeqStatus questSeqStatus = QuestSeqStatus.STOPPED;

    public static void start() {
        try {
            while (true) {
               // System.out.println(questSeqStatus.name());
                if (Params.START.getBoolValue() && questSeqStatus.equals(QuestSeqStatus.STOPPED)
                ) {
                    QuestTimer.reRun();
                    System.out.println("Start Seq");
                    Params.START.farceValue(true);
                    Params.START_FB.setValue(false);
                    questSeqStatus = QuestSeqStatus.RUNNING;
                    Params.ABORT_FB.setValue(true);
                    Params.ABORT.farceValue(false);
                    Params.PAUSE_FB.setValue(true);
                    Params.PAUSE.farceValue(false);

                    ContinuousStepStore.init();
                    Global.resetStepNumber();
                    Global.increaseStepNumber();
                    Step s = Step1.getInstance();
                    if (s.getState().equals(Thread.State.NEW)) {
                        s.start();
                    } else {
                        synchronized (s.getLock()) {
                            s.getLock().notify();
                        }
                    }

                }
                if (Params.ABORT.getBoolValue() && (questSeqStatus.equals(QuestSeqStatus.RUNNING) || questSeqStatus.equals(QuestSeqStatus.PAUSED))) {
                    stopQuest();
//                    System.out.println("Stopping");
//                    questSeqStatus = QuestSeqStatus.STOPPED;
//                    Params.ABORT.setValue(false);
//                    Params.ABORT_FB.setValue(false);
//                    Params.PAUSE_FB.setValue(false);
//                    Params.PAUSE.farceValue(false);
//                    Params.START.farceValue(false);
//                    Params.START_FB.setValue(true);
//                    QuestTimer.resetTimer();
//                    Global.resetStepNumber();
//                    ClipStore.stopAllClips();
//                    ClipStore.clearClipStore();
//                    Thread.sleep(3000);
//                    WS_MB_UNIT_SLAVE.setInitValue();
//                    ReadXML.readParametersFromXML(StateStore.getParameterStore());
                }
                if (Params.PAUSE.getBoolValue() && questSeqStatus.equals(QuestSeqStatus.RUNNING)) {
                    System.out.println("Pause");
                    questSeqStatus = QuestSeqStatus.PAUSED;
                    Params.PAUSE_FB.setValue(false);
                    Params.PAUSE.farceValue(false);
                    Params.RERUN.farceValue(false);
                    Params.RERUN_FB.setValue(true);
                    QuestTimer.pause();
                }
                if (Params.RERUN.getBoolValue() && questSeqStatus.equals(QuestSeqStatus.PAUSED)) {
                    System.out.println("Rerun");
                    questSeqStatus = QuestSeqStatus.RUNNING;
                    Params.PAUSE_FB.setValue(true);
                    Params.PAUSE.farceValue(false);
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

    public static void stopQuest() {
        try {
        System.out.println("Stopping");
    //    questSeqStatus = QuestSeqStatus.STOPPING;
     //   Params.ABORT.setValue(false);
     //   Params.ABORT_FB.setValue(false);
        Params.PAUSE_FB.setValue(false);
        Params.PAUSE.farceValue(false);
        Params.START.farceValue(false);
        Params.START_FB.setValue(true);
        QuestTimer.pause();
        QuestTimer.resetTimer();
        Global.resetStepNumber();
//        Thread.sleep(1000);
//        ClipStore.stopAllClips();
//        ClipStore.clearClipStore();
        AudioPlayer.stopAllClips();
        AudioPlayer.disposeAllClips();
        VideoPlayer.stopAllClips();
        VideoPlayer.disposeAllClips();//       Thread.sleep(3000);
        WS_MB_UNIT_SLAVE.setInitValue();
        ReadXML.readParametersFromXML(StateStore.getParameterStore());
    //        Thread.sleep(2000);
        questSeqStatus = QuestSeqStatus.STOPPED;
            ContinuousStepStore.init();
        Thread.sleep(100);
    //    Params.START_FB.setValue(true);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
       }
    }
}

