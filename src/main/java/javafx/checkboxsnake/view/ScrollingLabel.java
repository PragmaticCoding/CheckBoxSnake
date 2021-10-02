package javafx.checkboxsnake.view;

import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.beans.value.ObservableIntegerValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

public class ScrollingLabel extends Label {

    private static final double DURATION_TRANSITION_POINTS = 200;

    public ScrollingLabel(ObservableIntegerValue boundValue) {
        super();
        initAnimation(boundValue);
    }

    private void initAnimation(ObservableIntegerValue boundValue) {
        boundValue.addListener((observable, oldValue, newValue) ->
                TransitionFactory.getSequentialTransition(null,
                        movePointsDownAndFadeOut(newValue),
                        movePointsUpAndFadeIn()).play());
    }

    private Transition movePointsUpAndFadeIn() {
        return createMoveAndFadeTransition(null, 50, 0, 0, 1);
    }

    private Transition movePointsDownAndFadeOut(Number newValue) {
        return createMoveAndFadeTransition(event -> setText(newValue.toString()), 0, -50, 1, 0);
    }

    private Transition createMoveAndFadeTransition(EventHandler<ActionEvent> eventHandler, int fromY, int toY, int fadeFrom, int fadeTo) {
        Transition translateTransition = TransitionFactory.getFromToTranslateTransition(this, 0, fromY,
                0, toY, DURATION_TRANSITION_POINTS);
        Transition fadeTransition = TransitionFactory.getFadeTransition(this, fadeFrom, fadeTo, DURATION_TRANSITION_POINTS);
        ParallelTransition results = new ParallelTransition(translateTransition, fadeTransition);
        results.setOnFinished(eventHandler);
        return results;
    }

}
