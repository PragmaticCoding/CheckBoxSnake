package com.arm.checkboxsnake.game;

import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.arm.checkboxsnake.data.Direction;
import com.arm.checkboxsnake.data.Position;
import com.arm.checkboxsnake.data.Snake;
import com.arm.checkboxsnake.pane.GameField;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.input.KeyCode;

/**
 * --- here javadoc ---
 *
 * @author dnolte
 */
public class GameController {

    private GameFrameController gameFrameController;
    private long speed;

    private Timer gameTimer;

    private Snake snake = new Snake();

    private GameField gameField;

    private Direction inputDirection = Direction.RIGHT;
    private Direction executedDirection = null;

    private Position currentPosition;
    private Position foodPosition;
    private Position specialFoodPosition;

    private long gameLoopCount = 0;

    private IntegerProperty pointsProperty = new SimpleIntegerProperty(0);

    public GameController(GameFrameController gameFrameController, GameField gameField, long speed) {
        this.gameFrameController = gameFrameController;
        this.gameField = gameField;
        this.speed = speed;
    }

    public void startGame() {
        System.out.println("start game");
        resetPoints();
        initStartPositions();
        gameField.resetField();
        generateFood();
        runGameLoop();
    }

    public void stopGame() {
        gameTimer.cancel();
    }

    public IntegerProperty pointsProperty() {
        return pointsProperty;
    }

    private void runGameLoop() {
        gameLoopCount = 0;
        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    updateGame();
                    gameLoopCount++;
                });
            }
        }, 0, 200);
    }

    private void updateGame() {
        Position nextPosition = getNextPosition(inputDirection);
        if (!gameField.isPositionInBounds(nextPosition) && !snake.isClashing(nextPosition)) {
            if (!snake.isEmpty() && !isFoodHit(nextPosition) && !isSpecialFoodHit(nextPosition)) {
                gameField.unsetPixel(snake.removeLastPosition());
            } else if (isFoodHit(nextPosition)) {
                System.out.println("ate");
                increasePoints();
                gameFrameController.ateFood();
                generateFood();
            } else if (isSpecialFoodHit(nextPosition)) {
                increaseSpecialPoints();
                unsetSpecialFood();
                gameFrameController.ateSpecialFood();

            }
            if (isGenerateSpecialFood()) {
                generateSpecialFood();
            }
            gameField.setPixel(nextPosition);

            currentPosition = nextPosition;
            snake.addPosition(currentPosition);
            executedDirection = inputDirection;
            System.out.println("executed direction " + executedDirection);
            System.out.println("game loop count " + gameLoopCount);
        } else {
            System.out.println("game over");
            stopGame();
            gameFrameController.gameOver();
        }

    }

    private void increasePoints() {
        pointsProperty.set(pointsProperty.get() + 1);
    }

    private void resetPoints() {
        pointsProperty.set(0);
    }

    private void generateFood() {
        Position randomFoodPosition = getRandomFoodPosition();
        foodPosition = randomFoodPosition;
        gameField.setFood(randomFoodPosition);
    }

    private void unsetSpecialFood() {
        gameField.unsetSpecialFood();
        specialFoodPosition = null;
    }

    public void setPressedKey(KeyCode code) {
        switch (code) {
            case UP:
                inputDirection = executedDirection == Direction.DOWN ? executedDirection : Direction.UP;
                break;
            case DOWN:
                inputDirection = executedDirection == Direction.UP ? executedDirection : Direction.DOWN;
                break;
            case LEFT:
                inputDirection = executedDirection == Direction.RIGHT ? executedDirection : Direction.LEFT;
                break;
            case RIGHT:
                inputDirection = executedDirection == Direction.LEFT ? executedDirection : Direction.RIGHT;
                break;
        }
    }

    private Position getNextPosition(Direction direction) {
        int currentXPosition = currentPosition.xPositionProperty().get();
        int currentYPosition = currentPosition.yPositionProperty().get();

        Position nextPosition = null;

        switch (direction) {
            case DOWN:
                nextPosition = new Position(currentXPosition, currentYPosition + 1);
                break;
            case UP:
                nextPosition = new Position(currentXPosition, currentYPosition - 1);
                break;
            case LEFT:
                nextPosition = new Position(currentXPosition - 1, currentYPosition);
                break;
            case RIGHT:
                nextPosition = new Position(currentXPosition + 1, currentYPosition);
                break;
        }

        return nextPosition;
    }

    private Position getRandomFoodPosition() {
        Random random = new Random();

        Position randomPosition = null;
        while (randomPosition == null || snake.isNear(randomPosition)) {
            int randomX = random.nextInt(gameField.getGameFieldSize());
            int randomY = random.nextInt(gameField.getGameFieldSize());

            randomPosition = new Position(randomX, randomY);
        }

        return randomPosition;
    }

    private boolean isFoodHit(Position nextPosition) {
        return Objects.equals(foodPosition, nextPosition);
    }

    private boolean isSpecialFoodHit(Position nextPosition) {
        return Objects.equals(specialFoodPosition, nextPosition);
    }

    private void initStartPositions() {
        currentPosition = new Position(gameField.getGameFieldSize() / 2, gameField.getGameFieldSize() / 2);
        snake.resetSnake();
        snake.addPosition(currentPosition);
    }

    private boolean isGenerateSpecialFood() {
        return GameSettings.SPECIAL_FOOD_ENABLED && gameLoopCount != 0 && gameLoopCount % GameSettings.GAME_LOOPS_TILL_SPECIAL_FOOD == 0;
    }

    private void generateSpecialFood() {
        Position randomFoodPosition = getRandomFoodPosition();
        specialFoodPosition = randomFoodPosition;
        gameField.setSpecialFood(randomFoodPosition);
    }

    private void increaseSpecialPoints() {
        pointsProperty.set(pointsProperty.get() + 5);
    }
}
