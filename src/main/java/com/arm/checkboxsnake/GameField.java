/*
 * Copyright (c) 2018 by ARM GmbH, www.ablex.com
 * GameField.java
 * created on 06.04.2018 - 14:23:19
 * edited by dnolte 06.04.2018 - 14:23:19
 */
package com.arm.checkboxsnake;

import javafx.scene.layout.GridPane;

/**
 * --- here javadoc ---
 *
 * @author dnolte
 */
public class GameField extends GridPane {

    private final int gameFieldSize;

    private Snake snake;

    private PixelFactory pixelFactory = new PixelFactory();

    private Pixel currentFoodPixel;
    private Pixel currentSpecialFoodPixel;

    private Pixel[][] pixels;

    public GameField(Snake snake, int gameFieldSize) {
        this.snake = snake;
        this.gameFieldSize = gameFieldSize;

        pixels = new Pixel[gameFieldSize][gameFieldSize];

        initGameField();
    }

    public int getGameFieldSize() {
        return gameFieldSize;
    }

    public void setFood(Position position) {
        if (currentFoodPixel != null) {
            currentFoodPixel.unmarkAsFood();
        }
        currentFoodPixel = getPixel(position);
        currentFoodPixel.markAsFood();
    }
    
    public void setSpecialFood(Position position) {
        if (currentSpecialFoodPixel != null) {
            currentSpecialFoodPixel.unmarkAsSpecialFood();
        }
        currentSpecialFoodPixel = getPixel(position);
        currentSpecialFoodPixel.markAsSpecialFood();
    }
    
    public void unsetSpecialFood() {
        if (currentSpecialFoodPixel != null) {
            currentSpecialFoodPixel.unmarkAsSpecialFood();
        }
    }

    private void initGameField() {
        this.setHgap(-5);
        this.setVgap(1);

        for (int width = 0; width < gameFieldSize; width++) {
            for (int height = 0; height < gameFieldSize; height++) {
                Pixel pixel = pixelFactory.getPixel();
                this.add(pixel.getNode(), width, height);
                pixels[width][height] = pixel;
            }
        }
    }

    public void setPixel(Position position) {
        markPixel(position);
    }

    public void unsetPixel(Position position) {
        unmarkPixel(position);
    }
    
    public void resetField(){
         for (int width = 0; width < gameFieldSize; width++) {
            for (int height = 0; height < gameFieldSize; height++) {
                pixels[width][height].unmark();
                pixels[width][height].unmarkAsFood();
            }
        }
    }

    private void markPixel(Position position) {
        Pixel pixel = getPixel(position);
        pixel.mark();
    }

    private void unmarkPixel(Position position) {
        Pixel pixel = getPixel(position);
        pixel.unmark();
    }

    public boolean isPositionInBounds(Position position) {
        return position.xPositionProperty().get() < 0 || position.yPositionProperty().get() < 0 ||
                position.xPositionProperty().get() >= gameFieldSize || position.yPositionProperty().get() >= gameFieldSize;
    }

    private Pixel getPixel(Position postion) {
        return getPixel(postion.xPositionProperty().get(), postion.yPositionProperty().get());
    }

    private Pixel getPixel(int x, int y) {
        return pixels[x][y];
    }
}
