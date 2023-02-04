package org.autoquest.quest.timealarm.secX1;

import org.autoquest.quest.Action;
import org.autoquest.quest.Step;
import org.autoquest.service.SoundPlayer.MixerDeviceStore;
import org.autoquest.service.SoundPlayer.Player;


public class TimerAction1 extends Step {

    private static final TimerAction1 timerAction11 = new TimerAction1();

    public static TimerAction1 getInstance() {
        return timerAction11;
    }

    private TimerAction1() {
        super("Звук. подсказка.");

        Action action1 = new Action("Action1S4");
        action1.setDesc("Действия 1 S4");

        action1.defineAction(() -> {
            Player.playSound("sound2.wav", MixerDeviceStore.DEVICES[1]);
            System.out.println("ActionTimer1 Done");
        });
        addAction(action1);
    }
}
