package javafx.checkboxsnake.pixel;

import javafx.animation.Transition;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ObservableObjectValue;
import javafx.checkboxsnake.data.Position;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * --- here javadoc ---
 *
 * @author dnolte
 */
public class CheckBoxPixel {

    private final Node node;
    private final Position position;
    private final ObservableList<Position> snakePixels;
    private final ObservableObjectValue<Position> regularFoodPosition;
    private final ObservableObjectValue<Position> specialFoodPosition;
    private Transition rotateTransition = null;

    public CheckBoxPixel(Position position,
                         ObservableList<Position> snakePixels,
                         ObservableObjectValue<Position> regularFoodPosition,
                         ObservableObjectValue<Position> specialFoodPosition) {
        this.position = position;
        this.snakePixels = snakePixels;
        this.regularFoodPosition = regularFoodPosition;
        this.specialFoodPosition = specialFoodPosition;
        node = buildToggleNode();
    }

    public Node getNode() {
        return node;
    }

    private Node buildToggleNode() {
        Rectangle rectangle = new Rectangle(20, 20, Color.WHITE);
        rectangle.setStroke(Color.DARKGRAY);
        Node snakeCircle = createCircle(Color.DARKBLUE, Bindings.createBooleanBinding(() -> snakePixels.contains(position), snakePixels));
        Node foodCircle = createCircle(Color.GREEN, Bindings.createBooleanBinding(() -> position.equals(regularFoodPosition.get()), regularFoodPosition));
        Node specialFoodCircle = createCircle(Color.RED, Bindings.createBooleanBinding(() -> position.equals(specialFoodPosition.get()), specialFoodPosition));
        return new StackPane(rectangle, foodCircle, specialFoodCircle, snakeCircle);
    }

    private Node createCircle(Paint colour, BooleanBinding visibleBinding) {
        Circle circle = new Circle(8);
        circle.setFill(colour);
        circle.visibleProperty().bind(visibleBinding);
        return circle;
    }
}
