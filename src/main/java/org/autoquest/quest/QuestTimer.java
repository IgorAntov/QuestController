package org.autoquest.quest;


import org.autoquest.connections.Params;

import java.util.Timer;
import java.util.TimerTask;

public class QuestTimer {

    private static boolean pause = false;
    private static int time = 0;
    private static int hour = 0;
    private static int min = 0;
    private static int sec = 0;

    public static void runTimer() {
        pause = false;
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run() {
                if (StepsExecutor.isQuestRunning()) {
                    if (!pause) {
                        time++;
                    }
                }
                hour = time / 3600;
                min = (time - hour * 3600) / 60;
                sec = time - hour * 3600 - min * 60;
                Params.TIME_HOUR.setValue(hour);
                Params.TIME_MIN.setValue(min);
                Params.TIME_SEC.setValue(sec);
            }
        },1000,1000);

    }

    public static int getTime() {
        return time;
    }

    public static int getHour() {
        return hour;
    }

    public static int getMin() {
        return min;
    }

    public static int getSec() {
        return sec;
    }

    public static void resetTimer() {
        time = 0;
    }

    public static void pause() {
        pause = true;
    }

    public static void reRun() {
        pause = false;
    }

}
