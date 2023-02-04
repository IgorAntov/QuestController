package org.autoquest.service.SoundPlayer;

import org.apache.commons.io.FilenameUtils;
import org.autoquest.quest.Action;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Objects;

import static org.autoquest.service.SoundPlayer.Converter.convertFrom;

public class Player {

    public static synchronized void playSound(final String url, Mixer.Info info) {
        playSound(url, info, false);
    }

    public static synchronized void playSound(final String url, Mixer.Info info, boolean loop) {
        playSound(url, info, loop, 100);
    }

    public static synchronized void playSound(final String url, Mixer.Info info, boolean loop, int volume) {
        String ext = FilenameUtils.getExtension(url);
        if (ext.equals("mp3")) {
            playMp3Sound(url, info, loop, volume);
        } else {
            playWavSound(url, info, loop, volume);
        }
    }

    public static void playWavSound(final String url, Mixer.Info info, boolean loop, int volume) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip(info);
                    ClipStore.addClip(clip);
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            Objects.requireNonNull(getClass().getResourceAsStream("/sounds/" + url)));
                    clip.open(inputStream);
                    FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    float volumeLevel = volume /100.0f;
                    volumeControl.setValue(volumeControl.getMaximum() - (volumeControl.getMaximum() - volumeControl.getMinimum()) * (1.0f - volumeLevel));
                    if (loop) {
                        clip.loop(1000);
                    }
                    clip.start();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

    public static void playMp3Sound(final String url, Mixer.Info info, boolean loop, int volume) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip(info);
                    ClipStore.addClip(clip);
                    try (
                            final InputStream inputStream = getClass().getResourceAsStream("/sounds/" + url);
                            final ByteArrayOutputStream output = new ByteArrayOutputStream();
                    ) {
                        final AudioFormat audioFormat = new AudioFormat(44100, 8, 1, false, false);
                        System.out.println(inputStream == null);
                        convertFrom(inputStream).withTargetFormat(audioFormat).to(output);
                        final byte[] wavContent = output.toByteArray();
                        try {

                            AudioInputStream inputStream2 = AudioSystem.getAudioInputStream(
                                    new ByteArrayInputStream(wavContent)
                            );
                            clip.open(inputStream2);
                            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                            float volumeLevel = volume /100.0f;
                            volumeControl.setValue(volumeControl.getMaximum() - (volumeControl.getMaximum() - volumeControl.getMinimum()) * (1.0f - volumeLevel));
                            if (loop) {
                                clip.loop(100);
                            }
                            clip.start();
                        } catch (UnsupportedAudioFileException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }).start();
    }

}
