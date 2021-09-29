package javafx.checkboxsnake.pane;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableIntegerValue;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * --- here javadoc ---
 *
 * @author dnolte
 */
public class GameOverPane extends VBox {

    public GameOverPane(Runnable gameStarter, ObservableIntegerValue points, ObservableBooleanValue gameOverProperty) {
        super(30);
        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundFill(Color.WHITE.deriveColor(1, 1, 1, .8), CornerRadii.EMPTY, Insets.EMPTY)));
        this.getChildren().addAll(new Label("Game Over"), createPointsLabel(points), createRestartButton(gameStarter));
        visibleProperty().bind(gameOverProperty);
    }

    private Label createPointsLabel(ObservableIntegerValue points) {
        Label labelPoints = new Label();
        labelPoints.textProperty().bind(Bindings.createStringBinding(() -> String.format("You got %d points!", points.get()), points));
        labelPoints.setFont(Font.font(null, FontWeight.BOLD, 15));
        return labelPoints;
    }

    private Button createRestartButton(Runnable gameStarter) {
        Button buttonRestart = new Button("Restart");
        buttonRestart.setOnAction((ActionEvent event) -> {
            gameStarter.run();
        });
        return buttonRestart;
    }
}
