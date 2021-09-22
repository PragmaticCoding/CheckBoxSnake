package com.arm.checkboxsnake.pixel;

import com.arm.checkboxsnake.misc.TransitionFactory;
import javafx.animation.Transition;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;

/**
 * --- here javadoc ---
 *
 * @author dnolte
 */
public class CheckBoxPixel implements Pixel {

    private CheckBox node;

    private Transition rotateTransition = null;

    public CheckBoxPixel() {
        node = buildToggleNode();
    }

    @Override
    public Node getNode() {
        return node;
    }

    @Override
    public void mark() {
        node.setSelected(true);
    }

    @Override
    public void unmark() {
        node.setSelected(false);
    }

    @Override
    public void markAsFood() {
        node.getStyleClass().add("check-box-food");
        mark();
    }

    @Override
    public void unmarkAsFood() {
        node.getStyleClass().remove("check-box-food");
        unmark();
    }

    @Override
    public void markAsSpecialFood() {
        markAsFood();
        rotateTransition = TransitionFactory.getRotateTransition(node, Double.MAX_VALUE, 500, null);
        rotateTransition.setCycleCount(-1);
        rotateTransition.play();
    }

    @Override
    public void unmarkAsSpecialFood() {
        unmarkAsFood();
        rotateTransition.stop();
        node.setRotate(0);
    }

    private CheckBox buildToggleNode() {
        CheckBox node = new CheckBox();
        node.setFocusTraversable(false);
        node.addEventFilter(MouseEvent.MOUSE_PRESSED, Event::consume);

        return node;
    }
}
