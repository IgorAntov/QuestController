package org.autoquest.service.audio;

import org.autoquest.quest.QuestSeqStatus;
import org.autoquest.quest.StepsExecutor;
import org.autoquest.service.video.VideoApp;

import javax.sound.sampled.Clip;
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
        //      if (StepsExecutor.isQuestRunning()) {
        AudioApp application = new AudioApp("Player");
        audioPlayerStore.put(fileName, application);
        application.initialize();
        application.play(fileName, loop, volume);
        //     }
    }

    public static void stop(String fileName) {
        if (audioPlayerStore.containsKey(fileName)) {
            AudioApp application = audioPlayerStore.get(fileName);
            application.stop();
        }
    }

    public static void pause(String fileName) {
        if (audioPlayerStore.containsKey(fileName)) {
            AudioApp application = audioPlayerStore.get(fileName);
            application.pause();
        }
    }

    public static void resume(String fileName) {
        if (audioPlayerStore.containsKey(fileName)) {
            AudioApp application = audioPlayerStore.get(fileName);
            application.resume();
        }
    }

    public static void skipTime(String fileName, int delta) {
        if (audioPlayerStore.containsKey(fileName)) {
            AudioApp application = audioPlayerStore.get(fileName);
            application.skipTime(delta);
        }
    }

    public static void stopAllClips() {
        for (AudioApp app : audioPlayerStore.values()) {
            app.stop();
        }
        System.out.println("all audio clips were stopped");
    }

    public static void disposeAllClips() {
        for (AudioApp app : audioPlayerStore.values()) {
            app.disposeApp();
        }
        audioPlayerStore.clear();
        System.out.println("all audio clips were disposed");
    }
}
