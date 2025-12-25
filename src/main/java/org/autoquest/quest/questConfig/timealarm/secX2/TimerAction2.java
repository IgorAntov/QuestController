package org.autoquest.quest.questConfig.timealarm.secX2;

import org.autoquest.quest.Action;
import org.autoquest.quest.Step;
import org.autoquest.service.video.VideoPlayer;


public class TimerAction2 extends Step {

    private static final TimerAction2 timerAction11 = new TimerAction2();

    public static TimerAction2 getInstance() {
        return timerAction11;
    }

    private TimerAction2() {
        super("Звук. подсказка.");

        Action action1 = new Action("ActionTimer2A1");
        action1.setActionNick("Звук Подсказка Шаг2 дйствие1");
        action1.setDesc("Звук Подсказка Шаг2 Описание");
        action1.defineAction(() -> {
            System.out.println("ActionTimer2 Done");
        });

        addAction(action1);
    }
}
