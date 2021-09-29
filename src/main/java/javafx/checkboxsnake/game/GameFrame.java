package javafx.checkboxsnake.game;

import javafx.checkboxsnake.data.Direction;
import javafx.checkboxsnake.data.Position;
import javafx.checkboxsnake.data.ViewModel;
import javafx.checkboxsnake.pane.GameOverPane;
import javafx.checkboxsnake.pane.StartPane;
import javafx.checkboxsnake.pixel.CheckBoxPixel;
import javafx.checkboxsnake.sound.SoundController;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * @author dnolte
 */
public class GameFrame extends StackPane {

    private final SoundController soundController = SoundController.getInstance();
    private final ViewModel viewModel;

    public GameFrame(ViewModel viewModel, Runnable gameStarter) {
        this.viewModel = viewModel;
        Label labelPoints = new ScrollingLabel(viewModel.pointsProperty());
        HBox pointsBox = new HBox(6, new Label("Points: "), labelPoints);
        getStylesheets().add("/javafx/checkboxsnake/css/styles.css");
        GameOverPane gameOverPane = new GameOverPane(() -> startGame(gameStarter), viewModel.pointsProperty(), viewModel.gameOverProperty());
        getChildren().addAll(new VBox(20, pointsBox, initGameField()), new StartPane(() -> startGame(gameStarter)), gameOverPane);
        setPadding(new Insets(25, 15, 25, 15));
        viewModel.gameOverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                soundController.playSound(SoundController.Sound.GAME_OVER);
            }
        });
    }

    private GridPane initGameField() {
        GridPane gameField = new GridPane();
        gameField.setHgap(1);
        gameField.setVgap(1);
        for (int column = 0; column < GameSettings.GAME_FIELD_SIZE; column++) {
            for (int row = 0; row < GameSettings.GAME_FIELD_SIZE; row++) {
                CheckBoxPixel pixel = new CheckBoxPixel(new Position(column, row), viewModel.getSnakePixels(), viewModel.getFood());
                gameField.add(pixel.getNode(), column, row);
            }
        }
        return gameField;
    }

    private void startGame(Runnable gameStarter) {
        getScene().setOnKeyPressed((KeyEvent event) -> Direction.fromKeyCode(event.getCode()).ifPresent(viewModel.inputDirectionProperty()::set));
        gameStarter.run();
    }

    public void ateFood(boolean isFoodSpecial) {
        soundController.playSound(isFoodSpecial ? SoundController.Sound.SPECIAL_EAT : SoundController.Sound.EAT);
    }
}
