package javafx.checkboxsnake.pixel;

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

public class CheckBoxPixel {

    private final Node node;

    public CheckBoxPixel(Position position,
                         ObservableList<Position> snakePixels,
                         ObservableObjectValue<Position> regularFoodPosition,
                         ObservableObjectValue<Position> specialFoodPosition) {
        Rectangle rectangle = new Rectangle(20, 20, Color.WHITE);
        rectangle.setStroke(Color.DARKGRAY);
        Node snakeCircle = createCircle(Color.DARKBLUE, Bindings.createBooleanBinding(() -> snakePixels.contains(position), snakePixels));
        Node foodCircle = createCircle(Color.GREEN, Bindings.createBooleanBinding(() -> position.equals(regularFoodPosition.get()), regularFoodPosition));
        Node specialFoodCircle = createCircle(Color.RED, Bindings.createBooleanBinding(() -> position.equals(specialFoodPosition.get()), specialFoodPosition));
        node = new StackPane(rectangle, foodCircle, specialFoodCircle, snakeCircle);
    }

    public Node getNode() {
        return node;
    }

    private Node createCircle(Paint colour, BooleanBinding visibleBinding) {
        Circle circle = new Circle(8);
        circle.setFill(colour);
        circle.visibleProperty().bind(visibleBinding);
        return circle;
    }
}
