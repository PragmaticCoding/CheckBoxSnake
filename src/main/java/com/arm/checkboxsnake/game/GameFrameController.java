package com.arm.checkboxsnake.game;

import java.net.URL;
import java.util.ResourceBundle;

import com.arm.checkboxsnake.data.Snake;
import com.arm.checkboxsnake.misc.TransitionFactory;
import com.arm.checkboxsnake.pane.GameField;
import com.arm.checkboxsnake.pane.GameOverPane;
import com.arm.checkboxsnake.pane.StartPane;
import com.arm.checkboxsnake.sound.SoundController;
import javafx.animation.Transition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * @author dnolte
 */
public class GameFrameController implements Initializable {

    private static final double DURATION_TRANSITION_POINTS = 200;

    @FXML
    private StackPane rootPane;

    @FXML
    private VBox vBoxRoot;

    @FXML
    private Label labelPoints;

    private GameField gameField;

    private GameController gameController;
    private SoundController soundController = SoundController.getInstance();

    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rootPane.getStylesheets().add("/com/arm/checkboxsnake/css/styles.css");

        gameField = new GameField(new Snake(), GameSettings.GAME_FIELD_SIZE);
        gameController = new GameController(this, gameField, GameSettings.GAME_SPEED);

    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void gameOver() {
        soundController.playSound(SoundController.Sound.GAME_OVER);
        GameOverPane gameOverPane = new GameOverPane(this, rootPane, gameController.pointsProperty().get());
        rootPane.getChildren().add(gameOverPane);
    }

    public void buildGame() {
        initPointsView();
        vBoxRoot.getChildren().add(gameField);
    }

    public void initGame() {
        buildGame();
        initControls();
        initStartPane();
    }

    public void runGame() {
        gameController.startGame();
    }

    private void initControls() {
        scene.setOnKeyPressed((KeyEvent event) -> {
            System.out.println("pressed key " + event.getCode());
            gameController.setPressedKey(event.getCode());
        });
    }

    private void initStartPane() {
        StartPane startPane = new StartPane(this, rootPane);
        startPane.show();
    }

    public void ateFood() {
        soundController.playSound(SoundController.Sound.EAT);
    }

    public void ateSpecialFood() {
        soundController.playSound(SoundController.Sound.SPECIAL_EAT);
    }

    private void initPointsView() {
        gameController.pointsProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("old " + oldValue + " new " + newValue);
            if (newValue != oldValue) {
                Transition transition1 = TransitionFactory.getFromToTranslateTransition(labelPoints, 0, 0,
                        0, -50, DURATION_TRANSITION_POINTS, null);
                Transition transition2 = TransitionFactory.getFadeTransition(labelPoints, 1, 0, DURATION_TRANSITION_POINTS, null);
                Transition parallelTransition1 = TransitionFactory.getParallelTransition((EventHandler) (Event event) -> {
                    labelPoints.setText(newValue.toString());
                }, transition1, transition2);

                Transition transition3 = TransitionFactory.getFromToTranslateTransition(labelPoints, 0, 50,
                        0, 0, DURATION_TRANSITION_POINTS, null);
                Transition transition4 = TransitionFactory.getFadeTransition(labelPoints, 0, 1, DURATION_TRANSITION_POINTS, null);
                Transition parallelTransition2 = TransitionFactory.getParallelTransition((EventHandler) (Event event) -> {
                    labelPoints.setText(newValue.toString());
                }, transition3, transition4);

                TransitionFactory.getSequentialTransition(null, parallelTransition1, parallelTransition2).play();
            }
        });
    }
}
