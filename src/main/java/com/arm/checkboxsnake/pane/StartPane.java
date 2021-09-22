package com.arm.checkboxsnake.pane;

import com.arm.checkboxsnake.game.GameFrameController;
import com.arm.checkboxsnake.sound.SoundController;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * --- here javadoc ---
 *
 * @author dnolte
 */
public class StartPane extends VBox {

    private StackPane parentPane;

    private GameFrameController gameFrameController;

    private Label labelStart = new Label("CheckBox Snake");
    private CheckBox checkBoxSoundEnabled = new CheckBox("Sound enabled");
    private Button buttonStart = new Button("Start");

    public StartPane(GameFrameController gameFrameController, StackPane parentPane) {
        super(20);

        this.gameFrameController = gameFrameController;
        this.parentPane = parentPane;

        initPane();
        initButtons();
        initCheckBoxes();
    }

    private void initPane() {
        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY.deriveColor(1, 1, 1, .8), CornerRadii.EMPTY, Insets.EMPTY)));

        this.getChildren().add(labelStart);
        this.getChildren().add(checkBoxSoundEnabled);
        this.getChildren().add(buttonStart);
    }

    private void initButtons() {
        buttonStart.setOnAction((ActionEvent event) -> {
            hide();
            gameFrameController.runGame();
        });
        buttonStart.sceneProperty().addListener((ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) -> {
            if (newValue != null) {
                buttonStart.requestFocus();
            }
        });
    }

    public void show() {
        parentPane.getChildren().add(this);
    }

    public void hide() {
        parentPane.getChildren().remove(this);
    }

    private void initCheckBoxes() {
        checkBoxSoundEnabled.setSelected(true);
        SoundController.getInstance().soundEnabledProperty().bind(checkBoxSoundEnabled.selectedProperty());
    }

}
