package javafx.checkboxsnake.game;

import javafx.checkboxsnake.data.Food;
import javafx.checkboxsnake.data.Position;
import javafx.checkboxsnake.data.ViewModel;

public class FoodHandler {

    private final ViewModel viewModel;
    private Food regularFood;
    private Food specialFood;


    public FoodHandler(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void pulse(Position snakeHeadPosition) {
        consumeFoodIfHit(snakeHeadPosition, regularFood);
        consumeFoodIfHit(snakeHeadPosition, specialFood);
        removeFoodIfExpired();
        createSpecialFoodIfRequired(snakeHeadPosition);
        updateModel();
    }

    private void createSpecialFoodIfRequired(Position snakeHeadPosition) {
        if ((viewModel.getGameCounter() % GameSettings.GAME_LOOPS_TILL_SPECIAL_FOOD) == 0) {
            specialFood = Food.createRandomFood(snakeHeadPosition, true, viewModel.getGameCounter());
        }
    }

    private void consumeFoodIfHit(Position snakeHeadPosition, Food food) {
        if ((food != null) && checkFoodEaten(food)) {
            consumeFood(food, snakeHeadPosition);
        }
    }

    private void updateModel() {
        viewModel.setFoodPosition((regularFood != null) ? regularFood.getPosition() : null);
        viewModel.setSpecialFoodPosition((specialFood != null) ? specialFood.getPosition() : null);
    }

    private boolean checkFoodEaten(Food food) {
        return viewModel.getSnakePixels().contains(food.getPosition());
    }

    private void removeFoodIfExpired() {
        if (regularFood.isTimeToGo(viewModel.getGameCounter())) {
            regularFood = null;
        }
        if ((specialFood != null) && specialFood.isTimeToGo(viewModel.getGameCounter())) {
            specialFood = null;
        }
    }

    private void consumeFood(Food food, Position snakeHeadPosition) {
        viewModel.setPoints(viewModel.getPoints() + (food.isSpecial() ? 5 : 1));
        if (food.isSpecial()) {
            specialFood = null;
        } else {
            regularFood = Food.createRandomFood(snakeHeadPosition, false, viewModel.getGameCounter());
        }
    }


    public void reset(Position snakeHeadPosition) {
        regularFood = Food.createRandomFood(snakeHeadPosition, false, 0);
        specialFood = null;
        updateModel();
    }
}
