package uet.oop.bomberman.graphics;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class Sound extends JFrame {
    public static Clip plantBomb;
    public static Clip explodeBomb;
    public static Clip level1Screen;
    public static Clip bomberDead;

    public Sound(String name, String sound) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);         // used to specify one of several options for the close button
                                                                // Exit the application
        try {
            URL url = this.getClass().getClassLoader().getResource(name);
            assert url != null;

            AudioInputStream audioInput = AudioSystem.getAudioInputStream(url);
            if (sound.equals("explosion")) {
                explodeBomb = AudioSystem.getClip();
                explodeBomb.open(audioInput);
                FloatControl gainControl =(FloatControl)explodeBomb.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-8.0f);
                explodeBomb.start();
            }

            if (sound.equals("bomberDead")) {
                bomberDead = AudioSystem.getClip();
                bomberDead.open(audioInput);
                bomberDead.start();
            }

            if (sound.equals("plantBomb")) {
                plantBomb = AudioSystem.getClip();
                plantBomb.open(audioInput);
                FloatControl gainControl = (FloatControl) plantBomb.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(+6.0206f);
                plantBomb.start();
            }
        } catch (UnsupportedAudioFileException | LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
