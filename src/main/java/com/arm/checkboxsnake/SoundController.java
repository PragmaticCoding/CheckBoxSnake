/*
 * Copyright (c) 2018 by ARM GmbH, www.ablex.com
 * SoundController.java
 * created on 07.04.2018 - 10:51:23
 * edited by dnolte 07.04.2018 - 10:51:23
 */
package com.arm.checkboxsnake;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.media.AudioClip;

/**
 * --- here javadoc ---
 *
 * @author dnolte
 */
public class SoundController {

    private AudioClip audioClipEat = new AudioClip(this.getClass().getResource("/com/arm/checkboxsnake/resources/sounds/eat2.wav").toString());
    private AudioClip audioClipSpecialEat = new AudioClip(this.getClass().getResource("/com/arm/checkboxsnake/resources/sounds/eat.mp3").toString());
    private AudioClip audioClipGameOver = new AudioClip(this.getClass().getResource("/com/arm/checkboxsnake/resources/sounds/gameover.mp3").toString());

    private static SoundController instance = null;

    private BooleanProperty soundEnabledProperty = new SimpleBooleanProperty(true);

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

    public enum Sound {
        EAT,
        GAME_OVER, SPECIAL_EAT;
    }

}
