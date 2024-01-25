/*
    //https://www.tutorialspoint.com/vlcj/vlcj_full_screen_video.htm
*/


package org.autoquest.service.video;

import org.autoquest.service.Global;
import uk.co.caprica.vlcj.factory.AudioOutput;
import uk.co.caprica.vlcj.player.base.AudioDevice;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.component.MediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.fullscreen.adaptive.AdaptiveFullScreenStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.Serial;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;

public class VideoApp extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String VIDEO_PATH;
    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;

//    private List<AudioOutput> outputs;

    public VideoApp(String title) {
        super(title);
        VIDEO_PATH = Global.VIDEO_PATH;
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        mediaPlayerComponent.mediaPlayer().fullScreen().strategy(
                new AdaptiveFullScreenStrategy(this));
//        outputs = mediaPlayerComponent.mediaPlayerFactory().audio().audioOutputs();
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

    public void loadVideo(String path) {
        mediaPlayerComponent.mediaPlayer().media().startPaused(path);
    }

    public void play(String fileName) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println(e);
        }
//        mediaPlayerComponent.mediaPlayer().fullScreen().toggle();
//        File file = new File(getClass().getResource("/video/test.mp4").getPath());
        File file = new File(getClass().getResource("/video/" + fileName).getPath());
        if (VIDEO_PATH.isEmpty()) {
            loadVideo(file.getPath());
        } else {
            loadVideo(VIDEO_PATH);
        }
        setVisible(true);

 //       for(AudioOutput out: outputs) {
 //           System.out.println("VLC AD: " + out);
 //       }
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

     //   mediaPlayerComponent.mediaPlayer().audio().setOutput("sndio");
     //   mediaPlayerComponent.mediaPlayer().audio().setOutputDevice("alsa", "usbstream:CARD=PCH");
     //   System.out.println("VLC AD2: "+ mediaPlayerComponent.mediaPlayer().audio().);
        mediaPlayerComponent.mediaPlayer().audio().setOutput("alsa");
        mediaPlayerComponent.mediaPlayer().audio().setVolume(100);
        mediaPlayerComponent.mediaPlayer().controls().play();

    }

    public void pause() {
        mediaPlayerComponent.mediaPlayer().controls().pause();
    }

    public void skip(int delta) {
        mediaPlayerComponent.mediaPlayer().controls().skipTime(delta);
    }


    public void stop() {
        mediaPlayerComponent.mediaPlayer().controls().stop();
        setVisible(false); //you can't see me!
        dispose();
    }

}
