/*
 * Copyright (c) 2018 by ARM GmbH, www.ablex.com
 * Snake.java
 * created on 06.04.2018 - 14:21:12
 * edited by dnolte 06.04.2018 - 14:21:12
 */
package checkboxsnake;

import java.util.Arrays;
import java.util.Collection;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * --- here javadoc ---
 *
 * @author dnolte
 */
public class Snake {

    private IntegerProperty lengthProperty = new SimpleIntegerProperty(1);

    private Queue<Position> queueSnakePositions = new LinkedBlockingQueue<>();

    public IntegerProperty lengthProperty() {
        return lengthProperty;
    }

    public void resetSnake() {
        queueSnakePositions.clear();
        setLengthProperty();
    }

    public void addPosition(Position position) {
        queueSnakePositions.add(position);
        setLengthProperty();
    }

    public Position removeLastPosition() {
        Position poll = queueSnakePositions.poll();
        setLengthProperty();
        return poll;
    }

    public boolean isClashing(Position position) {
        return queueSnakePositions.contains(position);
    }
    
    public boolean isNear(Position position, int radius) {
        return getNearPositions(position, radius).stream().anyMatch((Position t) -> queueSnakePositions.contains(t));
    }

    public boolean isEmpty() {
        return queueSnakePositions.isEmpty();
    }

    private void setLengthProperty() {
        lengthProperty.set(queueSnakePositions.size());
    }
    
    private Collection<Position> getNearPositions(Position position, int radius){
        Position position1 = new Position(position.xPositionProperty().get() + 1, position.yPositionProperty().get() + 1);
        Position position2 = new Position(position.xPositionProperty().get() - 1, position.yPositionProperty().get() - 1);
        Position position3 = new Position(position.xPositionProperty().get() + 1, position.yPositionProperty().get() - 1);
        Position position4 = new Position(position.xPositionProperty().get() - 1, position.yPositionProperty().get() + 1);
        Position position5 = new Position(position.xPositionProperty().get() + 1, position.yPositionProperty().get());
        Position position6 = new Position(position.xPositionProperty().get() - 1, position.yPositionProperty().get());
        Position position7 = new Position(position.xPositionProperty().get(), position.yPositionProperty().get() + 1);
        Position position8 = new Position(position.xPositionProperty().get(), position.yPositionProperty().get() - 1);
        
        return Arrays.asList(position1, position2, position3, position4, position5, position6, position7, position8);
    }

}
