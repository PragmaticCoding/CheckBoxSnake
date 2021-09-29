package javafx.checkboxsnake.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.checkboxsnake.game.GameSettings;

import java.util.Random;

public class Food {
    private final static Random random = new Random();
    private final Position position;
    private final BooleanProperty special = new SimpleBooleanProperty(false);
    private final long createTime;


    public Food(Position position, boolean special, long createTime) {
        this.position = position;
        this.special.set(special);
        this.createTime = createTime;
    }

    public boolean isTimeToGo(long currentGameLoop) {
        return (currentGameLoop - createTime) > GameSettings.GAME_LOOPS_SPECIAL_FOOD_LIFE;
    }

    public boolean isSpecial() {
        return special.get();
    }

    public BooleanProperty specialProperty() {
        return special;
    }

    public Position getPosition() {
        return position;
    }

    public static Food createRandomFood(Position snakeHeadPosition, boolean isFoodSpecial, long createTime) {
        Position randomPosition;
        do {
            int randomX = random.nextInt(GameSettings.GAME_FIELD_SIZE);
            int randomY = random.nextInt(GameSettings.GAME_FIELD_SIZE);
            randomPosition = new Position(randomX, randomY);
        } while (snakeHeadPosition.isNear(randomPosition));
        return new Food(randomPosition, isFoodSpecial, createTime);
    }
}
