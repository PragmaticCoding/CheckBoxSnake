/*
 * Copyright (c) 2018 by ARM GmbH, www.ablex.com
 * TransitionFactory.java
 * created on 08.04.2018 - 09:59:16
 * edited by dnolte 08.04.2018 - 09:59:16
 */
package com.arm.checkboxsnake;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * --- here javadoc ---
 *
 * @author dnolte
 */
public class TransitionFactory {

    public static Transition getFadeTransition(Node node, double from, double to, double duration, EventHandler eventHandler) {
        prepareNode(node);
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(duration), node);
        prepareTransition(fadeTransition);
        fadeTransition.setFromValue(from);
        fadeTransition.setToValue(to);
        fadeTransition.setOnFinished(eventHandler);

        return fadeTransition;
    }

    public static Transition getFromToScaleTransition(Node node, double fromX, double fromY, double toX, double toY, double duration, EventHandler eventHandler) {
        prepareNode(node);
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(duration), node);
        scaleTransition.setToX(toX);
        scaleTransition.setToY(toY);
        scaleTransition.setFromX(fromX);
        scaleTransition.setFromY(fromY);

        prepareTransition(scaleTransition);

        scaleTransition.setOnFinished(eventHandler);

        return scaleTransition;
    }

    public static Transition getFromToTranslateTransition(Node node, double fromX, double fromY, double toX, double toY, double duration, EventHandler eventHandler) {
        prepareNode(node);
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(duration), node);
        translateTransition.setToX(toX);
        translateTransition.setToY(toY);
        translateTransition.setFromX(fromX);
        translateTransition.setFromY(fromY);

        prepareTransition(translateTransition);

        translateTransition.setOnFinished(eventHandler);

        return translateTransition;
    }

    public static Transition getParallelTransition(EventHandler eventHandler, Animation... animation) {
        ParallelTransition parallelTransition = new ParallelTransition(animation);
        if (eventHandler != null) {
            parallelTransition.setOnFinished(eventHandler);
        }
        prepareTransition(parallelTransition);
        return parallelTransition;
    }

    public static Transition getSequentialTransition(EventHandler eventHandler, Animation... animation) {
        SequentialTransition sequentialTransition = new SequentialTransition(animation);
        if (eventHandler != null) {
            sequentialTransition.setOnFinished(eventHandler);
        }
        prepareTransition(sequentialTransition);

        return sequentialTransition;
    }
    
        public static Transition getRotateTransition(Node node, double rotation, double duration, EventHandler eventHandler) {
//        prepareNode(node);
        RotateTransition rotateTransition = new RotateTransition(Duration.millis(duration));

        rotateTransition.setNode(node);
        rotateTransition.setByAngle(rotation);

        prepareTransition(rotateTransition);

        rotateTransition.setOnFinished(eventHandler);

        return rotateTransition;
    }

    private static void prepareNode(Node node) {
        node.setCache(true);
        node.setCacheHint(CacheHint.SPEED);
    }

    private static void prepareTransition(Transition transition) {
        transition.setInterpolator(Interpolator.EASE_BOTH);
    }

}
