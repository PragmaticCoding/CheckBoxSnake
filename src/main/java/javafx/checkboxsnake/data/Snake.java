package javafx.checkboxsnake.data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

import java.util.LinkedList;
import java.util.Set;

public class Snake {

    private final IntegerProperty lengthProperty = new SimpleIntegerProperty(1);
    private final LinkedList<Position> queueSnakePositions = new LinkedList<>();
    private final ObservableList<Position> observablePixels;
    private Position headPosition;

    public Snake(ObservableList<Position> observablePixels) {
        this.observablePixels = observablePixels;
    }


    public void resetSnake(Position startingPosition) {
        queueSnakePositions.clear();
        addPosition(startingPosition);
        observablePixels.setAll(queueSnakePositions);
    }

    public void move(Position position, boolean lengthenSnake) {
        addPosition(position);
        if (!lengthenSnake) {
            removeLastPosition();
        }
        observablePixels.setAll(queueSnakePositions);
    }

    private void addPosition(Position position) {
        headPosition = position;
        queueSnakePositions.add(position);
    }

    public Position getHead() {
        return headPosition;
    }

    private void removeLastPosition() {
        Position poll = queueSnakePositions.poll();
    }

    public boolean isClashing() {
        return Set.copyOf(queueSnakePositions).size() != queueSnakePositions.size();
    }
}
