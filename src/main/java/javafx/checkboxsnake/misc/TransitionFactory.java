package javafx.checkboxsnake.misc;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.util.Duration;

public class TransitionFactory {

    public static Transition getFadeTransition(Node node, double from, double to, double duration) {
        prepareNode(node);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(duration), node);
        fadeTransition.setFromValue(from);
        fadeTransition.setToValue(to);
        return prepareTransition(fadeTransition);
    }

    public static Transition getFromToTranslateTransition(Node node, double fromX, double fromY, double toX, double toY, double duration) {
        prepareNode(node);
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(duration), node);
        translateTransition.setToX(toX);
        translateTransition.setToY(toY);
        translateTransition.setFromX(fromX);
        translateTransition.setFromY(fromY);
        return prepareTransition(translateTransition);
    }

    public static Transition getSequentialTransition(EventHandler<ActionEvent> eventHandler, Animation... animation) {
        SequentialTransition sequentialTransition = new SequentialTransition(animation);
        sequentialTransition.setOnFinished(eventHandler);
        return prepareTransition(sequentialTransition);
    }

    private static void prepareNode(Node node) {
        node.setCache(true);
        node.setCacheHint(CacheHint.SPEED);
    }

    private static Transition prepareTransition(Transition transition) {
        transition.setInterpolator(Interpolator.EASE_BOTH);
        return transition;
    }

}
