package javafx.checkboxsnake.data;

import javafx.checkboxsnake.game.GameSettings;

import java.util.Objects;

/**
 * --- here javadoc ---
 *
 * @author dnolte
 */
public class Position {

    private final int column;
    private final int row;

    public Position(int xPosition, int yPosition) {
        column = xPosition;
        row = yPosition;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(column);
        hash = 67 * hash + Objects.hashCode(row);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        return (column == other.getColumn()) && (row == other.getRow());
    }

    public Position getNextPosition(Direction direction) {
        return new Position(column + (direction.equals(Direction.RIGHT) ? 1 : 0) + (direction.equals(Direction.LEFT) ? -1 : 0),
                row + (direction.equals(Direction.DOWN) ? 1 : 0) + (direction.equals(Direction.UP) ? -1 : 0));
    }

    public boolean isPositionInBounds() {
        return (column >= 0) && (row >= 0) &&
                (column < GameSettings.GAME_FIELD_SIZE) && row < (GameSettings.GAME_FIELD_SIZE);
    }

    public boolean isNear(Position position) {
        return (Math.abs(column - position.getColumn()) <= 1) && (Math.abs(row - position.getRow()) <= 1);
    }

    public String toString() {
        return "(" + column + ", " + row + ")";
    }
}
