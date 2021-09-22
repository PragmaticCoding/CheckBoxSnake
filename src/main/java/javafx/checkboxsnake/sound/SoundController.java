package javafx.checkboxsnake.sound;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.media.AudioClip;

/**
 * --- here javadoc ---
 *
 * @author dnolte
 */
public class SoundController {

    private AudioClip audioClipEat = new AudioClip(this.getClass().getResource("/javafx/checkboxsnake/sounds/eat2.wav").toString());
    private AudioClip audioClipSpecialEat = new AudioClip(this.getClass().getResource("/javafx/checkboxsnake/sounds/eat2.wav").toString());
    private AudioClip audioClipGameOver = new AudioClip(this.getClass().getResource("/javafx/checkboxsnake/sounds/gameover.mp3").toString());

    private static SoundController instance = null;

    private BooleanProperty soundEnabledProperty = new SimpleBooleanProperty(true);

    public enum Sound {
        EAT,
        GAME_OVER,
        SPECIAL_EAT;
    }

    private SoundController() {
        initGameOverSound();
    }

    public static SoundController getInstance() {
        if (instance == null) {
            instance = new SoundController();
        }

        return instance;
    }

    public BooleanProperty soundEnabledProperty() {
        return soundEnabledProperty;
    }

    private void initGameOverSound() {
        audioClipGameOver.setVolume(.3);
    }

    public void playSound(Sound sound) {
        System.out.println("play sound " + sound + ". sound enabed " + soundEnabledProperty.get());
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
            }
        }
    }
}
