package javafx.checkboxsnake.data;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ViewModel {

    private final IntegerProperty points = new SimpleIntegerProperty(0);
    private final ObjectProperty<Direction> inputDirection = new SimpleObjectProperty<>(Direction.RIGHT);
    private final BooleanProperty gameOver = new SimpleBooleanProperty(false);
    private final ObservableList<Position> snakePixels = FXCollections.observableArrayList();
    private final ObjectProperty<Position> foodPosition = new SimpleObjectProperty<>();
    private final ObjectProperty<Position> specialFoodPosition = new SimpleObjectProperty<>();
    private final IntegerProperty gameCounter = new SimpleIntegerProperty(0);

    public int getPoints() {
        return points.get();
    }

    public IntegerProperty pointsProperty() {
        return points;
    }

    public void setPoints(int points) {
        this.points.set(points);
    }

    public Direction getInputDirection() {
        return inputDirection.get();
    }

    public ObjectProperty<Direction> inputDirectionProperty() {
        return inputDirection;
    }

    public BooleanProperty gameOverProperty() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver.set(gameOver);
    }

    public ObservableList<Position> getSnakePixels() {
        return snakePixels;
    }

    public Position getFoodPosition() {
        return foodPosition.get();
    }

    public ObjectProperty<Position> foodPositionProperty() {
        return foodPosition;
    }

    public void setFoodPosition(Position foodPosition) {
        this.foodPosition.set(foodPosition);
    }

    public Position getSpecialFoodPosition() {
        return specialFoodPosition.get();
    }

    public ObjectProperty<Position> specialFoodPositionProperty() {
        return specialFoodPosition;
    }

    public void setSpecialFoodPosition(Position specialFoodPosition) {
        this.specialFoodPosition.set(specialFoodPosition);
    }

    public int getGameCounter() {
        return gameCounter.get();
    }

    public IntegerProperty gameCounterProperty() {
        return gameCounter;
    }

    public void setGameCounter(int gameCounter) {
        this.gameCounter.set(gameCounter);
    }
}
