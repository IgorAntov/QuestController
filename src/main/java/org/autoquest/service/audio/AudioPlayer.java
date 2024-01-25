package org.autoquest.service.audio;

import org.autoquest.service.video.VideoApp;

import java.util.HashMap;

public class AudioPlayer {

    private static final HashMap<String, AudioApp> audioPlayerStore = new HashMap<>();

    public static void play(String fileName) {
        play(fileName, false, 100);
    }

    public static void play(String fileName, boolean loop) {
        play(fileName, loop, 100);
    }

    public static void play(String fileName, boolean loop, int volume) {
        AudioApp application = new AudioApp("Player");
        audioPlayerStore.put(fileName, application);
        application.initialize();
        application.play(fileName, loop, volume);
    }

    public static void stop(String fileName) {
        AudioApp application = audioPlayerStore.get(fileName);
        application.stop();
    }
}
