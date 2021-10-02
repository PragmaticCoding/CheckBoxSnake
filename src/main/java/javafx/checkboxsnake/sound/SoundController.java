package javafx.checkboxsnake.sound;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.media.AudioClip;

import java.util.Objects;

public class SoundController {

    private static final AudioClip audioClipEat = loadClip("eat2.wav", 1, 1);
    private static final AudioClip audioClipSpecialEat = loadClip("eatSpecial.wav", 1, 1);
    private static final AudioClip audioClipGameOver = loadClip("gameover.mp3", 0.3, 1);
    private static final AudioClip audioClipTimeTick = loadClip("move.wav", 0.6, 2.5);
    private static final AudioClip audioClipSpecialOn = loadClip("specialAppears.wav", 0.5, 1);
    private static final AudioClip audioClipSpecialOff = loadClip("specialGoes.wav", 0.5, 1);

    private static final BooleanProperty soundEnabledProperty = new SimpleBooleanProperty(true);

    public enum Sound {
        EAT,
        GAME_OVER,
        SPECIAL_EAT,
        TIME_TICK,
        SPECIAL_APPEARS,
        SPECIAL_GOES
    }

    private static AudioClip loadClip(String fileName, double volume, double rate) {
        System.out.println(fileName);
        AudioClip audioClip = new AudioClip(Objects.requireNonNull(SoundController.class.getResource("/javafx/checkboxsnake/sounds/" + fileName)).toString());
        audioClip.setRate(rate);
        audioClip.setVolume(volume);
        return audioClip;
    }

    public static BooleanProperty soundEnabledProperty() {
        return soundEnabledProperty;
    }

    public static void playSound(Sound sound) {
        if (soundEnabledProperty.get()) {
            switch (sound) {
                case EAT:
                    audioClipEat.play();
                    break;
                case SPECIAL_EAT:
                    audioClipSpecialEat.play();
                    break;
                case GAME_OVER:
                    audioClipGameOver.play();
                    break;
                case TIME_TICK:
                    audioClipTimeTick.play();
                    break;
                case SPECIAL_APPEARS:
                    audioClipSpecialOn.play();
                    break;
                case SPECIAL_GOES:
                    audioClipSpecialOff.play();
            }
        }
    }
}
