package org.autoquest.service.video;

import javax.swing.*;
import java.util.HashMap;

public class VideoPlayer {

    private static final HashMap<String, VideoApp> videoPlayerStore = new HashMap<>();


    public static void play(String fileName) {
        VideoApp application = new VideoApp("Player");
        videoPlayerStore.put(fileName, application);
        System.out.println("SizePlay:" + videoPlayerStore.size());
        application.initialize();
        application.play(fileName);
    }

    public static void stop(String fileName) {
        System.out.println("Want to stop 1");
        System.out.println("SizeStop:" + videoPlayerStore.size());
        VideoApp application = videoPlayerStore.get(fileName);
        application.stop();
    }

}
