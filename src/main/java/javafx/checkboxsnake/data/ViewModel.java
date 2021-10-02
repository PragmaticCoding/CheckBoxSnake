package javafx.checkboxsnake.data;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ViewModel {

    private final IntegerProperty points = new SimpleIntegerProperty(0);
    private final ObjectProperty<Direction> inputDirection = new SimpleObjectProperty<>(Direction.RIGHT);
    private final BooleanProperty gameOver = new SimpleBooleanProperty(false);
    private final ObservableList<Position> snakePixels = FXCollections.observableArrayList();
    private final ObjectProperty<Position> foodPosition = new SimpleObjectProperty<>(Position.NOWHERE);
    private final ObjectProperty<Position> specialFoodPosition = new SimpleObjectProperty<>(Position.NOWHERE);
    private final ObjectProperty<Position> headPosition = new SimpleObjectProperty<>(Position.NOWHERE);

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

    public Position getHeadPosition() {
        return headPosition.get();
    }

    public ObjectProperty<Position> headPositionProperty() {
        return headPosition;
    }

    public void setHeadPosition(Position headPosition) {
        this.headPosition.set(headPosition);
    }
}
