package javafx.checkboxsnake.game;

import javafx.checkboxsnake.data.Direction;
import javafx.checkboxsnake.data.Position;
import javafx.checkboxsnake.data.ViewModel;

import java.util.LinkedList;

public class SnakeHandler extends PulseHandler {

    private final LinkedList<Position> queueSnakePositions = new LinkedList<>();
    private Direction currentDirection = Direction.RIGHT;

    public SnakeHandler(ViewModel viewModel) {
        super(viewModel);
    }

    public void reset(Position startingPosition) {
        currentDirection = Direction.RIGHT;
        queueSnakePositions.clear();
        move(startingPosition, true);
    }

    public void handlePulse() {
        currentDirection = setDirection();
        Position nextPosition = determineNextPosition();
        move(nextPosition, hasAnyFoodBeenHit(nextPosition));
    }

    private Position determineNextPosition() {
        Position nextPosition = viewModel.getHeadPosition().getNextPosition(currentDirection);
        return (queueSnakePositions.contains(nextPosition)) ? Position.NOWHERE : nextPosition;
    }

    private boolean hasAnyFoodBeenHit(Position position) {
        return position.equals(viewModel.getFoodPosition()) || position.equals(viewModel.getSpecialFoodPosition());
    }

    private void move(Position position, boolean lengthenSnake) {
        addPosition(position);
        if (!lengthenSnake) {
            queueSnakePositions.poll();
        }
        viewModel.getSnakePixels().setAll(queueSnakePositions);
    }

    private Direction setDirection() {
        return (!currentDirection.isOpposite(viewModel.getInputDirection())) ? viewModel.getInputDirection() : currentDirection;
    }

    private void addPosition(Position position) {
        viewModel.setHeadPosition(position);
        queueSnakePositions.add(position);
    }
}
