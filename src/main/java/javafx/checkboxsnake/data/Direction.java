package javafx.checkboxsnake.data;

import javafx.scene.input.KeyCode;

import java.util.Optional;

/**
 * --- here javadoc ---
 *
 * @author dnolte
 */
public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    public static Optional<Direction> fromKeyCode(KeyCode keyCode) {
        switch (keyCode) {
            case UP :
              return Optional.of(UP);
            case DOWN :
                return Optional.of(DOWN);
            case LEFT :
                return Optional.of(LEFT);
            case RIGHT :
                return Optional.of(RIGHT);
            default:
                return Optional.empty();
        }
    }

    public boolean isOpposite(Direction testDirection) {
        switch (this) {
            case UP :
                return testDirection.equals(DOWN);
            case DOWN:
                return testDirection.equals(UP);
            case RIGHT:
                return testDirection.equals(LEFT);
            case LEFT:
                return testDirection.equals(RIGHT);
            default:
                return false;
        }
    }
}
