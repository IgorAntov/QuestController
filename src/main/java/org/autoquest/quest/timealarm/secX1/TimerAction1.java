package org.autoquest.quest.timealarm.secX1;

import org.autoquest.quest.Action;
import org.autoquest.quest.ActionType;
import org.autoquest.quest.Step;
import org.autoquest.service.sound.MixerDeviceStore;
import org.autoquest.service.sound.Player;


public class TimerAction1 extends Step {

    private static final TimerAction1 timerAction11 = new TimerAction1();

    public static TimerAction1 getInstance() {
        return timerAction11;
    }

    private TimerAction1() {
        super("Звук. подсказка.");
//        setContinuous();

        Action action1 = new Action("ActionTimer1A1");
        action1.setDesc("Звук Подсказка");
        action1.defineAction(() -> {
//            Player.playSound("sound2.wav", MixerDeviceStore.DEVICES[2]);
            System.out.println("ActionTimer1 Done");
        });

        Action action2 = new Action("ActionTimer1A2");
        action2.setDesc("Пропустить шаг 1");
//        action2.defineAction(() -> {
//            Step1.getInstance().skeep();
//            System.out.println("ActionTimer1 Done");
//        });
        action2.defineAction(() -> {
            System.out.println("Audio Devices[0]:" + MixerDeviceStore.DEVICES[0]);
            System.out.println("Audio Devices[1]:" + MixerDeviceStore.DEVICES[1]);
            System.out.println("Audio Devices[2]:" + MixerDeviceStore.DEVICES[2]);
            System.out.println("Audio Devices[2].name:" + MixerDeviceStore.DEVICES[2].getName());
            System.out.println("Audio Devices[2].desc:" + MixerDeviceStore.DEVICES[2].getDescription());
            System.out.println("Audio Devices[2].vendor:" + MixerDeviceStore.DEVICES[2].getVendor());
            System.out.println("Audio Devices[2].hashCode:" + MixerDeviceStore.DEVICES[2].hashCode());
            Player.playSound("sound2.wav", MixerDeviceStore.DEVICES[2]);

            action2.setDelay(10);
//            Player.playSound("sound1.mp3", MixerDeviceStore.DEVICES[2]);
//            System.out.println("ActionTimer1 Done");
        });


        addAction(action1);
        addAction(action2);
    }
}
