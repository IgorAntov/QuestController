package org.autoquest.quest.timealarm.secX1;

import org.autoquest.quest.Action;
import org.autoquest.quest.ActionType;
import org.autoquest.quest.Step;
import org.autoquest.quest.steps.Step1;
import org.autoquest.service.SoundPlayer.MixerDeviceStore;
import org.autoquest.service.SoundPlayer.Player;


public class TimerAction1 extends Step {

    private static final TimerAction1 timerAction11 = new TimerAction1();

    public static TimerAction1 getInstance() {
        return timerAction11;
    }

    private TimerAction1() {
        super("Звук. подсказка.");
        setContinuous();

        Action action1 = new Action("ActionTimer1A1", ActionType.STORED);
        action1.setDesc("Звук Подсказка");
        action1.defineAction(() -> {
            Player.playSound("sound2.wav", MixerDeviceStore.DEVICES[1], true);
            System.out.println("ActionTimer1 Done");
        });

        Action action2 = new Action("ActionTimer1A2");
        action2.setDesc("Пропустить шаг 1");
        action2.defineAction(() -> {
            Step1.getInstance().skeep();
            System.out.println("ActionTimer1 Done");
        });

        addAction(action1);
        addAction(action2);
    }
}
