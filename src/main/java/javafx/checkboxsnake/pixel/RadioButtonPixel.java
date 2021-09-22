package javafx.checkboxsnake.pixel;

import javafx.checkboxsnake.misc.TransitionFactory;
import javafx.animation.Transition;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;

/**
 * --- here javadoc ---
 *
 * @author dnolte
 */
public class RadioButtonPixel implements Pixel {

    private RadioButton radioButton;

    private Transition transition = null;

    public RadioButtonPixel() {
        radioButton = buildNode();
    }

    @Override
    public Node getNode() {
        return radioButton;
    }

    @Override
    public void mark() {
        radioButton.setSelected(true);
    }

    @Override
    public void unmark() {
        radioButton.setSelected(false);
    }

    @Override
    public void markAsFood() {
        radioButton.getStyleClass().add("check-box-food");
        mark();
    }

    @Override
    public void unmarkAsFood() {
        radioButton.getStyleClass().remove("check-box-food");
        unmark();
    }

    @Override
    public void markAsSpecialFood() {
        markAsFood();
        transition = TransitionFactory.getFromToScaleTransition(radioButton, radioButton.getScaleX(), radioButton.getScaleY(), radioButton.getScaleX(), radioButton.getScaleY(), 500, null);
        transition.setCycleCount(-1);
        transition.play();
    }

    @Override
    public void unmarkAsSpecialFood() {
        unmarkAsFood();
        transition.stop();
        radioButton.setRotate(0);
    }

    private RadioButton buildNode() {
        RadioButton radioButton = new RadioButton();
        radioButton.setFocusTraversable(false);
        radioButton.addEventFilter(MouseEvent.MOUSE_PRESSED, Event::consume);
        return radioButton;
    }

}
