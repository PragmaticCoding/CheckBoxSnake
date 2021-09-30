package javafx.checkboxsnake.data;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;

public class Snake {

    private final LinkedList<Position> queueSnakePositions = new LinkedList<>();
    private final ViewModel viewModel;
    private Position headPosition;
    private Direction currentDirection = Direction.RIGHT;

    public Snake(ViewModel viewModel) {
        this.viewModel = viewModel;
    }


    public void resetSnake(Position startingPosition) {
        currentDirection = Direction.RIGHT;
        queueSnakePositions.clear();
        addPosition(startingPosition);
        viewModel.getSnakePixels().setAll(queueSnakePositions);
    }

    public void pulse() {
        currentDirection = setDirection();
        Position nextPosition = getHead().getNextPosition(currentDirection);
        move(nextPosition, hasAnyFoodBeenHit(nextPosition));
    }

    private boolean hasAnyFoodBeenHit(Position position) {
        return hasFoodBeenHit(position, viewModel.getFoodPosition()) || hasFoodBeenHit(position, viewModel.getSpecialFoodPosition());
    }

    private boolean hasFoodBeenHit(Position position, Position foodPosition) {
        if (Objects.nonNull(foodPosition)) {
            return foodPosition.equals(position);
        }
        return false;
    }

    private void move(Position position, boolean lengthenSnake) {
        addPosition(position);
        if (!lengthenSnake) {
            removeLastPosition();
        }
        viewModel.getSnakePixels().setAll(queueSnakePositions);
    }

    private Direction setDirection() {
        return (!currentDirection.isOpposite(viewModel.getInputDirection())) ? viewModel.getInputDirection() : currentDirection;
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
