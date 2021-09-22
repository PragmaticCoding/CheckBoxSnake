package com.arm.checkboxsnake.pixel;

import javafx.scene.Node;

/**
 * --- here javadoc ---
 *
 * @author dnolte
 */
public interface Pixel {

    public Node getNode();

    public void mark();

    public void unmark();

    public void markAsFood();

    public void unmarkAsFood();

    public void markAsSpecialFood();

    public void unmarkAsSpecialFood();
}

