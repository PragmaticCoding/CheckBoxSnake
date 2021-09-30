package javafx.checkboxsnake.game;

import javafx.application.Platform;
import javafx.checkboxsnake.data.Position;
import javafx.checkboxsnake.data.Snake;
import javafx.checkboxsnake.data.ViewModel;
import javafx.scene.layout.StackPane;

import java.util.Timer;
import java.util.TimerTask;

public class GameController {

    private final GameFrame gameFrame;
    private Timer gameTimer;
    private final Snake snake;
    private final FoodHandler foodHandler;
    private final ViewModel viewModel;

    public GameController() {
        viewModel = new ViewModel();
        snake = new Snake(viewModel);
        foodHandler = new FoodHandler(viewModel);
        this.gameFrame = new GameFrame(viewModel, this::startGame);
    }

    public StackPane getView() {
        return gameFrame;
    }

    public void startGame() {
        viewModel.setGameCounter(1);
        viewModel.setGameOver(false);
        viewModel.setPoints(0);
        initStartPositions();
        runGameLoop();
    }

    private void runGameLoop() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> gamePulse());
            }
        };
        gameTimer = new Timer();
        gameTimer.schedule(task, 0, 200);
    }

    private void gamePulse() {
        snake.pulse();
        foodHandler.pulse(snake.getHead());
        stopGameIfRequired();
        viewModel.setGameCounter(viewModel.getGameCounter() + 1);
    }

    private void stopGameIfRequired() {
        if (!snake.getHead().isPositionInBounds() || snake.isClashing()) {
            gameTimer.cancel();
            viewModel.setGameOver(true);
        }
    }

    private void initStartPositions() {
        snake.resetSnake(new Position(GameSettings.GAME_FIELD_SIZE / 2, GameSettings.GAME_FIELD_SIZE / 2));
        foodHandler.reset(snake.getHead());
    }
}
