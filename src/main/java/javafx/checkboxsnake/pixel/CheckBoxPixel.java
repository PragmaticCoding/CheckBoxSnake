package javafx.checkboxsnake.pixel;

import javafx.animation.Transition;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.checkboxsnake.data.Food;
import javafx.checkboxsnake.data.Position;
import javafx.checkboxsnake.misc.TransitionFactory;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

/**
 * --- here javadoc ---
 *
 * @author dnolte
 */
public class CheckBoxPixel {

    private final CheckBox node;
    private final Position position;
    private final ObservableList<Position> snakePixels;
    private final ObservableList<Food> foodList;
    private Transition rotateTransition = null;
    private ObjectProperty<Food> food = new SimpleObjectProperty<>();

    public CheckBoxPixel(Position position, ObservableList<Position> snakePixels, ObservableList<Food> foodList) {
        this.position = position;
        this.snakePixels = snakePixels;
        this.foodList = foodList;
        node = buildToggleNode();
        rotateTransition = TransitionFactory.getRotateTransition(node, Double.MAX_VALUE, 500, null);
        rotateTransition.setCycleCount(-1);
        foodList.addListener((InvalidationListener) (listener) -> {
            if (getFoodForThisPixel().isPresent()) {
                node.getStyleClass().add("check-box-food");
                handleRotation();
            } else {
                node.getStyleClass().remove("check-box-food");
                stopRotation();
            }
        });
        handleRotation();
    }

    private void stopRotation() {
        rotateTransition.stop();
        node.setRotate(0);
    }

    private void handleRotation() {
        getFoodForThisPixel().ifPresent(food -> {
            if (food.isSpecial()) {
                rotateTransition.play();
            } else {
                stopRotation();
            }
        });
    }

    private Optional<Food> getFoodForThisPixel() {
        return foodList.stream().filter(food -> food.getPosition().equals(position)).findAny();
    }

    public Node getNode() {
        return node;
    }

    private CheckBox buildToggleNode() {
        CheckBox node = new CheckBox();
        node.setFocusTraversable(false);
        node.addEventFilter(MouseEvent.MOUSE_PRESSED, Event::consume);
        node.selectedProperty().bind(Bindings.createBooleanBinding(() -> snakePixels.contains(position) || getFoodForThisPixel().isPresent(), foodList, snakePixels));

        return node;
    }
}
