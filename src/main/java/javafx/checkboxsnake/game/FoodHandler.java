package javafx.checkboxsnake.game;

import javafx.checkboxsnake.data.GameCounter;
import javafx.checkboxsnake.data.GameSettings;
import javafx.checkboxsnake.data.Position;
import javafx.checkboxsnake.data.ViewModel;

import java.util.Random;

public class FoodHandler extends PulseHandler {

    private final static Random random = new Random();
    private long specialFoodCreateTime = 0;
    private final GameCounter gameCounter;


    public FoodHandler(ViewModel viewModel, GameCounter gameCounter) {
        super(viewModel);
        this.gameCounter = gameCounter;
    }

    public void handlePulse() {
        consumeFoodIfHit(viewModel.getFoodPosition());
        consumeFoodIfHit(viewModel.getSpecialFoodPosition());
        removeFoodIfExpired();
        createSpecialFoodIfRequired();
    }

    private void createSpecialFoodIfRequired() {
        if ((gameCounter.getCount() % GameSettings.GAME_LOOPS_TILL_SPECIAL_FOOD) == 0) {
            viewModel.setSpecialFoodPosition(createRandomFood());
            specialFoodCreateTime = gameCounter.getCount();
        }
    }

    private void consumeFoodIfHit(Position foodPosition) {
        if (checkIfFoodHit(foodPosition)) {
            consumeFood(foodPosition);
        }
    }

    private boolean checkIfFoodHit(Position foodPosition) {
        return viewModel.getHeadPosition().equals(foodPosition);
    }

    private void removeFoodIfExpired() {
        if ((!viewModel.getSpecialFoodPosition().isNowhere()) && isTimeForSpecialFoodToGo()) {
            viewModel.setSpecialFoodPosition(Position.NOWHERE);
        }
    }

    public boolean isTimeForSpecialFoodToGo() {
        return (gameCounter.getCount() - specialFoodCreateTime) > GameSettings.GAME_LOOPS_SPECIAL_FOOD_LIFE;
    }

    private void consumeFood(Position foodPosition) {
        boolean isSpecialFood = foodPosition.equals(viewModel.getSpecialFoodPosition());
        viewModel.setPoints(viewModel.getPoints() + (isSpecialFood ? 5 : 1));
        if (isSpecialFood) {
            viewModel.setSpecialFoodPosition(Position.NOWHERE);
        } else {
            viewModel.setFoodPosition(createRandomFood());
        }
    }


    public void reset(Position startingPosition) {
        viewModel.setFoodPosition(createRandomFood());
        viewModel.setSpecialFoodPosition(Position.NOWHERE);
    }

    public Position createRandomFood() {
        Position randomPosition;
        do {
            int randomX = random.nextInt(GameSettings.GAME_FIELD_SIZE);
            int randomY = random.nextInt(GameSettings.GAME_FIELD_SIZE);
            randomPosition = new Position(randomX, randomY);
        } while (viewModel.getHeadPosition().isNear(randomPosition));
        return randomPosition;
    }
}
