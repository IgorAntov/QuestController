package org.autoquest.service.video;

import org.autoquest.service.audio.AudioApp;

import javax.swing.*;
import java.util.HashMap;

public class VideoPlayer {

    private static final HashMap<String, VideoApp> videoPlayerStore = new HashMap<>();


    public static void play(String fileName) {
        VideoApp application = new VideoApp("Player");
        videoPlayerStore.put(fileName, application);
        application.initialize();
        application.play(fileName);
    }

    public static void stop(String fileName) {
        if (videoPlayerStore.containsKey(fileName)) {
            VideoApp application = videoPlayerStore.get(fileName);
            application.stop();
        }
    }

    public static void pauseClip(String fileName) {
        if (videoPlayerStore.containsKey(fileName)) {
            VideoApp application = videoPlayerStore.get(fileName);
            application.pauseClip();
        }
    }

    public static void skip(String fileName, int delta) {
        if (videoPlayerStore.containsKey(fileName)) {
            VideoApp application = videoPlayerStore.get(fileName);
            application.skip(delta);
        }
    }

    public static void resume(String fileName) {
        if (videoPlayerStore.containsKey(fileName)) {
            VideoApp application = videoPlayerStore.get(fileName);
            application.resume();
        }
    }

    public static void setVolume(String fileName, int volume) {
        if (videoPlayerStore.containsKey(fileName)) {
            VideoApp application = videoPlayerStore.get(fileName);
            application.setVolume(volume);
        }
    }

    public static void stopAllClips() {
        for (VideoApp app : videoPlayerStore.values()) {
            app.stop();
        }
        System.out.println("all video clips were stopped and disposed");
    }

    public static void disposeAllClips() {
        for (VideoApp app : videoPlayerStore.values()) {
            app.disposeAll();
        }
        videoPlayerStore.clear();
        System.out.println("all video clips were stopped and disposed");
    }

    public static void disposeClip(String fileName) {
        if (videoPlayerStore.containsKey(fileName)) {
            VideoApp application = videoPlayerStore.get(fileName);
            application.dispose();
        }
    }

}
