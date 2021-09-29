package javafx.checkboxsnake.game;

import javafx.application.Platform;
import javafx.checkboxsnake.data.*;
import javafx.scene.layout.StackPane;

import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class GameController {

    private final GameFrame gameFrame;
    private Timer gameTimer;
    private final Snake snake;
    private Direction executedDirection = Direction.RIGHT;
    private final ViewModel viewModel;
    private long gameLoopCount = 1;

    public GameController() {
        viewModel = new ViewModel();
        snake = new Snake(viewModel.getSnakePixels());
        this.gameFrame = new GameFrame(viewModel, this::startGame);
    }

    public StackPane getView() {
        return gameFrame;
    }

    public void startGame() {
        gameLoopCount = 1;
        viewModel.setGameOver(false);
        viewModel.setPoints(0);
        initStartPositions();
        runGameLoop();
    }

    private void runGameLoop() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> updateGame());
            }
        };
        gameTimer = new Timer();
        gameTimer.schedule(task, 0, 200);
    }

    private void updateGame() {
        Direction newDirection = setDirection();
        Position nextPosition = snake.getHead().getNextPosition(newDirection);
        snake.move(nextPosition, getFoodHit(nextPosition).isPresent());
        if (snake.getHead().isPositionInBounds() && !snake.isClashing()) {
            getFoodHit(snake.getHead()).ifPresent(this::consumeFood);
            executedDirection = newDirection;
        } else {
            gameTimer.cancel();
            viewModel.setGameOver(true);
        }
        checkSpecialFood();
        gameLoopCount++;
    }

    private Direction setDirection() {
        return (!executedDirection.isOpposite(viewModel.getInputDirection())) ? viewModel.getInputDirection() : executedDirection;
    }

    private void consumeFood(Food food) {
        viewModel.setPoints(viewModel.getPoints() + (food.isSpecial() ? 5 : 1));
        gameFrame.ateFood(food.isSpecial());
        viewModel.getFood().remove(food);
        if (!food.isSpecial()) {
            generateFood(false);
        }
    }

    private void checkSpecialFood() {
        viewModel.getFood().removeAll(viewModel.getFood().stream().
                filter(Food::isSpecial).
                filter(food -> food.isTimeToGo(gameLoopCount)).
                collect(Collectors.toList()));
        if ((gameLoopCount % GameSettings.GAME_LOOPS_TILL_SPECIAL_FOOD) == 0) {
            generateFood(true);
        }
    }

    private void generateFood(boolean isFoodSpecial) {
        viewModel.getFood().add(Food.createRandomFood(snake.getHead(), isFoodSpecial, gameLoopCount));
    }

    private Optional<Food> getFoodHit(Position position) {
        return viewModel.getFood().stream().filter(food -> food.getPosition().equals(position)).findFirst();
    }

    private void initStartPositions() {
        snake.resetSnake(new Position(GameSettings.GAME_FIELD_SIZE / 2, GameSettings.GAME_FIELD_SIZE / 2));
        viewModel.getFood().clear();
        generateFood(false);
    }
}
