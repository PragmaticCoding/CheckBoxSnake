package javafx.checkboxsnake.view;

import javafx.checkboxsnake.sound.SoundController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class StartPane extends VBox {

    public StartPane(Runnable gameStarter) {
        super(20);
        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY.deriveColor(1, 1, 1, .8), CornerRadii.EMPTY, Insets.EMPTY)));
        this.getChildren().addAll(new Label("CheckBox Snake"), initCheckBox(), createStartButton(gameStarter));
    }

    private Button createStartButton(Runnable gameStarter) {
        Button buttonStart = new Button("Start");
        buttonStart.setOnAction((ActionEvent event) -> {
            setVisible(false);
            gameStarter.run();
        });
        buttonStart.requestFocus();
        return buttonStart;
    }

    private CheckBox initCheckBox() {
        CheckBox checkBoxSoundEnabled = new CheckBox();
        checkBoxSoundEnabled.setSelected(true);
        SoundController.soundEnabledProperty().bind(checkBoxSoundEnabled.selectedProperty());
        return checkBoxSoundEnabled;
    }
}
