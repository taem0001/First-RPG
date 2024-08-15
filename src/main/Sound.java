package main;

import javax.sound.sampled.*;
import java.io.File;

public class Sound {
    private Clip clip;
    private File[] soundFiles;

    public Sound() {
        File dir = new File("../res/sound/.");
        soundFiles = dir.listFiles();
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFiles[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}