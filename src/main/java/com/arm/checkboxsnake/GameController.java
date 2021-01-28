/*
 * Copyright (c) 2018 by ARM GmbH, www.ablex.com
 * GameController.java
 * created on 06.04.2018 - 14:20:46
 * edited by dnolte 06.04.2018 - 14:20:46
 */
package com.arm.checkboxsnake;

import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
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

    private Position startPosition;
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
                    gameFrameController.gameIsUpdated(buildGameUpdateData());
                    updateGame();
                    gameLoopCount++;
                });
            }
        }, 0, speed);
    }

    private void updateGame() {
        Position nextPosition = getNextPosition(inputDirection);
        if (!gameField.isPositionInBounds(nextPosition) && !snake.isClashing(nextPosition)) {
            /*if (gameLoopCounts % 3 == 0) {
                
            } else*/ if (!snake.isEmpty() && !isFoodHit(nextPosition) && !isSpecialFoodHit(nextPosition)) {
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

    private GameUpdateData buildGameUpdateData() {
        GameUpdateData gameUpdateData = new GameUpdateData(inputDirection);

        return gameUpdateData;
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
        while (randomPosition == null || snake.isNear(randomPosition, 1)) {
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
        startPosition = new Position(gameField.getGameFieldSize() / 2, gameField.getGameFieldSize() / 2);
        currentPosition = new Position(gameField.getGameFieldSize() / 2, gameField.getGameFieldSize() / 2);
        snake.resetSnake();
        snake.addPosition(currentPosition);
    }

    private boolean isGenerateSpecialFood() {
        return false;
//        return gameLoopCount != 0 && gameLoopCount % 30 == 0;
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
