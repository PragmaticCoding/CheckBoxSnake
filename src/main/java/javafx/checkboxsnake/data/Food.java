package javafx.checkboxsnake.data;

import javafx.checkboxsnake.game.GameSettings;

import java.util.Random;

public class Food {
    private final static Random random = new Random();
    private final Position position;
    private final long createTime;
    private final boolean special;


    public Food(Position position, boolean special, long createTime) {
        this.position = position;
        this.special = special;
        this.createTime = createTime;
    }

    public boolean isTimeToGo(long currentGameLoop) {
        if (!special) return false;
        return (currentGameLoop - createTime) > GameSettings.GAME_LOOPS_SPECIAL_FOOD_LIFE;
    }

    public boolean isSpecial() {
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
