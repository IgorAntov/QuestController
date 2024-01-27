package org.autoquest.service.audio;

import org.autoquest.service.Global;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.medialist.MediaList;
import uk.co.caprica.vlcj.medialist.MediaListRef;
import uk.co.caprica.vlcj.player.base.Equalizer;
import uk.co.caprica.vlcj.player.component.*;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.fullscreen.adaptive.AdaptiveFullScreenStrategy;
import uk.co.caprica.vlcj.player.list.MediaListPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.Serial;
import java.util.List;

import static uk.co.caprica.vlcj.medialist.MediaListFactory.newMediaList;
import static uk.co.caprica.vlcj.player.list.PlaybackMode.LOOP;

public class AudioApp extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String AUDIO_PATH;

    private final Equalizer equalizer;
    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
    private final AudioPlayerComponent audioPlayerComponent;
    //    private final AudioListPlayerComponent audioListPlayerComponent;
//    private final MediaListPlayer mediaListPlayer;
    private boolean isLoop = false;

    MediaListPlayer mediaListPlayer;
    MediaPlayerFactory mediaPlayerFactory;
    EmbeddedMediaPlayer mediaPlayer;

    public AudioApp(String title) {
        super(title);
        AUDIO_PATH = Global.SOUND_PATH;
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        audioPlayerComponent = new AudioPlayerComponent();

//        audioListPlayerComponent = new AudioListPlayerComponent();

        mediaPlayerComponent.mediaPlayer().fullScreen().strategy(new AdaptiveFullScreenStrategy(this));
//        AudioListPlayerComponent audioListPlayer = mediaPlayerFactory.newMediaListPlayer();
        List<String> presetNames = mediaPlayerComponent.mediaPlayerFactory().equalizer().presets();
        System.out.println(presetNames);
        equalizer = mediaPlayerComponent.mediaPlayerFactory().equalizer().newEqualizer("Dance");
//        outputs = mediaPlayerComponent.mediaPlayerFactory().audio().audioOutputs();

        mediaPlayerFactory = new MediaPlayerFactory();
        mediaListPlayer = mediaPlayerFactory.mediaPlayers().newMediaListPlayer();
        mediaPlayer = mediaPlayerFactory.mediaPlayers().newEmbeddedMediaPlayer();
        mediaListPlayer.mediaPlayer().setMediaPlayer(mediaPlayer);
        audioPlayerComponent.mediaPlayer().audio().setOutput("alsa");
    }

    public void initialize() {
        this.setBounds(100, 100, 600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mediaPlayerComponent.release();
                System.exit(0);
            }
        });
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(mediaPlayerComponent, BorderLayout.CENTER);

        JPanel controlsPane = new JPanel();
        JButton playButton = new JButton("Play");
        //        controlsPane.add(playButton);
        //       contentPane.add(controlsPane, BorderLayout.SOUTH);
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mediaPlayerComponent.mediaPlayer().controls().play();
            }
        });
        this.setContentPane(contentPane);
        this.setVisible(true);
    }

    public void loadAudio(String path) {
        //       audioPlayerComponent.mediaPlayer().audio().setOutputDevice();
        audioPlayerComponent.mediaPlayer().media().startPaused(path);
    }

    public void play(String fileName, boolean loop, int volume) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e);
        }
//        mediaPlayerComponent.mediaPlayer().fullScreen().toggle();
//        File file = new File(getClass().getResource("/video/test.mp4").getPath());

/*
        for (AudioOutput i : outputs) {
            System.out.println("OUTPUT: " + i);
            List<AudioDevice> devices = i.getDevices();
            if (!devices.isEmpty()) {
                for (AudioDevice itdev : devices) {
                    System.out.println("Audio DevId:" + itdev.getDeviceId());
                }
            }
        }
*/

        if (loop) {
            isLoop = true;
            String[] options = {};
            MediaListRef mediaListRef = mediaPlayerFactory.media().newMediaListRef();
            File file = new File(getClass().getResource("/sounds/" + fileName).getPath());
            if (AUDIO_PATH.isEmpty()) {
                loadAudio(file.getPath());
                mediaListRef.newMediaList().media().add(file.getPath(), options);
            } else {
                loadAudio(AUDIO_PATH + fileName);
                mediaListRef.newMediaList().media().add(AUDIO_PATH + fileName, options);
            }
            setVisible(true);
            mediaListPlayer.list().setMediaList(mediaListRef);
            mediaListPlayer.controls().setMode(LOOP);
//            mediaListPlayer.mediaPlayer().mediaPlayer().audio().setOutput("alsa");
            mediaListPlayer.mediaPlayer().mediaPlayer().audio().setVolume(volume);
            mediaListPlayer.controls().play();
        }
        if (!loop) {
//      mediaPlayerComponent.mediaPlayer().audio().setOutput("sndio");

            File file = new File(getClass().getResource("/sounds/" + fileName).getPath());
            if (AUDIO_PATH.isEmpty()) {
                loadAudio(file.getPath());
            } else {
                loadAudio(AUDIO_PATH + fileName);
            }
            setVisible(true);
            audioPlayerComponent.mediaPlayer().audio().setVolume(volume);
            audioPlayerComponent.mediaPlayer().controls().play();
//            mediaListPlayer.controls().play();
        }
    }

    public void stop() {
        if (!isLoop) {
            audioPlayerComponent.mediaPlayer().controls().stop();
        }
        if (isLoop) {
            mediaListPlayer.controls().stop();
        }
 //       setVisible(false); //you can't see me!
//        dispose();
    }

    public void pause() {
        if (!isLoop) {
            audioPlayerComponent.mediaPlayer().controls().pause();
        }
        if (isLoop) {
            mediaListPlayer.controls().pause();
        }
    }

    public void skipTime(int delta) {
        if (!isLoop) {
            audioPlayerComponent.mediaPlayer().controls().skipTime(delta);
        }
    }

    public void resume() {
        if (!isLoop) {
            audioPlayerComponent.mediaPlayer().controls().play();
        }
        if (isLoop) {
            mediaListPlayer.controls().play();
        }
    }

    public void disposeApp() {
        setVisible(false);
        dispose();
    }
}
