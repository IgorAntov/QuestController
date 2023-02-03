package org.autoquest.service.SoundPlayer;

import javax.sound.sampled.Clip;
import java.util.ArrayList;

public class ClipStore {

    private static final ArrayList<Clip> CLIP_STORE = new ArrayList<>();

    public static void addClip(Clip clip) {
        CLIP_STORE.add(clip);
    }

    public static void stopAllClips() {
        for (Clip clip: CLIP_STORE) {
            clip.stop();
            clip.drain();
        }
    }

    public static void clearClipStore() {
        CLIP_STORE.clear();
    }

}
