package org.autoquest.service.sound;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;

public class MixerDeviceStore {
    public static final Mixer.Info[] DEVICES = AudioSystem.getMixerInfo();

    public static Mixer.Info getSoundDevice(int number) {
        return DEVICES[number];
    }

}