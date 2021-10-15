package javafx.checkboxsnake.game;

import javafx.animation.AnimationTimer;
import javafx.checkboxsnake.data.GameCounter;
import javafx.checkboxsnake.data.GameSettings;
import javafx.checkboxsnake.data.Position;
import javafx.checkboxsnake.data.ViewModel;
import javafx.checkboxsnake.view.GameFrame;
import javafx.scene.layout.StackPane;

import java.util.List;

public class GameController {

    private final GameFrame gameFrame;
    private final ViewModel viewModel;
    private final List<PulseHandler> pulseHandlers;
    private final GameCounter gameCounter = new GameCounter();
    private AnimationTimer gameTimer;

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
        final long[] lastPulse = {0};
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if ((now - lastPulse[0]) > GameSettings.GAME_SPEED) {
                    gamePulse(now);
                    lastPulse[0] = now;
                }
            }
        };
        gameTimer.start();
    }

    private void gamePulse(long now) {

        pulseHandlers.forEach(PulseHandler::handlePulse);
        stopGameIfRequired();
        gameCounter.incrementCount();
    }

    private void stopGameIfRequired() {
        if (viewModel.getSnakePixels().stream().anyMatch(Position::isNowhere)) {
            gameTimer.stop();
            viewModel.setGameOver(true);
        }
    }

    private void initStartPositions() {
        Position startingPosition = new Position(GameSettings.GAME_FIELD_SIZE / 2, GameSettings.GAME_FIELD_SIZE / 2);
        pulseHandlers.forEach(pulseHandler -> pulseHandler.reset(startingPosition));
    }
}
