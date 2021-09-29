package javafx.checkboxsnake.data;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ViewModel {

    private final IntegerProperty points = new SimpleIntegerProperty(0);
    private final ObjectProperty<Direction> inputDirection = new SimpleObjectProperty<>(Direction.RIGHT);
    private final BooleanProperty gameOver = new SimpleBooleanProperty(false);
    private final ObservableList<Position> snakePixels = FXCollections.observableArrayList();
    private final ObservableList<Food> food = FXCollections.observableArrayList();

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

    public ObservableList<Food> getFood() {
        return food;
    }
}
