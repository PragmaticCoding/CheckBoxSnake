package javafx.checkboxsnake.view;

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

public class Pixel {

    private final Node node;

    public Pixel(Position position,
                 ObservableList<Position> snakePixels,
                 ObservableObjectValue<Position> regularFoodPosition,
                 ObservableObjectValue<Position> specialFoodPosition,
                 ObservableObjectValue<Position> headPosition) {
        Rectangle rectangle = new Rectangle(20, 20, Color.WHITE);
        rectangle.setStroke(Color.DARKGRAY);
        Node snakeCircle = createCircle(Color.CORNFLOWERBLUE, Bindings.createBooleanBinding(() -> snakePixels.contains(position), snakePixels));
        Node foodCircle = createCircle(Color.GREEN, Bindings.createBooleanBinding(() -> position.equals(regularFoodPosition.get()), regularFoodPosition));
        Node specialFoodCircle = createCircle(Color.RED, Bindings.createBooleanBinding(() -> position.equals(specialFoodPosition.get()), specialFoodPosition));
        Node headCircle = createCircle(Color.DEEPSKYBLUE, Bindings.createBooleanBinding(() -> position.equals(headPosition.get()), headPosition));
        node = new StackPane(rectangle, snakeCircle, headCircle, foodCircle, specialFoodCircle);
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
