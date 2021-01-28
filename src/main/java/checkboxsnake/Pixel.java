/*
 * Copyright (c) 2018 by ARM GmbH, www.ablex.com
 * Pixel.java
 * created on 06.04.2018 - 14:39:53
 * edited by dnolte 06.04.2018 - 14:39:53
 */
package checkboxsnake;

import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;

/**
 * --- here javadoc ---
 *
 * @author dnolte
 */
public class Pixel<N extends Node>{

//    private CheckBox node;
    private RadioButton node;

    private Transition rotateTransition = null;

    public Pixel() {
        node = buildNode();
    }

    public Node getNode() {
        return node;
    }

    public void mark() {
        node.setSelected(true);
    }

    public void unmark() {
        node.setSelected(false);
    }

    public void markAsFood() {
        node.getStyleClass().add("check-box-food");
        mark();
    }

    public void unmarkAsFood() {
        node.getStyleClass().remove("check-box-food");
        unmark();
    }

    public void markAsSpecialFood() {
        markAsFood();
        rotateTransition = TransitionFactory.getRotateTransition(node, Double.MAX_VALUE, 500, null);
        rotateTransition.setCycleCount(-1);
        rotateTransition.play();
    }

    public void unmarkAsSpecialFood() {
        unmarkAsFood();
        rotateTransition.stop();
        node.setRotate(0);
    }

    private RadioButton buildNode() {
//        CheckBox checkBox = new CheckBox();
//        checkBox.setFocusTraversable(false);
//        checkBox.addEventFilter(MouseEvent.MOUSE_PRESSED, (event) -> {
//            event.consume();
//        });
        RadioButton radioButton = new RadioButton();
         radioButton.setFocusTraversable(false);
        radioButton.addEventFilter(MouseEvent.MOUSE_PRESSED, (event) -> {
            event.consume();
        });
          rotateTransition = TransitionFactory.getRotateTransition(node, Double.MAX_VALUE, 500, null);
        rotateTransition.setCycleCount(-1);
        rotateTransition.play();
//        checkBox.getStyleClass().add("")
        return radioButton;
    }

}
