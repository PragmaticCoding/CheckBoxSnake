package javafx.checkboxsnake.game;

import javafx.application.Platform;
import javafx.checkboxsnake.data.GameCounter;
import javafx.checkboxsnake.data.GameSettings;
import javafx.checkboxsnake.data.Position;
import javafx.checkboxsnake.data.ViewModel;
import javafx.checkboxsnake.view.GameFrame;
import javafx.scene.layout.StackPane;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GameController {

    private final GameFrame gameFrame;
    private Timer gameTimer;
    private final ViewModel viewModel;
    private final List<PulseHandler> pulseHandlers;
    private final GameCounter gameCounter = new GameCounter();

    public GameController() {
        viewModel = new ViewModel();
        pulseHandlers = List.of(new SnakeHandler(viewModel), new FoodHandler(viewModel, gameCounter));
        this.gameFrame = new GameFrame(viewModel, this::startGame);
    }

    public StackPane getView() {
        return gameFrame;
    }

    public void startGame() {
        gameCounter.resetCounter();
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
        pulseHandlers.forEach(PulseHandler::handlePulse);
        stopGameIfRequired();
        gameCounter.incrementCount();
    }

    private void stopGameIfRequired() {
        if (viewModel.getSnakePixels().stream().anyMatch(Position::isNowhere)) {
            gameTimer.cancel();
            viewModel.setGameOver(true);
        }
    }

    private void initStartPositions() {
        Position startingPosition = new Position(GameSettings.GAME_FIELD_SIZE / 2, GameSettings.GAME_FIELD_SIZE / 2);
        pulseHandlers.forEach(pulseHandler -> pulseHandler.reset(startingPosition));
    }
}
