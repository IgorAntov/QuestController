package org.autoquest.quest.timealarm.secX1;

import org.autoquest.quest.Action;
import org.autoquest.quest.ActionType;
import org.autoquest.quest.Step;
import org.autoquest.service.audio.AudioPlayer;
import org.autoquest.service.sound.MixerDeviceStore;
import org.autoquest.service.sound.Player;

import javax.sound.sampled.AudioPermission;
import javax.sound.sampled.AudioSystem;


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
//            Player.playSound("sound1.mp4", MixerDeviceStore.DEVICES[2]);
            AudioPlayer.play("sound1.mp3");
            System.out.println("ActionTimer1 Done");
        });

        Action action2 = new Action("ActionTimer1A2");
        action2.setDesc("Пропустить шаг 1");
//        action2.defineAction(() -> {
//            Step1.getInstance().skip();
//            System.out.println("ActionTimer1 Done");
//        });

        action2.setDelay(5);
        action2.defineAction(() -> {
/*
            for (int i = 0; i < MixerDeviceStore.DEVICES.length; i++) {
                System.out.println("Audio Devices"+ i + ": " + MixerDeviceStore.DEVICES[i] +
                        ", Name:" + MixerDeviceStore.DEVICES[i].getName() +
                        ", Desc:" + MixerDeviceStore.DEVICES[i].getDescription() +
                        ", Vendor:" + MixerDeviceStore.DEVICES[i].getVendor());
            }
*/
//            Player.playSound("sound2.wav", MixerDeviceStore.DEVICES[2]);
            AudioPlayer.play("sound2.wav", true, 50);
//            Player.playSound("sound1.mp3", MixerDeviceStore.DEVICES[2]);
        });
        addAction(action1);
        addAction(action2);
    }
}
