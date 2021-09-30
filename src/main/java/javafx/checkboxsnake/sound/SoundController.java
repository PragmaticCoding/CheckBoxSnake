package javafx.checkboxsnake.sound;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.media.AudioClip;

import java.util.Objects;

/**
 * --- here javadoc ---
 *
 * @author dnolte
 */
public class SoundController {

    private static final AudioClip audioClipEat = new AudioClip(Objects.requireNonNull(SoundController.class.getResource("/javafx/checkboxsnake/sounds/eat2.wav")).toString());
    private static final AudioClip audioClipSpecialEat = new AudioClip(Objects.requireNonNull(SoundController.class.getResource("/javafx/checkboxsnake/sounds/eatSpecial.wav")).toString());
    private static final AudioClip audioClipGameOver = new AudioClip(Objects.requireNonNull(SoundController.class.getResource("/javafx/checkboxsnake/sounds/gameover.mp3")).toString());
    private static final AudioClip audioClipTimeTick = new AudioClip(Objects.requireNonNull(SoundController.class.getResource("/javafx/checkboxsnake/sounds/move.wav")).toString());

    private static final BooleanProperty soundEnabledProperty = new SimpleBooleanProperty(true);

    public enum Sound {
        EAT,
        GAME_OVER,
        SPECIAL_EAT,
        TIME_TICK
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
                    audioClipGameOver.setVolume(.3);
                    audioClipGameOver.play();
                    break;
                case TIME_TICK:
                    audioClipTimeTick.setVolume(.6);
                    audioClipTimeTick.setRate(2.5);
                    audioClipTimeTick.play();
            }
        }
    }
}
