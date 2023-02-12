package org.autoquest.service.video;

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
        VideoApp application = videoPlayerStore.get(fileName);
        application.stop();
    }

}
