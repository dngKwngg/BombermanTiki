package uet.oop.bomberman.graphics;

import javafx.scene.media.Media;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static uet.oop.bomberman.BombermanGame.player;

public class Sound extends JFrame {
    public static Clip titleScreen;
    public static Clip bombExplode;
    public static Clip bomberDie;
    public static Clip plantBomb;

    public static boolean isSoundDie = false;

    public Sound(String name, String sound) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            URL url = this.getClass().getClassLoader().getResource(name);
            assert url != null;
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(url);
            if (sound.equals("explosion")) {
                bombExplode = AudioSystem.getClip();
                bombExplode.open(audioInput);
                FloatControl gainControl = (FloatControl) bombExplode.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-8.0f);
                bombExplode.start();
            }

            if (sound.equals("plantBomb")) {
                plantBomb = AudioSystem.getClip();
                plantBomb.open(audioInput);
                FloatControl gainControl = (FloatControl) plantBomb.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(+6.0206f);
                plantBomb.start();
            }

            if (sound.equals("bomberDie")) {
                bomberDie = AudioSystem.getClip();
                bomberDie.open(audioInput);
//                FloatControl gainControl = (FloatControl) bomberDie.getControl(FloatControl.Type.MASTER_GAIN);
//                gainControl.setValue(-8.0f);
                bomberDie.start();
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void updateSound() {
        if (!player.getLife()) {
//            bombExplode.close();
            if (!isSoundDie) {
                new Sound("sound/just_died.wav", "bomberDie");
                isSoundDie = true;
            }
        }
    }
}
