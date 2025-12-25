package org.autoquest.quest.questConfig.timealarm.secX1;

import org.autoquest.quest.Action;
import org.autoquest.quest.Step;
import org.autoquest.service.audio.AudioPlayer;
import org.autoquest.service.video.VideoPlayer;


public class TimerAction1 extends Step {

    private static final TimerAction1 timerAction11 = new TimerAction1();

    public static TimerAction1 getInstance() {
        return timerAction11;
    }

    private TimerAction1() {
        super("Звук. подсказка.");
//        setContinuous();

        Action action1 = new Action("ActionTimer1A1");
        action1.setActionNick("Звук Подсказка дйствие1");
        action1.setDesc("Звук Подсказка Описание");
        action1.defineAction(() -> {
            //   Player.playSound("sound1.mp4", MixerDeviceStore.DEVICES[2]);
   //!!         AudioPlayer.play("sound1.mp3");
            //         VideoPlayer.resume("test.mp4");
            System.out.println("ActionTimer1 Done");
        });

        Action action2 = new Action("ActionTimer1A2");
        action2.setActionNick("Звук Подсказка дйствие2");
        action2.setDesc("Звук Подсказка дйствие2 описание");
//        action2.defineAction(() -> {
//            Step1.getInstance().skip();
//            System.out.println("ActionTimer1 Done");
//        });

//        action2.setDelay(5);
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
            System.out.println("Action 2 start");
//!!            AudioPlayer.play("sound2.wav", false, 100);
//            Player.playSound("sound1.mp3", MixerDeviceStore.DEVICES[2]);
   //         try {
   //             sleep(2000);
   //         } catch (InterruptedException e) {
   //             throw new RuntimeException(e);
   //         }
   //         System.out.println("Pause audio");
   //         AudioPlayer.pause("sound2.wav");
   //         try {
   //             sleep(5000);
   //         } catch (InterruptedException e) {
   //             throw new RuntimeException(e);
   //         }
 //           AudioPlayer.skipTime("sound2.wav", 1000);
 //           System.out.println("Resume");
 //           AudioPlayer.resume("sound2.wav");
            VideoPlayer.disposeClip("test.mp4");
            //VideoPlayer. resume("test.mp4");
        });
        addAction(action1);
        addAction(action2);
    }
}
